package strategies;

import reading.Producers;

import java.util.ArrayList;
import java.util.Comparator;

public class QuantityStrategy implements ChooseProducerStrategy{
    private final ArrayList<Producers> producersArrayList;

    public QuantityStrategy(ArrayList<Producers> producersArrayList) {
        this.producersArrayList = producersArrayList;
    }

    @Override
    public void chooseProducer() {
        /*
        Sort by id in the beginning.
         */
        producersArrayList.sort(Comparator.comparing(Producers::getId));
        producersArrayList.sort((o1, o2) -> {
            int c = o2.getEnergyPerDistributor().compareTo(o1.getEnergyPerDistributor());
            if (c == 0) {
                c = o1.getId().compareTo(o2.getId());
            }
            return c;
        });
    }
}
