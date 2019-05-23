package test.java.ch.bfh.randomnumbergenerator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.ch.bfh.thirteen.randomnumbergenerator.WeightedRandomNumberGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeightedRandomNumberGeneratorTest {
    private static int min = 1;
    private static int max = 6;
    private static WeightedRandomNumberGenerator wrng;

    @BeforeAll
    static void initializeTests() {
        wrng = new WeightedRandomNumberGenerator(max, min,0.3);
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

    /**
     * tests if the increaseMax works and the numbers are in the given range
     */
    @Test
    void increaseMax() {
        for(int j = 0; j < 10; j++){
            wrng.increaseMax();
            max++;
            if (max >= 9) {
                min++;
            }
            for (int i = 0; i < 1000; i++) {
                int number = wrng.getNumber();
                assertTrue(number >= min);
                assertTrue(number <= max);
            }
        }

    }
}
