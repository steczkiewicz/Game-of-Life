package agh.ics.oop;

public class SimulationEngine implements Runnable {
    private final DarwinMap map;
    private final int renderDelay;

    public SimulationEngine(DarwinMap map, int renderDelay) {
        this.map = map;
        this.renderDelay = renderDelay;
    }

    public void run() {
        int i = 0;
        while (this.map.getAnimalsNumber() > 0) {
            try {
                System.out.println("Day: " + i + 1);
                map.removeDead();
                map.moveAnimals();
                map.eatGrass();
                map.breedAnimals();
                map.addNewGrass();
                map.changeEnergy();
                System.out.println("zwierzaki: " + map.animalsHashMap);
                System.out.println("roslinki: " + map.grassHashMap);
                i += 1;
                Thread.sleep(renderDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}