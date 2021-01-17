package observer;

import base.WorkingDistributors;
import reading.ProducerChanges;

import java.util.ArrayList;

public final class Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final ArrayList<ProducersWithEnergy> producers = new ArrayList<>();

    /**
     * Attach the observer to the list.
     * @param observer the observer to be added
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /**
     * Update the state for the observers.
     * @param changes new prices for producers
     * @param distributorsArrayList used to notify the distributors too,
     *                              to search for a new producer
     * @param month used to obtain the distributors
     */
    public void setState(ArrayList<ProducerChanges> changes,
                         ArrayList<WorkingDistributors> distributorsArrayList,
                         Integer month) {
        for (ProducerChanges p : changes) {
            producers.add(new ProducersWithEnergy(p.getId(), p.getEnergyPerDistributor()));
        }
        notifyAllObservers(distributorsArrayList, month);
    }

    public void notifyAllObservers(ArrayList<WorkingDistributors> distributorsArrayList,
                                   Integer month) {
        for (ProducersWithEnergy p : producers) {
            observers.get(p.getId()).update(p.getEnergy(), distributorsArrayList, month);
        }
    }
}
