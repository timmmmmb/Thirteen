package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.observer.CustomPropertyChangeSupport;
import main.java.ch.bfh.thirteen.stack.SizedStack;
import main.java.ch.bfh.thirteen.timer.Timer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static main.java.ch.bfh.thirteen.application.ThirteenApplication.getSettings;

@XmlRootElement(name = "game")
public class Game implements PropertyChangeListener {
    private CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);
    @XmlElement(name = "history")
    private SizedStack<Board> history = new SizedStack<>(10);
    @XmlElement(name = "board")
    private Board gameBoard;
    @XmlElement(name = "timer")
    private Timer timer = new Timer();
    @XmlElement(name = "moves")
    private int moves = 0;
    @XmlElement(name = "bombcost")
    private int bombcost = 50;
    @XmlElement(name = "undocost")
    private int undocost = 50;

    public Game() {
        setGameBoard(new Board(getSettings().getBoardWidth(), getSettings().getBoardHeight()));
    }

    public void restartGame() {
        history.clear();
        setGameBoard(new Board(getSettings().getBoardWidth(), getSettings().getBoardHeight()));
        timer.restart();
        timer.play();
        moves = 0;
    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public Board getBoard() {
        return gameBoard;
    }

    public void removeField(FieldLabel fl) {
        addHistory();
        bombcost += getSettings().getBOMBINCREMENTCOST();
        gameBoard.removeSingleField(getFieldFromFieldLabel(fl));
        moves++;
    }

    /**
     * this function is called by a player to click a field
     *
     * @param fl the fieldlabel that was clicked
     */
    public void clickField(FieldLabel fl) {
        addHistory();
        int x = (int) fl.getBoundsInParent().getMinX() / getSettings().getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / getSettings().getFieldHeight();
        gameBoard.clickField(x, y);
        moves++;
        getSettings().increaseStars(1);
    }

    /**
     * this function is only used by the bots
     *
     * @param f the field that was clicked
     */
    public void clickField(Field f) {
        addHistory();
        gameBoard.clickField(f);
        moves++;
    }

    public void undo() {
        if (history.isEmpty() || getSettings().decreaseStars(undocost)) {
            return;
        }
        undocost += getSettings().getUNDOINCREMENTCOST();
        setGameBoard(history.pop());
        moves++;
    }

    private void setGameBoard(Board b) {
        if (gameBoard != null) {
            gameBoard.getPcs().removePropertyChangeListener(this);
        }
        gameBoard = b;
        addPCL();
    }

    /**
     * this function adds all of the necessary pcl when being created or loaded
     */
    public void addPCL() {
        getTimer().getPcs().addPropertyChangeListener(this);
        gameBoard.getPcs().addPropertyChangeListener(this);
        ThirteenApplication.getSettings().getPcs().addPropertyChangeListener(this);
    }

    private void addHistory() {
        history.push(new Board(gameBoard));
    }

    private Field getFieldFromFieldLabel(FieldLabel fl) {
        //get coordinates
        int x = (int) fl.getBoundsInParent().getMinX() / getSettings().getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / getSettings().getFieldHeight();
        return gameBoard.getField(x, y);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.pcs.firePropertyChange(evt);
    }

    public Timer getTimer() {
        return timer;
    }

    public int getMoves() {
        return moves;
    }

    public String toString() {
        return getBoard().toSting();
    }

    public int getBombcost() {
        return bombcost;
    }

    public int getUndocost() {
        return undocost;
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }
}
