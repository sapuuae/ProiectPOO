package strategies;

import base.WorkingDistributors;
import reading.Producers;

import java.util.ArrayList;

public class PriceStrategy implements ChooseProducerStrategy{
    private final ArrayList<Producers> producersArrayList;
    private final ArrayList<WorkingDistributors> distributorsArrayList;

    public PriceStrategy(final ArrayList<Producers> producersArrayList,
                         final ArrayList<WorkingDistributors> distributorsArrayList) {
        this.producersArrayList = producersArrayList;
        this.distributorsArrayList = distributorsArrayList;
    }

    @Override
    public void chooseProducer() {

    }
}
