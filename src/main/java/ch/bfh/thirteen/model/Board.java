package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.observer.CustomPropertyChangeSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

@XmlRootElement(namespace = "board")
public class Board {
    private WeightedRandomNumberGenerator wrng;
    private CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);
    @XmlElement(name = "max")
    private int current_max = 6;
    @XmlElement(name = "min")
    private int current_min = 1;
    @XmlElement(name = "width")
    private int width;
    @XmlElement(name = "height")
    private int height;
    private GameState gameState = GameState.UNINITIALIZED;
    @XmlElement(name = "positions")
    private Field[][] positions;

    private ArrayList<Field> clickableFields = new ArrayList<>();

    public Board() {
        this(5, 5);
    }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        wrng = new WeightedRandomNumberGenerator(current_max - 1, current_min, 0.3);
        positions = new Field[width][height];
        initializeBoard();
    }

    public Board(Board b) {
        this.current_min = b.current_min;
        this.current_max = b.current_max;
        this.gameState = GameState.RUNNING;
        this.width = b.width;
        this.height = b.height;
        wrng = b.wrng;
        positions = new Field[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                positions[x][y] = new Field(b.positions[x][y]);
            }
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    /**
     * used to get one field by coordinates
     *
     * @param x the x coordinate of the searched field
     * @param y the y coordinate of the searched field
     * @return the Field at the specified coordinates
     */
    public Field getField(int x, int y) {
        return positions[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    PropertyChangeSupport getPcs() {
        return pcs;
    }

    void clickField(int x, int y) {
        Field f = getField(x, y);
        clickField(f);
    }

    void clickField(Field f) {
        if (gameState != GameState.RUNNING) {
            return;
        }
        if (isClickable(f)) {
            removeNeighbors(f);
            incrementFieldValue(f, f.getCoordinate().getX(), f.getCoordinate().getY());
            moveFields();
            checkGamestate();
        }
    }

    public void finishAnimation() {
        if (gameState != GameState.ANIMATING) {
            return;
        }
        setGameState(GameState.RUNNING);
    }

    public String toSting() {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (positions[x][y] == null) {
                    result.append("NULL, ");
                } else {
                    result.append(positions[x][y].toString());
                    result.append(", ");
                }
            }
            result.replace(result.length() - 2, result.length(), "");
            result.append("\n");
        }
        return result.toString();
    }

    private void initializeBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                addField(x, y);
            }
        }
        // once add the maxnumber to the board
        Field f = positions[new Random().nextInt(getWidth() - 1)][getHeight() - 1];
        f.setValue(current_max);

        //if the game is not playable restart
        if (isLost()) {
            positions = new Field[width][height];
            initializeBoard();
        }
        setGameState(GameState.RUNNING);
    }

    /**
     * checks if two fields are clickable
     *
     * @param f the field that gets checkt
     * @return true if the field f can be clicked
     */
    private boolean isClickable(Field f) {
        for (Field neighbor : getNeighbors(f)) {
            if (f.getValue() == neighbor.getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isClickable(Field f1, Field f2) {
        return f1.getValue() == f2.getValue();
    }

    /**
     * this function is used to get all neighbores of a field
     *
     * @param f the field which neighbores you need
     * @return an arraylist with all of the neighbores
     */
    private ArrayList<Field> getNeighbors(Field f) {

        ArrayList<Field> neighbors = new ArrayList<>();
        outer:
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (positions[x][y] == f) {
                    if (y + 1 < positions[x].length)
                        neighbors.add(positions[x][y + 1]);
                    if (y > 0)
                        neighbors.add(positions[x][y - 1]);
                    if (x + 1 < positions.length)
                        neighbors.add(positions[x + 1][y]);
                    if (x > 0)
                        neighbors.add(positions[x - 1][y]);
                    break outer;
                }
            }
        }
        return neighbors;
    }

    /**
     * checks if the game is won
     *
     * @return true if the current max is 13 else false
     */
    private boolean isWon() {
        return current_max == 13;
    }

    /**
     * checks each field if it is clickable
     * also adds all of the clickable fields to the clickableField ArrayList
     *
     * @return true if there is atleast one field clickable
     */
    private boolean isLost() {
        clickableFields.clear();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isClickable(positions[x][y])) {
                    clickableFields.add(positions[x][y]);
                }
                resetVisited(x, y);
                positions[x][y].setCoordinate(new Coordinate(x, y));
            }
        }
        return clickableFields.isEmpty();
    }

    /**
     * removes the neighboring fields of f if they have the same value
     *
     * @param f the field whose neighbores shall get removed
     */
    private void removeNeighbors(Field f) {
        f.setVisited(true);
        for (Field neighbor : getNeighbors(f)) {
            if (!neighbor.isVisited() && !neighbor.getToBeRemoved() && isClickable(f, neighbor)) {
                neighbor.toBeRemoved();
                removeNeighbors(neighbor);
                if (neighbor.getToBeRemoved()) {
                    removeField(neighbor);
                }
            }
        }
    }

    /**
     * removes Field f from the board
     *
     * @param f the field who shall get removed
     */
    private void removeField(Field f) {
        FieldPosition fp = null;
        outer:
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (positions[x][y] == f) {
                    positions[x][y] = null;
                    fp = new FieldPosition(f, x, y);
                    break outer;
                }
            }
        }
        this.pcs.firePropertyChange("removedField", fp, null);
    }

    /**
     * moves the field f down until there are no more empty field below
     */
    private void moveFields() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = getHeight() - 2; y >= 0; y--) {
                if (positions[x][y] != null) {
                    int moveamount = 0;
                    for (int i = y; i < getHeight(); i++) {
                        if (positions[x][i] == null) {
                            moveamount++;
                        }
                    }
                    if (moveamount == 0) {
                        continue;
                    }
                    this.pcs.firePropertyChange("movedField", new FieldPosition(positions[x][y], x, y), moveamount);
                    positions[x][y + moveamount] = positions[x][y];
                    positions[x][y] = null;
                }
            }
        }
        addFields();
    }

    /**
     * adds new Fields while the rows are not full
     */
    private void addFields() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (positions[x][y] == null)
                    addField(x, y);
            }

        }
    }

    /**
     * adds one new field to the board at the top in the row x
     *
     * @param x the row where the new field shall get added
     */
    private void addField(int x, int y) {
        positions[x][y] = new Field(wrng.getNumber(), new Coordinate(x, y));
        this.pcs.firePropertyChange("addedField", new FieldPosition(positions[x][y], x, y), null);
    }

    private void setGameState(GameState gameState) {
        this.pcs.firePropertyChange("GameStateChange", this.gameState, gameState);
        this.gameState = gameState;

    }

    private void incrementFieldValue(Field f, int x, int y) {
        this.pcs.firePropertyChange("incrementedFieldValue", new FieldPosition(f, x, y), f.getValue() + 1);
        f.incrementValue();
        setNewMax(f.getValue());
    }

    private void resetVisited(int x, int y) {
        positions[x][y].setCoordinate(new Coordinate(x, y));
        positions[x][y].setVisited(false);
    }

    private void setNewMax(int i) {
        if (i > current_max) {
            //remove all tiles with the lowest number
            for (int x = 0; x < getWidth(); x++) {
                for (Field f : positions[x]) {
                    if (f == null) {
                        continue;
                    }
                    if (f.getValue() == this.current_min) {
                        removeField(f);
                    }
                }
            }

            int oldmax = current_max;
            current_max = i;
            this.pcs.firePropertyChange("newMaxValue", oldmax, current_max);
            wrng.increaseMax();
        }
    }

    public int getCurrent_max() {
        return current_max;
    }

    void removeSingleField(Field f) {
        removeField(f);
        moveFields();
        checkGamestate();
    }

    private void checkGamestate() {
        if (isWon()) {
            setGameState(GameState.WON);
            ThirteenApplication.getSettings().setHighscore();
        } else if (isLost()) {
            setGameState(GameState.LOST);
            ThirteenApplication.getSettings().setHighscore();
        } else {
            setGameState(GameState.ANIMATING);
        }
    }

    public ArrayList<Field> getClickableFields() {
        return clickableFields;
    }


}
