package observer;

import reading.ProducerChanges;
import reading.Producers;

import java.util.ArrayList;

public class Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private final ArrayList<ProducersWithEnergy> producers = new ArrayList<>();

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void setState(ArrayList<ProducerChanges> changes) {
        for (ProducerChanges p : changes) {
            producers.add(new ProducersWithEnergy(p.getId(), p.getEnergyPerDistributor()));
        }
        notifyAllObservers();
    }

    public void notifyAllObservers() {
        for (ProducersWithEnergy p : producers) {
            observers.get(p.getId()).update(p.getEnergy());
        }
    }
}
