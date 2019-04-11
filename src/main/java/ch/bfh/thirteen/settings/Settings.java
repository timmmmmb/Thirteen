package main.java.ch.bfh.thirteen.settings;

import javafx.scene.image.Image;
import main.java.ch.bfh.thirteen.model.Board;

public class Settings {

    private static Board gameBoard;
    private static final int fieldWidth = 64;
    private static final int fieldHeight = 64;
    private static final int boardHeight = 5;
    private static final int boardWidth = 5;
    private static Image gameIcon;

    /**
     * this function loads all of the resources at the start of the game
     */
    public static void loadResources() {
        gameIcon = new Image("images/13_logo.png");
    }

    public static int getFieldWidth() {
        return fieldWidth;
    }

    public static int getFieldHeight() {
        return fieldHeight;
    }

    private static int getBoardHeight() {
        return boardHeight;
    }

    private static int getBoardWidth() {
        return boardWidth;
    }

    public static Image getGameIcon() {
        return gameIcon;
    }

    public static void initializeBoard() {
        gameBoard = new Board(getBoardWidth(), getBoardHeight());
    }

    public static Board getBoard() {
        return gameBoard;
    }
}
