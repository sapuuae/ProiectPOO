package reading;

import base.WorkingDistributors;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<Consumers> consumers;
    private ArrayList<WorkingDistributors> distributors;

    public ArrayList<Consumers> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumers> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<WorkingDistributors> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<WorkingDistributors> distributors) {
        this.distributors = distributors;
    }
}
