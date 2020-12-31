package strategies;

import base.WorkingDistributors;
import reading.Producers;

import java.util.ArrayList;

public class QuantityStrategy implements ChooseProducerStrategy{
    private final ArrayList<Producers> producersArrayList;
    private final ArrayList<WorkingDistributors> distributorsArrayList;

    public QuantityStrategy(final ArrayList<Producers> producersArrayList,
                            final ArrayList<WorkingDistributors> distributorsArrayList) {
        this.producersArrayList = producersArrayList;
        this.distributorsArrayList = distributorsArrayList;
    }

    @Override
    public void chooseProducer() {
    }
}
