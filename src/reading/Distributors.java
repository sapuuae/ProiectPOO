package reading;

import base.EnergyAndPrice;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class Distributors {
    private Integer id;
    private Integer contractLength;
    private Double initialBudget;
    private Double initialInfrastructureCost;
    private Double initialProductionCost;
    private Double initialContractCost = 0.0;
    private Double infrastructureCost = 0.0;
    private Double productionCost = 0.0;
    private Double totalCosts = 0.0;
    private Integer energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private final ArrayList<EnergyAndPrice> actualEnergyList = new ArrayList<>();
    private Integer totalEnergyNow;

    /**
     * Used to get actual energy gotten by distributor.
     * @return the value of energy
     */
    public Integer getTotalEnergyNow() {
        return totalEnergyNow;
    }

    /**
     * Used to update the new energy gotten.
     * @param totalEnergyNow new value of energy
     */
    public void setTotalEnergyNow(Integer totalEnergyNow) {
        this.totalEnergyNow = totalEnergyNow;
    }

    /**
     * Get the list with energy from producers.
     * @return the list with energy values
     */
    public ArrayList<EnergyAndPrice> getActualEnergyList() {
        return actualEnergyList;
    }

    /**
     * Get the strategy searched by distributor.
     * @return the strategy
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * Set the strategy searched.
     * @param producerStrategy the strategy to be set
     */
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     * Get the energy needed by distributor.
     * @return the energy needed
     */
    public Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * Used for reading the energy needed.
     * @param energyNeededKW the energy to be set
     */
    public void setEnergyNeededKW(Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final Double getTotalCosts() {
        return totalCosts;
    }

    public final void setTotalCosts(final Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public final Double getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final Double productionCost) {
        this.productionCost = productionCost;
    }

    public final Double getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setInfrastructureCost(final Double infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /*
        Get the ContractCost.
         */
    public final Double getInitialContractCost() {
        return initialContractCost;
    }

    /*
    Set initialContractCost.
     */
    public final void setInitialContractCost(final double initialContractCost) {
        this.initialContractCost = initialContractCost;
    }

    /*
    Get distributor's id.
     */
    public final Integer getId() {
        return id;
    }

    public final Integer getContractLength() {
        return contractLength;
    }

    public final Double getInitialBudget() {
        return initialBudget;
    }

    public final Double getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    public final void setInitialBudget(final Double initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * Will be extended in WorkingDistributors.
     * @param initialInfrastructureCost initialInfrastructureCost
     */
    public void setInitialInfrastructureCost(final Double initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * Will be extended in WorkingDistributors.
     * @param initialProductionCost initialProductionCost
     */
    public void setInitialProductionCost(final Double initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }
}
