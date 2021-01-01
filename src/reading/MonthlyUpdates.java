package reading;

import base.ElectricConsumers;

import java.util.ArrayList;

public final class MonthlyUpdates {
    private ArrayList<Consumers> newConsumers;
    private final ArrayList<ElectricConsumers> consumersToWork = new ArrayList<>();
    private ArrayList<CostsChanges> distributorChanges;
    private ArrayList<ProducerChanges> producerChanges;



    public ArrayList<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public ArrayList<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<CostsChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(final ArrayList<CostsChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public ArrayList<ElectricConsumers> getConsumersToWork() {
        return consumersToWork;
    }
}
