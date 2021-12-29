package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public class DarwinMap {
    public HashMap<Vector2d, Grass> grassHashMap = new HashMap<>();
    public HashMap<Vector2d, ArrayList<Animal>> animalsHashMap = new HashMap<>();
    public LinkedList<Animal> animalsList = new LinkedList<>();
    public int width;
    public int height;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public double jungleRatio;
    public int startAnimals;
    public int startGrass;
    public int minEnergyToBreed;
    public Vector2d[] jungleCords;
    public int jungleHeight;
    public int jungleWidth;

    public DarwinMap(int width, int height, int startEnergy, int moveEnergy, int plantEnergy, double jungleRatio, int startAnimals, int startGrass, int minEnergyToBreed) {
        this.width = width;
        this.height = height;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
        this.startAnimals = startAnimals;
        this.startGrass = startGrass;
        this.minEnergyToBreed = minEnergyToBreed;
        calculateJungleCords();
        spawnGrass();
        spawnAnimals();
    }

    public void calculateJungleCords() {
        double jungleArea = this.width * this.height * this.jungleRatio;
        this.jungleWidth = (int) Math.sqrt(jungleArea);
        this.jungleHeight = (int) Math.sqrt(jungleArea);
        while (jungleWidth > this.width) {
            this.jungleWidth -= 1;
        }
        while (jungleHeight > this.height) {
            this.jungleHeight -= 1;
        }
        this.jungleCords = new Vector2d[]{new Vector2d(this.width - this.jungleWidth / 2, this.height - this.jungleHeight / 2), new Vector2d(this.width - this.jungleWidth / 2 + this.jungleWidth, this.height - jungleHeight / 2 + jungleHeight)};
    }

    public int getAnimalsNumber() {
        return (animalsList.size());
    }

    public ArrayList<Animal> findBestMates(ArrayList<Animal> animals) {
        ArrayList<Animal> parents = new ArrayList<>();
        Animal father = null;
        Animal mother = null;
        for (Animal animal : animals) {
            if (father == null && animal.getEnergy() > minEnergyToBreed) {
                father = animal;
            } else if (mother == null && animal.getEnergy() > minEnergyToBreed) {
                mother = animal;
            } else if (father != null && animal.getEnergy() > father.getEnergy()) {
                mother = father;
                father = animal;
            } else if (father != null && mother != null && animal.getEnergy() > mother.getEnergy()) {
                mother = animal;
            }
        }
        parents.add(father);
        parents.add(mother);
        return parents;
    }

    public void correctAnimalsHashMap() {
        ArrayList<Vector2d> keysToRemove = new ArrayList<>();
        for (Vector2d key : this.animalsHashMap.keySet()) {
            if (this.animalsHashMap.get(key).size() == 0) {
                keysToRemove.add(key);
            }
        }
        for (Vector2d key : keysToRemove) {
            this.animalsHashMap.remove(key);
        }
    }

    public void spawnGrass() {
        for (int i = 0; i < this.startGrass; i++) {
            for (int j = 0; j < 10; j++) {
                Vector2d grassPos = new Vector2d((int) (Math.random() * this.height), (int) (Math.random() * this.width));
                if (objectAt(grassPos) == null) {
                    Grass grass = new Grass(grassPos);
                    this.grassHashMap.put(grassPos, grass);
                    break;
                }
            }
        }
    }

    public void spawnAnimals() {
        for (int i = 0; i < this.startAnimals; i++) {
            for (int j = 0; j < 10; j++) {
                Vector2d animalPos = new Vector2d((int) (Math.random() * this.height), (int) (Math.random() * this.width));
                if (objectAt(animalPos) == null) {
                    Animal animal = new Animal(startEnergy, animalPos);
                    ArrayList<Animal> animals = new ArrayList<>();
                    this.animalsList.add(animal);
                    animals.add(animal);
                    this.animalsHashMap.put(animalPos, animals);
                    break;
                }
            }
        }
    }

    public void removeDead() {
        ArrayList<Animal> dead = new ArrayList<>();
        for (ArrayList<Animal> animals : this.animalsHashMap.values()) {
            for (Animal animal : animals) {
                if (animal.isDead()) {
                    dead.add(animal);
                }
            }
        }
        for (Animal animal : dead) {
            this.animalsList.remove(animal);
            this.animalsHashMap.get(animal.getPosition()).remove(animal);
        }
    }

//    public void addNewAnimal() {
//        while (true) {
//            Vector2d newAnimalPos = new Vector2d((int) (Math.random() * 20), (int) (Math.random() * 20));
//            if (objectAt(newAnimalPos) == null) {
//                Animal animal = new Animal(this, newAnimalPos);
//                animal.addObserver(this);
//                this.animalsHashMap.put(newAnimalPos, animal);
//                this.animalsList.push(animal);
//                return;
//            }
//        }
//    }


    public void moveAnimals() {
        for (Animal animal : this.animalsList) {
            Vector2d oldPosition = animal.getPosition();
            animal.rotate();
            if (canAnimalMoveThere(animal)) {
                animal.move(MoveDirection.FORWARD);
                Vector2d newPosition = animal.getPosition();
                this.animalsHashMap.get(oldPosition).remove(animal);
                if (this.animalsHashMap.get(newPosition) == null) {
                    ArrayList<Animal> animals = new ArrayList<>();
                    this.animalsHashMap.put(newPosition, animals);
                }
                this.animalsHashMap.get(newPosition).add(animal);
            }
        }
        correctAnimalsHashMap();
    }

    public boolean canAnimalMoveThere(Animal animal) {
        MapDirection orientation = animal.getOrientation();
        Vector2d position = animal.getPosition();
        Vector2d new_position = position.add(orientation.toUnitVector());
        return new_position.x >= 0 && new_position.y >= 0 && new_position.x < this.width && new_position.y < this.height;
    }

    public void eatGrass() {
        for (Animal animal : this.animalsList) {
            if (this.grassHashMap.get(animal.getPosition()) != null) {
                animal.energy += this.plantEnergy;
                this.grassHashMap.remove(animal.getPosition());
            }
        }
    }

    public void breedAnimals() {
        for (ArrayList<Animal> potentialMates : this.animalsHashMap.values()) {
            if (potentialMates.size() > 1) {
                ArrayList<Animal> parents = findBestMates(potentialMates);
                if (parents.get(0) == null || parents.get(1) == null) {
                    return;
                }
                Animal child = new Animal(parents.get(0).getEnergy() / 4 + parents.get(1).getEnergy() / 4, parents.get(0).getPosition());
                child.inheritGenes(parents.get(0), parents.get(1));
                parents.get(0).energy -= parents.get(0).energy / 4;
                parents.get(1).energy -= parents.get(1).energy / 4;
                this.animalsHashMap.get(child.getPosition()).add(child);
                this.animalsList.add(child);
            }
        }
    }

    public void addNewGrass() {
        for (int i = 0; i < 100; i++) {
            Vector2d jungleGrassPos = new Vector2d((int) (Math.random() * this.jungleWidth + this.jungleCords[0].x), (int) (Math.random() * this.jungleHeight + this.jungleCords[0].y));
            if (objectAt(jungleGrassPos) == null) {
                Grass grass = new Grass(jungleGrassPos);
                this.grassHashMap.put(jungleGrassPos, grass);
                break;
            }
        }
        for (int i = 0; i < 100; i++) {
            Vector2d normalGrassPos = new Vector2d((int) (Math.random() * width), (int) (Math.random() * height));
            if (objectAt(normalGrassPos) == null && normalGrassPos.x < jungleCords[0].x && normalGrassPos.x > jungleCords[1].x && normalGrassPos.y < jungleCords[0].y && normalGrassPos.y < jungleCords[1].y) {
                Grass grass = new Grass(normalGrassPos);
                this.grassHashMap.put(normalGrassPos, grass);
                break;
            }
        }
    }

    public void changeEnergy() {
        for (Animal animal : this.animalsList) {
            animal.energy -= moveEnergy;
        }
    }

    public Object objectAt(Vector2d position) {
        if (animalsHashMap.get(position) != null) {
            return animalsHashMap.get(position).get(0);
        }
        if (grassHashMap.get(position) != null) {
            return grassHashMap.get(position);
        }
        return null;
    }

//    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        animalsHashMap.put(newPosition, (Animal) objectAt(oldPosition));
//        animalsHashMap.remove(oldPosition);
//    }
}