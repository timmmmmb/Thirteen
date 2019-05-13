package test.java.ch.bfh.model.test;

import main.java.ch.bfh.thirteen.model.Coordinate;
import main.java.ch.bfh.thirteen.model.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {
    private static int value = 1;
    private static Field field;

    @BeforeAll
    static void initializeTests() {
        field = new Field(value,new Coordinate(0,0));
    }

    /**
     * tests if the field value is set correctly
     */
    @Test
    void testConstructorValue() {
        assertEquals(field.getValue(), value);
    }
}
