package writing;

public final class ConsumersToWrite {
    private final Integer id;
    private final Boolean isBankrupt;
    private final Integer budget;

    public ConsumersToWrite(final Integer id, final Boolean isBankrupt, final Integer budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getIsBankrupt() {
        return isBankrupt;
    }

    public Integer getBudget() {
        return budget;
    }
}
