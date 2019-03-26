package test.java.ch.bfh.model.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import main.java.ch.bfh.thirteen.model.Board;
import main.java.ch.bfh.thirteen.model.Field;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(gameBoard.getRows().get(0).size(), height+1);
        assertEquals(gameBoard.getHeight(), height);
    }

    /**
     * tests if the width of the board is used properly
     */
    @Test
    void testConstructorWidth() {
        assertEquals(gameBoard.getRows().size(), width+1);
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

    /**
     * this test tests if the function returns the correct amount of fields
     */
    @Test
    void testGetNeighbores() {
        try {
            Field f = gameBoard.getField(0,0);
            assertEquals(2, gameBoard.getNeighbores(f).size());
            f = gameBoard.getField(1,0);
            assertEquals(3, gameBoard.getNeighbores(f).size());
            f = gameBoard.getField(1,1);
            assertEquals(4, gameBoard.getNeighbores(f).size());

            f = gameBoard.getField(width,height);
            assertEquals(2, gameBoard.getNeighbores(f).size());
            f = gameBoard.getField(width-1,height);
            assertEquals(3, gameBoard.getNeighbores(f).size());
            f = gameBoard.getField(width,height-1);
            assertEquals(3, gameBoard.getNeighbores(f).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
