package ch.bfh.thirteen.settings;

public class Settings {
    private static int fieldWidth = 64;
    private static int fieldHeight = 64;
    private static int boardHeight = 5;
    private static int boardWidth = 5;

    /**
     * this function loads all of the resources at the start of the game
     */
    public static void loadResources(){

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
