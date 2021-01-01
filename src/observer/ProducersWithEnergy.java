package observer;

public final class ProducersWithEnergy {
    private final Integer id;
    private final Integer energy;

    public ProducersWithEnergy(Integer id, Integer energy) {
        this.id = id;
        this.energy = energy;
    }

    public Integer getId() {
        return id;
    }

    public Integer getEnergy() {
        return energy;
    }
}
