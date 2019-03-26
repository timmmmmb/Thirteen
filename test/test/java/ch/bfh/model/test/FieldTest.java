package test.java.ch.bfh.model.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.ch.bfh.thirteen.model.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {
    private static int value = 1;
    private static int x = 5;
    private static int y = 6;
    private static Field field;

    @BeforeAll
    static void initializeTests() {
        field = new Field(x, y, value);
    }

    /**
     * tests if the field x is set correctly
     */
    @Test
    void testConstructorX() {
        assertEquals(field.getX(), x);
    }

    /**
     * tests if the field y is set correctly
     */
    @Test
    void testConstructorY() {
        assertEquals(field.getY(), y);
    }

    /**
     * tests if the field value is set correctly
     */
    @Test
    void testConstructorValue() {
        assertEquals(field.getValue(), value);
    }
}
