package main.java.ch.bfh.thirteen.model.main;

import java.util.ArrayList;

/**
 * this class creates reandom numbers but it uses a weightingSystem so it uses some numbers more than others
 */
public class WeightedRandomNumberGenerator {
    private int min, max;
    private int[] weight;
    private ArrayList<Integer> possibilitys = new ArrayList<>();

    public WeightedRandomNumberGenerator(int max, int min, int[] weight) throws IllegalArgumentException {
        if (max - min + 1 != weight.length) {
            throw new IllegalArgumentException("weight array doesnt have correct size expected size: " + (max - min + 1) + " recieved size " + weight.length);
        }
        this.min = min;
        this.max = max;
        this.weight = weight;
        createList();
    }

    private void createList() {
        possibilitys.clear();
        for (int i = min; i <= max; i++) {
            for (int j = 0; j < weight[i - min]; j++) {
                possibilitys.add(i);
            }
        }
    }

    public int getNumber() {
        double position = (Math.random() * (possibilitys.size()));
        return possibilitys.get((int) position);
    }
}
