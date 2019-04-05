package test.java.ch.bfh.model.test;

import main.java.ch.bfh.thirteen.model.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {
    private static int value = 1;
    private static int x = 5;
    private static int y = 6;
    private static Field field;

    @BeforeAll
    static void initializeTests() {
        field = new Field(value);
    }

    /**
     * tests if the field value is set correctly
     */
    @Test
    void testConstructorValue() {
        assertEquals(field.getValue(), value);
    }
}
