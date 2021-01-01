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
    private ArrayList<EnergyAndPrice> actualEnergyList = new ArrayList<>();
    private Integer totalEnergyNow;

    public Integer getTotalEnergyNow() {
        return totalEnergyNow;
    }

    public void setTotalEnergyNow(Integer totalEnergyNow) {
        this.totalEnergyNow = totalEnergyNow;
    }

    public ArrayList<EnergyAndPrice> getActualEnergyList() {
        return actualEnergyList;
    }

    public void setActualEnergyList(ArrayList<EnergyAndPrice> actualEnergyList) {
        this.actualEnergyList = actualEnergyList;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

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

    public final Double getInitialProductionCost() {
        return initialProductionCost;
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
