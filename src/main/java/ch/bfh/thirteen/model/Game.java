package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.stack.SizedStack;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@XmlRootElement(name = "game")
public class Game implements PropertyChangeListener {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    @XmlElement(name = "history")
    private SizedStack<Board> history = new SizedStack<>(10);
    @XmlElement(name = "board")
    private Board gameBoard;

    private int bombcost = 50;
    private int undocost = 50;
    private final int BOMBINCREMENTCOST = 50;
    private final int UNDOINCREMENTCOST = 50;

    public Game(){
        restartGame();
    }

    public void restartGame() {
        history.clear();
        setGameBoard(new Board(ThirteenApplication.getSettings().getBoardWidth(), ThirteenApplication.getSettings().getBoardHeight()));
    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public Board getBoard() {
        return gameBoard;
    }

    public void removeField(FieldLabel fl) {
        addHistory();
        gameBoard.removeSingleField(getFieldFromFieldLabel(fl));
    }

    public void clickField(FieldLabel fl) {
        addHistory();
        int x = (int) fl.getBoundsInParent().getMinX() / ThirteenApplication.getSettings().getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / ThirteenApplication.getSettings().getFieldHeight();
        gameBoard.clickField(x, y);
    }

    public void undo() {
        if (history.isEmpty()) {
            return;
        }
        setGameBoard(history.pop());
    }

    private void setGameBoard(Board b){
        if(gameBoard!=null){
            gameBoard.getPcs().removePropertyChangeListener(this);
        }
        gameBoard = b;
        addPCL();
    }

    public void addPCL(){
        gameBoard.getPcs().addPropertyChangeListener(this);
    }

    private void addHistory(){
        history.push(new Board(gameBoard));
    }

    private Field getFieldFromFieldLabel(FieldLabel fl) {
        //get coordinates
        int x = (int) fl.getBoundsInParent().getMinX() / ThirteenApplication.getSettings().getFieldWidth();
        int y = (int) fl.getBoundsInParent().getMinY() / ThirteenApplication.getSettings().getFieldHeight();
        return gameBoard.getField(x, y);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.pcs.firePropertyChange(evt);
    }
}
