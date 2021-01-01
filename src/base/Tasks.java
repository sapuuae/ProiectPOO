package base;

import observer.Subject;
import reading.CostsChanges;
import reading.ProducerChanges;
import reading.Producers;
import strategies.ChooseProducerStrategy;
import strategies.ChooseProducerStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;

public final class Tasks {
    private static Tasks instance = null;

    private Tasks() { }

    /**
     * Creates an instance of ConsumerTasks, with Singleton Pattern.
     * @return an instance of ConsumerTasks.
     */
    public static Tasks getInstance() {
        if (instance == null) {
            instance = new Tasks();
        }
        return instance;
    }

    /**
     * Updates consumers and costs for distributors.
     * @param distributorsArrayList the list of distributors.
     * @param consumers the list of consumers.
     * @param newConsumers consumers to be added.
     * @param costsChanges costs to be modified.
     */
    public void monthlyUpdate(final ArrayList<WorkingDistributors> distributorsArrayList,
                              final ArrayList<ElectricConsumers> consumers,
                              final ArrayList<ElectricConsumers> newConsumers,
                              final ArrayList<CostsChanges> costsChanges,
                              final ArrayList<Producers> producersArrayList,
                              final ArrayList<ProducerChanges> producerChanges) {
        consumers.addAll(newConsumers);
        distributorsArrayList.sort(Comparator.comparing(WorkingDistributors::getId));
        for (CostsChanges c : costsChanges) {
            /*
            Calculate new prices for contracts.
             */
            WorkingDistributors currentDistributor = distributorsArrayList.get(c.getId());
            currentDistributor.setInfrastructureCost(c.getInfrastructureCost());
        }
        for (WorkingDistributors d : distributorsArrayList) {
            d.updateContractCost();
            d.setTotalCosts(d.getInfrastructureCost()
                    + d.getProductionCost() * d.getNumberOfCustomers());
        }
        distributorsArrayList.sort(Comparator.comparing(WorkingDistributors::getContractCost));
        producersArrayList.sort(Comparator.comparing(Producers::getId));
        Subject subject = new Subject();
        for (Producers p : producersArrayList) {
            subject.attach(p);
        }
        subject.setState(producerChanges);
    }

    /**
     * Search in the distributors' list for a contract.
     * @param distributorsArrayList the list of distributors
     * @param c consumer to choose contract for
     */
    public void chooseContract(final ArrayList<WorkingDistributors> distributorsArrayList,
                               final ElectricConsumers c) {
        /*
        Sort the distributors by their contract price everytime.
        */
        if (c.getAssignedDistributor() == null || c.getRemainedContractMonths() == 0
                || c.getAssignedDistributor().getBankrupt()) {
            if (c.getAssignedDistributor() != null
                    && c.getAssignedDistributor().getBankrupt()) {
                c.setLatePayment(0);
            }
            for (WorkingDistributors d : distributorsArrayList) {
                if (!d.getBankrupt()) {
                    if (c.getLatePayment() == 1) {
                        c.setOldDistributor(c.getAssignedDistributor());
                    } else {
                        c.setOldDistributor(null);
                    }
                    c.setAssignedDistributor(d); // choose a distributor for the consumer
                    c.setRemainedContractMonths(d.getContractLength());
                    c.setPrice(d.getContractCost());
                    d.setNumberOfCustomers(d.getNumberOfCustomers() + 1);
                    d.getDistributorConsumers().add(c);
                    d.setTotalCosts(d.getTotalCosts() + d.getProductionCost());
                    break;
                }
            }
        }
    }

    /**
     * Give the monthly payment for consumer and make the operations.
     * @param consumers the list of consumers
     * @param distributorsArrayList the list of distributors
     */
    public void updateConsumer(final ArrayList<ElectricConsumers> consumers,
                               final ArrayList<WorkingDistributors> distributorsArrayList) {
        for (ElectricConsumers c : consumers) {
            if (!c.getBankrupt()) {
                c.setInitialBudget(c.getInitialBudget() + c.getMonthlyIncome());
                chooseContract(distributorsArrayList, c);
                c.makeThePayment();
            }
        }
    }

    /**
     * Update the budget of the distributor.
     * @param distributorsArrayList the list of distributors
     */
    public void updateDistributor(final ArrayList<WorkingDistributors> distributorsArrayList) {
        for (WorkingDistributors d : distributorsArrayList) {
            if (!d.getBankrupt()) {
                d.updateBudget();
            }
        }
    }

    public void chooseProducers(final ArrayList<WorkingDistributors> distributorsArrayList,
                                final ArrayList<Producers> producersArrayList) {
            for (WorkingDistributors d : distributorsArrayList) {
                ChooseProducerStrategy strategy = ChooseProducerStrategyFactory.createStrategy(
                        d.getProducerStrategy().label, producersArrayList);
                strategy.chooseProducer();
                d.setTotalEnergyNow(0);
                d.getActualEnergyList().clear();
                for (Producers p : producersArrayList) {
                    if (d.getTotalEnergyNow() < d.getEnergyNeededKW()) {
                        if (p.getActualDistributors() < p.getMaxDistributors()) {
                            d.setTotalEnergyNow(d.getTotalEnergyNow() + p.getEnergyPerDistributor());
                            d.getActualEnergyList().add(new EnergyAndPrice(
                                    p.getEnergyPerDistributor(), p.getPriceKW()));
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
                d.updateContractCost();
            }
    }
}
