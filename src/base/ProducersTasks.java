package base;

import observer.Subject;
import reading.ProducerChanges;
import reading.Producers;
import strategies.ChooseProducerStrategy;
import strategies.ChooseProducerStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;

public class ProducersTasks {
    public void updateProducers(final ArrayList<Producers> producersArrayList,
                                final ArrayList<ProducerChanges> changes,
                                final ArrayList<WorkingDistributors> distributorsArrayList) {
        producersArrayList.sort(Comparator.comparing(Producers::getId));
        Subject subject = new Subject();
        for (Producers p : producersArrayList) {
            subject.attach(p);
        }
        subject.setState(changes, distributorsArrayList);
    }

    public void chooseProducers(final ArrayList<WorkingDistributors> distributorsArrayList,
                                final ArrayList<Producers> producersArrayList,
                                final int month) {
        distributorsArrayList.sort(Comparator.comparing(WorkingDistributors::getId));
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
                        p.setActualDistributors(0);
                    }
                    d.getProducersArrayList().clear();
                    for (Producers p : producersArrayList) {
                        if (d.getTotalEnergyNow() < d.getEnergyNeededKW()) {
                            if (p.getActualDistributors() < p.getMaxDistributors()) {
                                d.setTotalEnergyNow(d.getTotalEnergyNow() + p.getEnergyPerDistributor());
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
        for (WorkingDistributors d : distributorsArrayList) {
            producersArrayList.sort(Comparator.comparing(Producers::getId));
            ArrayList<Producers> distributorProducers = d.getProducersArrayList();
            for (Producers p : distributorProducers) {
                p.getMonthlyStats().get(month).getDistributorsIds().add(d.getId());
            }
        }
    }
}
