package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine, Runnable {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<IAnimalObserver> observers = new ArrayList<>();
    private DarwinMap map = new DarwinMap();

    public SimulationEngine(IWorldMap map, Vector2d[] startingPositions) {
        for (Vector2d pos : startingPositions) {
            Animal animal = new Animal(map, pos);
            if (map.place(animal)) {
                animals.add(animal);
            }
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPositions) {
        this(map, startingPositions);
//        this.moves = moves;
    }

//    public void setMoves(MoveDirection[] moves) {
//        this.moves = moves;
//    }

    public void addObserver(IAnimalObserver observer) {
        this.observers.add(observer);
    }

    private void updateObservers() {
        for (IAnimalObserver observer : this.observers) {
            observer.update();
        }
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            map.removeDead();
            map.moveAnimals();
            map.eatGrass();
            map.addNewGrass();
            map.changeEnergy();
            System.out.println("zwierzaki: " + map.animalsList);
            System.out.println("roslinki: " + map.grassList);
            updateObservers();
        }
    }
}