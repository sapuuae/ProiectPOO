package writing;

public final class ConsumersToWriteInDistributors {
    private final Integer consumerId;
    private final Integer price;
    private final Integer remainedContractMonths;

    public ConsumersToWriteInDistributors(final Integer consumerId,
                                          final Integer price,
                                          final Integer remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }
}
