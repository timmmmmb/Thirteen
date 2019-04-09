package main.java.ch.bfh.thirteen.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private WeightedRandomNumberGenerator wrng;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private int current_max = 6;
    private int current_min = 1;
    private int width;
    private int height;
    private int score = 0;
    private GameState gameState = GameState.UNINITIALIZED;
    private Field[][] positions;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        wrng = new WeightedRandomNumberGenerator(current_max - 1, current_min, new int[]{3, 3, 3, 2, 1});
        positions = new Field[width][height];
        initializeBoard();
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

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public void clickField(int x, int y) {
        if (gameState != GameState.RUNNING) {
            return;
        }
        Field f = getField(x, y);
        if (isClickable(f)) {
            removeNeighbors(f);
            incrementFieldValue(f, x, y);
            moveFields();
            if (isWon()) {
                setGameState(GameState.WON);
            } else if (isLost()) {
                setGameState(GameState.LOST);
            } else {
                setGameState(GameState.ANIMATING);
            }
            increaseScore();
            resetVisited();
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
                positions[x][y] = new Field(wrng.getNumber());
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
            if (isClickable(f, neighbor)) {
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
     *
     * @return true if there is atleast one field clickable
     */
    private boolean isLost() {
        for (int x = 0; x < width; x++) {
            for (Field f : positions[x]) {
                if (isClickable(f))
                    return false;
            }
        }
        return true;
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
        positions[x][y] = new Field(wrng.getNumber());
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

    private void increaseScore() {
        this.pcs.firePropertyChange("ScoreChanged", this.score, this.score + 1);
        score++;
    }

    private void resetVisited() {
        for (int x = 0; x < getWidth(); x++) {
            for (Field f : positions[x]) {
                f.setVisited(false);
            }
        }
    }

    private void setNewMax(int i) {
        if (i > current_max) {
            int oldmax = current_max;
            current_max = i;
            this.pcs.firePropertyChange("newMaxValue", oldmax, current_max);
        }
    }

    int getCurrent_max() {
        return current_max;
    }

}
