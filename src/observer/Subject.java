package observer;

import base.WorkingDistributors;
import reading.ProducerChanges;

import java.util.ArrayList;

public class Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final ArrayList<ProducersWithEnergy> producers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void setState(ArrayList<ProducerChanges> changes,
                         ArrayList<WorkingDistributors> distributorsArrayList,
                         Integer month) {
        for (ProducerChanges p : changes) {
            producers.add(new ProducersWithEnergy(p.getId(), p.getEnergyPerDistributor()));
        }
        notifyAllObservers(distributorsArrayList, month);
    }

    public void notifyAllObservers(ArrayList<WorkingDistributors> distributorsArrayList, Integer month) {
        for (ProducersWithEnergy p : producers) {
            observers.get(p.getId()).update(p.getEnergy(), distributorsArrayList, month);
        }
    }
}
