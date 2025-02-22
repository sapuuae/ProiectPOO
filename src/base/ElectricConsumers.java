package base;

import reading.Consumers;
import reading.Constants;

public final class ElectricConsumers extends Consumers {
    private WorkingDistributors assignedDistributor;
    private WorkingDistributors oldDistributor;
    /*
    Contract's cost.
     */
    private Double price = 0.0;
    private Integer latePayment = 0;
    private Integer remainedContractMonths = 0;
    private Boolean isBankrupt = false;
    private Double lateBill = 0.0;

    public ElectricConsumers(final Consumers c) {
        this.setId(c.getId());
        this.setInitialBudget(c.getInitialBudget());
        this.setMonthlyIncome(c.getMonthlyIncome());
    }

    public WorkingDistributors getAssignedDistributor() {
        return assignedDistributor;
    }

    public void setAssignedDistributor(final WorkingDistributors assignedDistributor) {
        this.assignedDistributor = assignedDistributor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getLatePayment() {
        return latePayment;
    }

    public void setLatePayment(final Integer latePayment) {
        this.latePayment = latePayment;
    }

    public Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final Integer remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public Boolean getBankrupt() {
        return isBankrupt;
    }

    public void setOldDistributor(final WorkingDistributors oldDistributor) {
        this.oldDistributor = oldDistributor;
    }

    /**
     * Used to see if a consumer can pay in the actual month.
     * @param lastBillPenalty last month bill
     */
    public void checkThePayment(Double lastBillPenalty) {
        if (this.getInitialBudget() - lastBillPenalty - this.price < 0) {
            this.isBankrupt = true;
            this.remainedContractMonths = 0;
        } else {
            this.setInitialBudget(this.getInitialBudget() - lastBillPenalty
                    - this.price);
            this.assignedDistributor.setInitialBudget(
                    this.assignedDistributor.getInitialBudget() + lastBillPenalty
                            + this.price);
            this.remainedContractMonths--;
            this.latePayment = 0;
        }
    }

    /**
     * Make the payment for the consumer.
     */
    public void makeThePayment() {
        /*
        Check if the consumer can afford the payment.
         */
        if ((this.getInitialBudget() - this.price < 0) && this.latePayment == 0) {
            this.latePayment = 1;
            this.lateBill = this.price;
            this.remainedContractMonths--;
            return;
        }
        if (this.latePayment == 1) {
            /*
            The consumer has a month not paid, so check if he can afford to pay
            last month bill + current bill.
             */
            Double lastBillPenalty = (double) Math.round(
                    Math.floor(Constants.getBILLDIFF() * this.lateBill));
            if (this.oldDistributor != null) {
                if (this.oldDistributor == this.assignedDistributor) {
                    /*
                    The odl distributor is the same as the current one.
                     */
                    checkThePayment(lastBillPenalty);
                    return;
                } else {
                    if (this.getInitialBudget() - lastBillPenalty - this.price < 0) {
                        if (this.getInitialBudget() - lastBillPenalty < 0) {
                            /*
                            Can't afford just the old one.
                             */
                            this.isBankrupt = true;
                            this.remainedContractMonths = 0;
                        } else {
                            this.setInitialBudget(this.getInitialBudget() - lastBillPenalty);
                            this.oldDistributor.setInitialBudget(
                                    this.oldDistributor.getInitialBudget() + lastBillPenalty);
                            this.remainedContractMonths--;
                            this.lateBill = this.price;
                            return;
                        }
                    } else {
                        this.setInitialBudget(this.getInitialBudget() - lastBillPenalty
                                - this.price);
                        this.assignedDistributor.setInitialBudget(
                                this.assignedDistributor.getInitialBudget() + lastBillPenalty
                                        + this.price);
                        this.remainedContractMonths--;
                        this.latePayment = 0;
                        return;
                    }
                }
            } else {
                checkThePayment(lastBillPenalty);
                return;
            }
        }
        this.setInitialBudget(this.getInitialBudget() - this.price);
        this.getAssignedDistributor().setInitialBudget(
                this.getAssignedDistributor().getInitialBudget() + this.price);
        this.remainedContractMonths--;
    }

}
