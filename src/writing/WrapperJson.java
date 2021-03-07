package writing;

import java.util.ArrayList;

public final class WrapperJson {
    private final ArrayList<ConsumersToWrite> consumers;
    private final ArrayList<DistributorsToWrite> distributors;
    private final ArrayList<ProducersToWrite> energyProducers;

    public WrapperJson(final ArrayList<ConsumersToWrite> consumers,
                       final ArrayList<DistributorsToWrite> distributors,
                       final ArrayList<ProducersToWrite> energyProducers) {
        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public ArrayList<ProducersToWrite> getEnergyProducers() {
        return energyProducers;
    }

    public ArrayList<DistributorsToWrite> getDistributors() {
        return distributors;
    }

    public ArrayList<ConsumersToWrite> getConsumers() {
        return consumers;
    }
}
