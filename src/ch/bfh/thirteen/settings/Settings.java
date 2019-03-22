package ch.bfh.thirteen.settings;

public class Settings {
    private static final int fieldWidth = 64;
    private static final int fieldHeight = 64;
    private static final int boardHeight = 5;
    private static final int boardWidth = 5;

    /**
     * this function loads all of the resources at the start of the game
     */
    public static void loadResources() {

    }

    public static int getFieldWidth() {
        return fieldWidth;
    }

    public static int getFieldHeight() {
        return fieldHeight;
    }

    public static int getBoardHeight() {
        return boardHeight;
    }

    public static int getBoardWidth() {
        return boardWidth;
    }
}
