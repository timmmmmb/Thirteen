package ch.bfh.thirteen.model.test;

import ch.bfh.thirteen.model.main.Board;
import ch.bfh.thirteen.model.main.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private static int height = 5;
    private static int width = 6;
    private static Board gameBoard;

    @BeforeAll
    static void initializeTests() {
        gameBoard = new Board(width, height);
    }

    /**
     * tests if the height of the board is used propertly
     */
    @Test
    void testConstructorHeight() {
        assertEquals(gameBoard.getRows().get(0).size(), height);
        assertEquals(gameBoard.getHeight(), height);
    }

    /**
     * tests if the width of the board is used properly
     */
    @Test
    void testConstructorWidth() {
        assertEquals(gameBoard.getRows().size(), width);
        assertEquals(gameBoard.getWidth(), width);
    }

    /**
     * tests if the fields in the rows are all of the Type Field
     */
    @Test
    void testConstructorFields() {
        for (Vector<Field> column : gameBoard.getRows()) {
            for (Field field : column) {
                assertNotNull(field);
                assertTrue(field.getValue() > 0);
            }
        }

    }
}
