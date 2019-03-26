package test.java.ch.bfh.model.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.ch.bfh.thirteen.model.WeightedRandomNumberGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightedRandomNumberGeneratorTest {
    private static int min = 1;
    private static int max = 6;
    private static int[] weight = {1, 2, 2, 3, 3, 1};
    private static WeightedRandomNumberGenerator wrng;

    @BeforeAll
    static void initializeTests() {
        wrng = new WeightedRandomNumberGenerator(max, min, weight);
    }

    /**
     * tests if the numbers are in the given range
     */
    @Test
    void numbers() {
        for (int i = 0; i < 1000; i++) {
            int number = wrng.getNumber();
            assertTrue(number >= min);
            assertTrue(number <= max);
        }
    }
}
