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
        assertEquals(gameBoard.getHeight(), height);
    }

    /**
     * tests if the width of the board is used properly
     */
    @Test
    void testConstructorWidth() {
        assertEquals(gameBoard.getWidth(), width);
    }

}
