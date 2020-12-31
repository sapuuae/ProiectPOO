package reading;

public class Distributors {
    private Integer id;
    private Integer contractLength;
    private Double initialBudget;
    private Double initialInfrastructureCost;
    private Double initialProductionCost;
    private Double initialContractCost = 0.0;

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
