package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;

public class DarwinMap extends AbstractWorldMap {
    private final int Boundary = 100;
    public HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    public HashMap<Vector2d, Animal> animalsHashMap = new HashMap<>();
    public LinkedList<Animal> animalsList = new LinkedList<>();
    public LinkedList<Grass> grassList = new LinkedList<>();

    public DarwinMap() {
//        this.Boundary = boundary;
        for (int i=0;i<5;i++) {
            addNewAnimal();
            addNewGrass();
        }
    }

    public void removeDead() {
        for (int i = 0; i < this.animalsList.size(); i++) {
            Animal animal = this.animalsList.get(i);
            if (animal.isDead()) {
                //nw czy tu cos jeszcze nie trzeba dodac xd
                this.animalsList.remove(animal);
                this.animalsHashMap.remove(animal.getPosition());
            }
        }
    }

    public void addNewAnimal() {
        while (true) {
            Vector2d newAnimalPos = new Vector2d((int) (Math.random() * 100), (int) (Math.random() * 30));
            if (objectAt(newAnimalPos) == null) {
                Animal animal = new Animal(this,newAnimalPos);
                place(animal);
                this.animalsHashMap.put(newAnimalPos,animal);
                this.animalsList.push(animal);
                return;
            }
        }
    }

    public void moveAnimals() {
        for (Animal animal : this.animalsList) {
            animal.rotate();
            animal.move(MoveDirection.FORWARD);
        }
    }

    public void eatGrass() {
        for (int i = 0; i < this.grassList.size(); i++) {
            Grass grass = this.grassList.get(i);
            Animal animal = this.animalsHashMap.get(grass.getPosition());
            if (animal != null) {
                animal.energy += 5;
                this.grassList.remove(grass);
                this.grassHashMap.remove(grass.getPosition());
            }
        }
    }

//    narazie sie nie beda rozmnazac essa?
//    public void breedAnimals() {
//        for (Vector2d field : animals.keySet()) {
//
//        }
//    }

    public void addNewGrass() {
        while (true) {
            Vector2d jungleGrassPos = new Vector2d((int) (Math.random() * 10) + 45, (int) (Math.random() * 10) + 10);
            if (objectAt(jungleGrassPos) == null) {
                Grass grass = new Grass(jungleGrassPos);
                this.grassHashMap.put(jungleGrassPos, grass);
                this.grassList.push(grass);
                break;
            }
        }
        while (true) {
            Vector2d normalGrassPos = new Vector2d((int) (Math.random() * 100), (int) (Math.random() * 30));
            if (objectAt(normalGrassPos) == null) {
                Grass grass = new Grass(normalGrassPos);
                this.grassHashMap.put(normalGrassPos, grass);
                this.grassList.push(grass);
                break;
            }
        }
    }

    public void changeEnergy() {
        for (Animal animal : this.animalsList) {
            animal.energy -= 1;
        }
    }

    public void placeGrass() {
        for (int i = 0; i < 100; i++) {
            Vector2d randomPosition = new Vector2d((int) (Math.random() * Boundary), (int) (Math.random() * Boundary));
            if (objectAt(randomPosition) == null) {
                Grass grass = new Grass(randomPosition);
                mapElements.put(randomPosition, grass);
                return;
            }
        }
        throw new IllegalStateException("No free space for grass");
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        Vector2d position = animal.getPosition();
        if (objectAt(position) instanceof Grass) {
            placeGrass();
        } else if (objectAt(position) instanceof Animal) {
            throw new IllegalArgumentException("Cannot place animal at" + position);
        }

        mapElements.put(position, animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (objectAt(oldPosition) instanceof Animal && objectAt(newPosition) instanceof Grass) {
            placeGrass();
        }
        mapElements.put(newPosition, (IMapElement) objectAt(oldPosition));
        mapElements.remove(oldPosition);
    }
}
