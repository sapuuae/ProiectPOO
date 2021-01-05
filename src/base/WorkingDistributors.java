package base;

import reading.Distributors;
import reading.Constants;
import reading.Producers;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class WorkingDistributors extends Distributors {
    private Double contractCost = 0.0;
    private Integer numberOfCustomers = 0;
    private Boolean isBankrupt = false;
    private final ArrayList<ElectricConsumers> distributorConsumers = new ArrayList<>();
    private final ArrayList<Producers> producersArrayList = new ArrayList<>();
    private Boolean needToUpdate = true;

    public Boolean getNeedToUpdate() {
        return needToUpdate;
    }

    public void setNeedToUpdate(Boolean needToUpdate) {
        this.needToUpdate = needToUpdate;
    }

    public final ArrayList<Producers> getProducersArrayList() {
        return producersArrayList;
    }

    @Override
    public final void setInitialInfrastructureCost(final Double initialInfrastructureCost) {
        super.setInitialInfrastructureCost(initialInfrastructureCost);
        super.setInitialContractCost(initialInfrastructureCost);
        this.setInfrastructureCost(initialInfrastructureCost);
        this.setTotalCosts(getInfrastructureCost());
    }

    @Override
    public final void setInitialProductionCost(final Double initialProductionCost) {
        super.setInitialProductionCost(initialProductionCost);
        super.setInitialContractCost(this.getInitialContractCost()
                + initialProductionCost + Constants.getPROFIT() * initialProductionCost);
        this.setProductionCost(initialProductionCost);
        this.contractCost = this.getInitialContractCost();
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
                    Math.floor(this.getInfrastructureCost() / this.numberOfCustomers)
                            + this.getProductionCost()
                            + Math.round(Math.floor(Constants.getPROFIT()
                            * this.getProductionCost())));
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
            this.contractCost = this.getInfrastructureCost() + this.getProductionCost()
                    + Math.round(Math.floor(Constants.getPROFIT() * this.getProductionCost()));
        }
        this.setTotalCosts(this.getInfrastructureCost()
                + this.getProductionCost() * this.numberOfCustomers);
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
            this.setInitialBudget(this.getInitialBudget() - this.getInfrastructureCost());
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
