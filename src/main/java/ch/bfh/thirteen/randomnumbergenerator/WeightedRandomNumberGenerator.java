package main.java.ch.bfh.thirteen.randomnumbergenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 * this class creates reandom numbers but it uses a weightingSystem so it uses some numbers more than others
 */
public class WeightedRandomNumberGenerator {
    private int lb, ub;
    private double p;
    private int w;
    private ArrayList<Double> klist = new ArrayList<>();

    public WeightedRandomNumberGenerator(int max, int min, double p) throws IllegalArgumentException {
        this.lb = min;
        this.ub = max;
        this.w = ub - lb;
        this.p = p;
        generateKlist();
    }

    private void generateKlist() {
        for (int i = 0; i <= w; i++) {
            klist.add(calculateValue(i));
        }
    }

    public int getNumber() {
        double r = new Random().nextDouble();
        double sum = 0.0;
        for (int i = 0; i <= ub - lb; i++) {
            sum += klist.get(i);
            if (r < sum) {
                return i + lb;
            }
        }
        return lb;
    }

    public void increaseMax() {
        ub++;
        if (ub >= 9) {
            lb++;
        }
        this.w = ub - lb;
        lb = Math.max(1, ub - w);
        generateKlist();
    }

    private double factorial(int n) {
        double res = 1;
        for (double i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private double binomialCoefficient(int k) {
        return factorial(w) / (factorial(k) * factorial(w - k));
    }

    private double calculateValue(int k) {
        return binomialCoefficient(k) * Math.pow(p, k) * Math.pow((1 - p), w - k);
    }
}
