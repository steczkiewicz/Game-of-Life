package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DarwinMap implements IPositionChangeObserver {
    private final int Boundary = 20;
    public HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    public HashMap<Vector2d, Animal> animalsHashMap = new HashMap<>();
    public LinkedList<Animal> animalsList = new LinkedList<>();
    public LinkedList<Grass> grassList = new LinkedList<>();

    public DarwinMap() {
//        this.Boundary = boundary;
        for (int i = 0; i < 10; i++) {
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
            Vector2d newAnimalPos = new Vector2d((int) (Math.random() * 20), (int) (Math.random() * 20));
            if (objectAt(newAnimalPos) == null) {
                Animal animal = new Animal(this, newAnimalPos);
                animal.addObserver(this);
                this.animalsHashMap.put(newAnimalPos, animal);
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

//    public void breedAnimals() {
//        for (Vector2d field : animalsHashMap.keySet()) {
//            ArrayList<Animal> animals = new ArrayList<>();
//            animals.add(animalsHashMap.get(field));
//            if (animals.size() > 1);
//        }
//
//    }

    public void addNewGrass() {
        for (int i = 0; i < 100; i++) {
            Vector2d jungleGrassPos = new Vector2d((int) (Math.random() * 20), (int) (Math.random() * 20));
            if (objectAt(jungleGrassPos) == null) {
                Grass grass = new Grass(jungleGrassPos);
                this.grassHashMap.put(jungleGrassPos, grass);
                this.grassList.push(grass);
                break;
            }
        }
        for (int i = 0; i < 100; i++) {
            Vector2d normalGrassPos = new Vector2d((int) (Math.random() * 20), (int) (Math.random() * 20));
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

    public Object objectAt(Vector2d position) {
        if (animalsHashMap.get(position) != null) {
            return animalsHashMap.get(position);
        }
        if (grassHashMap.get(position) != null) {
            return grassHashMap.get(position);
        }
        return null;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        animalsHashMap.put(newPosition, (Animal) objectAt(oldPosition));
        animalsHashMap.remove(oldPosition);
    }
}