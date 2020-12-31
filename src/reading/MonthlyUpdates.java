package reading;

import base.ElectricConsumers;

import java.util.ArrayList;

public final class MonthlyUpdates {
    private ArrayList<Consumers> newConsumers;
    private final ArrayList<ElectricConsumers> consumersToWork = new ArrayList<>();
    private ArrayList<CostsChanges> costsChanges;

    public ArrayList<Consumers> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumers> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<CostsChanges> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final ArrayList<CostsChanges> costsChanges) {
        this.costsChanges = costsChanges;
    }

    public ArrayList<ElectricConsumers> getConsumersToWork() {
        return consumersToWork;
    }
}
