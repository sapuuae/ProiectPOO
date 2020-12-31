package factory;

import base.ElectricConsumers;
import reading.Consumers;
import reading.MonthlyUpdates;

import java.util.ArrayList;

public final class CreateElectricConsumers {
    private final ArrayList<Consumers> consumers;
    private final ArrayList<MonthlyUpdates> monthlyUpdates;

    public CreateElectricConsumers(final ArrayList<Consumers> consumers,
                                   final ArrayList<MonthlyUpdates> monthlyUpdates) {
        this.consumers = consumers;
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     * Used to create the ArrayLists for consumers list and newConsumers.
     * @param consumersList will be added elements to it
     */
    public void createElectricConsumers(final ArrayList<ElectricConsumers> consumersList) {
        for (Consumers c : consumers) {
            FactoryConsumer factoryConsumer = FactoryConsumer.getInstance();
            consumersList.add(factoryConsumer.createConsumer(c));
        }

        for (MonthlyUpdates m : monthlyUpdates) {
            ArrayList<Consumers> newConsumers = m.getNewConsumers();
            for (Consumers c : newConsumers) {
                FactoryConsumer factoryConsumer = FactoryConsumer.getInstance();
                m.getConsumersToWork().add(factoryConsumer.createConsumer(c));
            }
        }
    }

}
