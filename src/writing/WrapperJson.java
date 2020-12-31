package writing;

import java.util.ArrayList;

public final class WrapperJson {
    private final ArrayList<ConsumersToWrite> consumers;
    private final ArrayList<DistributorsToWrite> distributors;

    public WrapperJson(final ArrayList<ConsumersToWrite> consumers,
                       final ArrayList<DistributorsToWrite> distributors) {
        this.consumers = consumers;
        this.distributors = distributors;
    }

    public ArrayList<DistributorsToWrite> getDistributors() {
        return distributors;
    }

    public ArrayList<ConsumersToWrite> getConsumers() {
        return consumers;
    }
}
