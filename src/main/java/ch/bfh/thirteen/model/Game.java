package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.settings.Settings;
import main.java.ch.bfh.thirteen.stack.SizedStack;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "game")
public class Game {
    private SizedStack<Board> history = new SizedStack<>(10);
    private Board gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());

    public void restartGame() {
        history.clear();
        gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());
    }

    public Board getBoard() {
        return gameBoard;
    }

    public void removeField(FieldLabel fl) {
        history.push(new Board(gameBoard));
        gameBoard.removeSingleField(getFieldFromFieldLabel(fl));
    }

    public void clickField(FieldLabel fl) {
        history.push(new Board(gameBoard));
        int x = (int) fl.getBoundsInParent().getMinX() / Settings.getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / Settings.getFieldHeight();
        gameBoard.clickField(x, y);
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        gameBoard = history.pop();
    }

    private Field getFieldFromFieldLabel(FieldLabel fl) {
        //get coordinates
        int x = (int) fl.getBoundsInParent().getMinX() / Settings.getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / Settings.getFieldHeight();
        return gameBoard.getField(x, y);
    }
}
