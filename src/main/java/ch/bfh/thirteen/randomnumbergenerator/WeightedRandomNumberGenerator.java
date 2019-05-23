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

    /**
     * constructor creates a new RNG
     * @param max the max of the rng
     * @param min the min value that gets generatet
     * @param p a double value to controll the distribution
     */
    public WeightedRandomNumberGenerator(int max, int min, double p) {
        this.lb = min;
        this.ub = max;
        this.w = ub - lb;
        this.p = p;
        generateKlist();
    }

    /**
     * generates the list with all of the probabilitys for the numbers
     */
    private void generateKlist() {
        for (int i = 0; i <= w; i++) {
            klist.add(calculateValue(i));
        }
    }

    /**
     * gets a random number
     * @return the new number
     */
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

    /**
     * increases the max value of the rng
     * if the max is > 9 also increases the min value
     */
    public void increaseMax() {
        ub++;
        if (ub >= 9) {
            lb++;
        }
        this.w = ub - lb;
        generateKlist();
    }

    /**
     * creates factorial of the input n 1*2*3*....*n = factorial
     * @param n the input which shall get generated until here
     * @return the factorial value
     */
    private double factorial(int n) {
        double res = 1;
        for (double i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * creates the binomialCoefficient
     * @param k the value to create a binomialCoefficient
     * @return the binomialCoefficient
     */
    private double binomialCoefficient(int k) {
        return factorial(w) / (factorial(k) * factorial(w - k));
    }

    /**
     * calculates the probability
     * @param k the value to get the probability from
     * @return the probability a double less than 1
     */
    private double calculateValue(int k) {
        return binomialCoefficient(k) * Math.pow(p, k) * Math.pow((1 - p), w - k);
    }
}
