package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.settings.Settings;

public class Game {
    private static Board gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());

    public static void restartGame() {
        gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());
    }

    public static Board getBoard() {
        return gameBoard;
    }
}
