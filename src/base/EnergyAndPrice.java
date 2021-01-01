package base;

public class EnergyAndPrice {
    private final Integer energy;
    private final Double price;

    public EnergyAndPrice(Integer energy, Double price) {
        this.energy = energy;
        this.price = price;
    }

    public Integer getEnergy() {
        return energy;
    }

    public Double getPrice() {
        return price;
    }
}
