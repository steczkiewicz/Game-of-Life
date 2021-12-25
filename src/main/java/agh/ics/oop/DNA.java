package agh.ics.oop;

import java.util.Arrays;
import java.util.Random;

public class DNA {
    public int[] genesArray;
    public DNA() {
        this.genesArray = new int[32];
        getRandomGenes();
        Arrays.sort(this.genesArray);
    }

    public void getRandomGenes() {
        Random rand = new Random();
        for (int i = 0; i < 32; i++) {
            this.genesArray[i] = rand.nextInt(8);
        }
    }

    public int returnRandomGene() {
        Random rand = new Random();
        return this.genesArray[rand.nextInt(32)];
    }
}
