package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Animal implements IMapElement{
    public int energy;
    public DNA DNA;
    private MapDirection ori;
    private Vector2d pos;
    private final IWorldMap map;
    private final ArrayList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.energy = 20;
        this.map = map;
        this.ori = MapDirection.NORTH;
        this.pos = initialPosition;
        this.observers = new ArrayList<>();
        this.DNA = new DNA();
    }

    public boolean isDead() {
        return this.energy <= 0;
    }

    public int getEnergy() {return energy;}

    public MapDirection getOrientation() { return ori; }

    @Override
    public Vector2d getPosition() { return pos; }

    @Override
    public Image getImage() throws FileNotFoundException {
        return new Image(new FileInputStream("src/main/resources/" +
                switch (this.ori) {
                    case NORTH -> "up.png";
                    case NORTHEAST -> "northeast.png";
                    case EAST -> "right.png";
                    case SOUTHEAST -> "southeast.png";
                    case SOUTH -> "down.png";
                    case SOUTHWEST -> "southwest.png";
                    case WEST -> "left.png";
                    case NORTHWEST -> "northwest.png";
                }));
    }

    public void move(MoveDirection direction){
        if (this.energy > 0) {
            switch (direction) {
                case LEFT -> this.ori = this.ori.previous();
                case RIGHT -> this.ori = this.ori.next();
                case FORWARD -> {
                    Vector2d new_pos = this.pos.add(this.ori.toUnitVector());
                    if (this.map.canMoveTo(new_pos)) {
                        this.positionChanged(this.pos, new_pos);
                        this.pos = new_pos;
                    }
                }
                case BACKWARD -> {
                    Vector2d new_pos = this.pos.subtract(this.ori.toUnitVector());
                    if (this.map.canMoveTo(new_pos)) {
                        this.positionChanged(this.pos, new_pos);
                        this.pos = new_pos;
                    }
                }
            }
            this.energy -= 1;
        }
    }

    public void rotate() {
        int n = this.DNA.returnRandomGene();
        for (int i=0; i<n; i++){
            this.ori = this.ori.next();
        }
    }

    public void inheritGenes(Animal parent1, Animal parent2) {
        int esum = parent1.energy + parent2.energy;
        if (parent1.energy / esum * 32 >= 0)
            System.arraycopy(parent1.DNA.genesArray, 0, this.DNA.genesArray, 0, parent1.energy / esum * 32);
        if (32 - parent1.energy / esum * 32 >= 0)
            System.arraycopy(parent2.DNA.genesArray, parent1.energy / esum * 32, this.DNA.genesArray, parent1.energy / esum * 32, 32 - parent1.energy / esum * 32);
    }

    public String toString() {
        return this.getPosition().toString();
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }
    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}