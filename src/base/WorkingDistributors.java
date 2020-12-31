package base;

import reading.Distributors;
import reading.Constants;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class WorkingDistributors extends Distributors {
    private Double infrastructureCost = 0.0;
    private Double productionCost = 0.0;
    private Double contractCost = 0.0;
    private Integer numberOfCustomers = 0;
    private Double totalCosts = 0.0;
    private Boolean isBankrupt = false;
    private final ArrayList<ElectricConsumers> distributorConsumers = new ArrayList<>();

    @Override
    public final void setInitialInfrastructureCost(final Double initialInfrastructureCost) {
        super.setInitialInfrastructureCost(initialInfrastructureCost);
        super.setInitialContractCost(initialInfrastructureCost);
        this.infrastructureCost = initialInfrastructureCost;
        this.totalCosts = infrastructureCost;
    }

    @Override
    public final void setInitialProductionCost(final Double initialProductionCost) {
        super.setInitialProductionCost(initialProductionCost);
        super.setInitialContractCost(this.getInitialContractCost()
                + initialProductionCost + Constants.getPROFIT() * initialProductionCost);
        this.productionCost = initialProductionCost;
        this.contractCost = this.getInitialContractCost();
    }

    public final Double getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setInfrastructureCost(final Double infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public final Double getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final Double productionCost) {
        this.productionCost = productionCost;
    }

    public final Double getContractCost() {
        return contractCost;
    }

    public final Integer getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public final void setNumberOfCustomers(final Integer numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    /**
     * Update the contract cost of the distributor, at the beginning of the month.
     */
    public final void updateContractCost() {
        if (this.getNumberOfCustomers() != 0) {
            this.contractCost = (double) Math.round(
                    Math.floor(this.infrastructureCost / this.numberOfCustomers)
                            + this.productionCost
                            + Math.round(Math.floor(Constants.getPROFIT() * this.productionCost)));
            ArrayList<ElectricConsumers> consumersToRemove = new ArrayList<>();
            for (ElectricConsumers c : distributorConsumers) {
                if (c.getRemainedContractMonths() == 0) {
                    consumersToRemove.add(c);
                    this.numberOfCustomers--;
                }
            }
            for (ElectricConsumers c : consumersToRemove) {
                distributorConsumers.remove(c);
            }
        } else {
            this.contractCost = this.infrastructureCost + this.productionCost
                    + Math.round(Math.floor(Constants.getPROFIT() * this.productionCost));
        }
        this.totalCosts = this.infrastructureCost + this.productionCost * this.numberOfCustomers;
    }

    public final Double getTotalCosts() {
        return totalCosts;
    }

    public final void setTotalCosts(final Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public final Boolean getBankrupt() {
        return isBankrupt;
    }

    public final ArrayList<ElectricConsumers> getDistributorConsumers() {
        return distributorConsumers;
    }

    /**
     * Make the payment for the distributor and check if it's bankrupt.
     */
    public final void updateBudget() {
        if (numberOfCustomers == 0) {
            this.setInitialBudget(this.getInitialBudget() - this.infrastructureCost);
        } else {
            this.setInitialBudget(this.getInitialBudget() - this.getTotalCosts());
            try {
                distributorConsumers.forEach(a -> {
                    if (a.getBankrupt()) {
                        distributorConsumers.remove(a);
                        this.numberOfCustomers--;
                    }
                });
            } catch (ConcurrentModificationException e) {
                return;
            }
        }
        if (this.getInitialBudget() < 0) {
            this.isBankrupt = true;
            this.distributorConsumers.clear();
        }
    }
}
