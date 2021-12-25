package agh.ics.oop;

public class GrassField extends AbstractWorldMap {
    private final int Boundary;

    public GrassField(int n, int boundary) {
        super();
        this.Boundary = boundary;
        for (int i = 0; i < n; i++) {
            placeGrass();
        }
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

    public void placeGrass() {
        for (int i = 0; i < 100; i++) {
            Vector2d randomPosition = new Vector2d( (int) (Math.random()*Boundary), (int) (Math.random()*Boundary));
            if (objectAt(randomPosition) == null) {
                Grass grass = new Grass(randomPosition);
                mapElements.put(randomPosition, grass);
//                mapBoundary.place(grass);
                return;
            }
        }
        throw new IllegalStateException("No free space for grass");
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