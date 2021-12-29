package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Animal implements IMapElement {
    public int energy;
    public DNA dna;
    private MapDirection ori;
    private Vector2d pos;

    public Animal(int startingEnergy, Vector2d initialPosition) {
        this.energy = startingEnergy;
        this.ori = MapDirection.NORTH;
        this.pos = initialPosition;
        this.dna = new DNA();
        this.rotate();
    }

    public boolean isDead() {
        return this.energy <= 0;
    }

    public int getEnergy() {
        return energy;
    }

    public MapDirection getOrientation() {
        return ori;
    }

    @Override
    public Vector2d getPosition() {
        return pos;
    }

    @Override
    public Image getImage() throws FileNotFoundException {
        return new Image(new FileInputStream("src/main/resources/animal.png"));
    }

    public void move(MoveDirection direction) {
        if (this.energy > 0) {
            switch (direction) {
                case LEFT -> this.ori = this.ori.previous();
                case RIGHT -> this.ori = this.ori.next();
                case FORWARD -> {
                    this.pos = this.pos.add(this.ori.toUnitVector());
                }
                case BACKWARD -> {
                    this.pos = this.pos.subtract(this.ori.toUnitVector());
                }
            }
        }
    }

    public void rotate() {
        int n = this.dna.returnRandomGene();
        for (int i = 0; i < n; i++) {
            this.ori = this.ori.next();
        }
    }

    public void inheritGenes(Animal parent1, Animal parent2) {
        int esum = parent1.energy + parent2.energy;
        if (parent1.energy / esum * 32 >= 0)
            System.arraycopy(parent1.dna.genesArray, 0, this.dna.genesArray, 0, parent1.energy / esum * 32);
        if (32 - parent1.energy / esum * 32 >= 0)
            System.arraycopy(parent2.dna.genesArray, parent1.energy / esum * 32, this.dna.genesArray, parent1.energy / esum * 32, 32 - parent1.energy / esum * 32);
    }

    public String toString() {
        return this.getPosition().toString() + ' ' + this.energy;
    }

}