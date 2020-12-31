package strategies;

import base.WorkingDistributors;
import reading.Producers;

import java.util.ArrayList;

public class GreenStrategy implements ChooseProducerStrategy{
    private final ArrayList<Producers> producersArrayList;
    private final ArrayList<WorkingDistributors> distributorsArrayList;

    public GreenStrategy(final ArrayList<Producers> producersArrayList,
                         final ArrayList<WorkingDistributors> distributorsArrayList) {
        this.producersArrayList = producersArrayList;
        this.distributorsArrayList = distributorsArrayList;
    }

    @Override
    public void chooseProducer() {
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
            return c;
        });
    }
}
