package base;

import observer.Subject;
import reading.DistributorStats;
import reading.ProducerChanges;
import reading.Producers;
import strategies.ChooseProducerStrategy;
import strategies.ChooseProducerStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;

public final class ProducersTasks {
    /**
     * Used to update the energy given by a producer.
     * @param producersArrayList the array producers
     * @param changes new energy values
     * @param distributorsArrayList the array of distributors
     * @param month current month
     */
    public void updateProducers(final ArrayList<Producers> producersArrayList,
                                final ArrayList<ProducerChanges> changes,
                                final ArrayList<WorkingDistributors> distributorsArrayList,
                                final Integer month) {
        producersArrayList.sort(Comparator.comparing(Producers::getId));
        Subject subject = new Subject();
        for (Producers p : producersArrayList) {
            subject.attach(p);
        }
        subject.setState(changes, distributorsArrayList, month);
    }

    /**
     * Used to select a producer for a distributor.
     * @param distributorsArrayList the array of distributors
     * @param producersArrayList the array of producers
     * @param month current month
     */
    public void chooseProducers(final ArrayList<WorkingDistributors> distributorsArrayList,
                                final ArrayList<Producers> producersArrayList,
                                final int month) {
        distributorsArrayList.sort(Comparator.comparing(WorkingDistributors::getId));
        for (WorkingDistributors d : distributorsArrayList) {
            ArrayList<Producers> distributorProducers = d.getProducersArrayList();
            for (Producers p : distributorProducers) {
                p.getMonthlyStats().get(month).getDistributorsIds().add(d.getId());
            }
        }
        for (WorkingDistributors d : distributorsArrayList) {
            if (!d.getBankrupt()) {
                if (d.getNeedToUpdate()) {
                    d.setNeedToUpdate(false);
                    ChooseProducerStrategy strategy = ChooseProducerStrategyFactory.createStrategy(
                            d.getProducerStrategy().label, producersArrayList);
                    strategy.chooseProducer();
                    d.setTotalEnergyNow(0);
                    d.getActualEnergyList().clear();
                    ArrayList<Producers> currentProducers = d.getProducersArrayList();
                    for (Producers p : currentProducers) {
                        DistributorStats stats = p.getMonthlyStats().get(month);
                        ArrayList<Integer> ids = stats.getDistributorsIds();
                        ids.remove(d.getId());
                        p.setActualDistributors(p.getActualDistributors() - 1);
                    }
                    d.getProducersArrayList().clear();
                    for (Producers p : producersArrayList) {
                        if (d.getTotalEnergyNow() < d.getEnergyNeededKW()) {
                            if (p.getActualDistributors() < p.getMaxDistributors()) {
                                d.setTotalEnergyNow(d.getTotalEnergyNow()
                                        + p.getEnergyPerDistributor());
                                d.getActualEnergyList().add(new EnergyAndPrice(
                                        p.getEnergyPerDistributor(), p.getPriceKW()));
                                d.getProducersArrayList().add(p);
                                p.setActualDistributors(p.getActualDistributors() + 1);
                            }
                        } else {
                            break;
                        }
                    }

                    double cost = 0.0;
                    for (EnergyAndPrice energy : d.getActualEnergyList()) {
                        cost += energy.getEnergy() * energy.getPrice();
                    }
                    d.setProductionCost((double) Math.round(Math.floor(cost / 10)));
                    if (month == 0) {
                        d.updateContractCost();
                    }
                }
            }
        }

        for (Producers p : producersArrayList) {
            p.getMonthlyStats().get(month).getDistributorsIds().clear();
        }

        for (WorkingDistributors d : distributorsArrayList) {
            producersArrayList.sort(Comparator.comparing(Producers::getId));
            ArrayList<Producers> distributorProducers = d.getProducersArrayList();
            for (Producers p : distributorProducers) {
                p.getMonthlyStats().get(month).getDistributorsIds().add(d.getId());
            }
        }
    }
}
