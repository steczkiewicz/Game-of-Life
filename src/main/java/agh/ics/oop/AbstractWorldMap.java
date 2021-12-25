package agh.ics.oop;


import java.util.HashMap;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final HashMap<Vector2d, IMapElement> mapElements;

    public AbstractWorldMap() {
        mapElements = new HashMap<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) throws IllegalArgumentException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            mapElements.put(position, animal);
            animal.addObserver(this);
            return true;
        } else throw new IllegalArgumentException("Cannot place animal at" + position);
    }

    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        mapElements.put(newPosition, (IMapElement) objectAt(oldPosition));
        mapElements.remove(oldPosition);
    }
}