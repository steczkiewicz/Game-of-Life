package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements Runnable {
    private final ArrayList<IAnimalObserver> observers = new ArrayList<>();
    private DarwinMap map = new DarwinMap();

    public void addObserver(IAnimalObserver observer) {
        this.observers.add(observer);
    }

    private void updateObservers() {
        for (IAnimalObserver observer : this.observers) {
            observer.update();
        }
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            map.removeDead();
            map.moveAnimals();
            map.eatGrass();
//            map.breedAnimals();
            map.addNewGrass();
            map.changeEnergy();
            System.out.println("zwierzaki: " + map.animalsHashMap);
            System.out.println("roslinki: " + map.grassHashMap);
            updateObservers();
        }
    }
}