package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.fieldlabel.FieldLabel;
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
    /**
     * class to control the game on the board
     */
    private final CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);
    @XmlElement(name = "history")
    private final SizedStack<Board> history = new SizedStack<>(10);
    @XmlElement(name = "board")
    private Board gameBoard;
    @XmlElement(name = "timer")
    private final Timer timer = new Timer();
    @XmlElement(name = "moves")
    private int moves = 0;
    @XmlElement(name = "bombcost")
    private int bombcost = 50;
    @XmlElement(name = "undocost")
    private int undocost = 50;

    /**
     * constructor for the game that sets up the game board
     */
    public Game() {
        setGameBoard(new Board(getSettings().getBoardWidth(), getSettings().getBoardHeight()));
    }

    /**
     * restarts the game with a new game board, timer and moves
     */
    public void restartGame() {
        history.clear();
        setGameBoard(new Board(getSettings().getBoardWidth(), getSettings().getBoardHeight()));
        timer.restart();
        timer.play();
        moves = 0;
    }

    /**
     * gets the property change support
     *
     * @return property change support
     */
    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    /**
     * returns the current game board
     *
     * @return game board
     */
    public Board getBoard() {
        return gameBoard;
    }

    /**
     * removes a given field
     * gets the cost for using the removal bomb
     *
     * @param fl field to remove
     */
    public void removeField(FieldLabel fl) {
        addHistory();
        bombcost += getSettings().getBOMBINCREMENTCOST();
        gameBoard.removeSingleField(getFieldFromFieldLabel(fl));
        moves++;
    }

    /**
     * this function is called by a player to click a field
     *
     * @param fl the field label that was clicked
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

    /**
     * undoes the last move at a cost of stars
     */
    public void undo() {
        if (history.isEmpty() || getSettings().decreaseStars(undocost)) {
            return;
        }
        undocost += getSettings().getUNDOINCREMENTCOST();
        setGameBoard(history.pop());
        moves++;
    }

    /**
     * sets up a game board from a given game board
     *
     * @param b board to set
     */
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

    /**
     * adds the history of moves
     */
    private void addHistory() {
        history.push(new Board(gameBoard));
    }

    /**
     * gets coordinates of field from field label
     *
     * @param fl field  label to get coordinates from
     * @return field of x and y coordinates
     */
    private Field getFieldFromFieldLabel(FieldLabel fl) {
        //get coordinates
        int x = (int) fl.getBoundsInParent().getMinX() / getSettings().getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / getSettings().getFieldHeight();
        return gameBoard.getField(x, y);
    }

    /**
     * property change with the property change event
     *
     * @param evt property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.pcs.firePropertyChange(evt);
    }

    /**
     * gets the time from timer
     *
     * @return time
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * gets number of moves
     *
     * @return number of moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * to string method for the game
     *
     * @return String with board information
     */
    public String toString() {
        return getBoard().toSting();
    }

    /**
     * gets the cost for the bomb
     *
     * @return bomb cost
     */
    public int getBombcost() {
        return bombcost;
    }

    /**
     * gets the undo cost
     *
     * @return undo cost
     */
    public int getUndocost() {
        return undocost;
    }

    /**
     * checks if the game has a history of moves
     *
     * @return game history
     */
    public boolean hasHistory() {
        return !history.isEmpty();
    }
}
