package factory;

import base.ElectricConsumers;
import reading.Consumers;

public final class FactoryConsumer {
    private static FactoryConsumer instance = null;

    private FactoryConsumer() { }

    /**
     * Used to get the instance of factoryConsumer.
     * @return an instance for the Factory
     */
    public static FactoryConsumer getInstance() {
        if (instance == null) {
            instance = new FactoryConsumer();
        }
        return instance;
    }

    /**
     * Used to create an ElectricConsumer from a Consumer.
     * @param c used to get fields
     * @return an ElectricConsumer
     */
    public ElectricConsumers createConsumer(final Consumers c) {
        return new ElectricConsumers(c);
    }
}
