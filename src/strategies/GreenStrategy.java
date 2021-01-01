package strategies;

import reading.Producers;

import java.util.ArrayList;
import java.util.Comparator;

public class GreenStrategy implements ChooseProducerStrategy{
    private final ArrayList<Producers> producersArrayList;

    public GreenStrategy(ArrayList<Producers> producersArrayList) {
        this.producersArrayList = producersArrayList;
    }

    @Override
    public void chooseProducer() {
        /*
        Sort by id in the beginning.
         */
        producersArrayList.sort(Comparator.comparing(Producers::getId));
        producersArrayList.sort((o1, o2) -> {
            int c = Boolean.compare(o2.getEnergyType().isRenewable(), o1.getEnergyType().isRenewable());
            if (c == 0) {
                /*
                Sort them by price then.
                 */
                c = o1.getPriceKW().compareTo(o2.getPriceKW());
            }
            if (c == 0) {
                /*
                Sort them by quantity.
                 */
                c = o2.getEnergyPerDistributor().compareTo(o1.getEnergyPerDistributor());
            }
            if (c == 0) {
                c = o1.getId().compareTo(o2.getId());
            }
            return c;
        });
    }
}
