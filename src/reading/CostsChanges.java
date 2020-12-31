package reading;

public final class CostsChanges {
    private Integer id;
    private Double infrastructureCost;
    private Double productionCost;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final Double infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public Double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final Double productionCost) {
        this.productionCost = productionCost;
    }
}
