package main.java.ch.bfh.thirteen.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Board {
    private WeightedRandomNumberGenerator wrng;
    private int current_max = 6;
    private int current_min = 1;
    private int width;
    private int height;
    private GameState gameState = GameState.UNINITIALIZED;
    private ArrayList<Vector<Field>> rows = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        wrng = new WeightedRandomNumberGenerator(current_max - 1, current_min, new int[]{3, 3, 3, 2, 1});
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < width; i++) {
            rows.add(new Vector<>());
        }
        int x = 0;
        for (Vector<Field> row : rows) {
            for (int i = 0; i < height; i++) {
                row.add(new Field(x, i, wrng.getNumber(),this));
            }
            x++;
        }

        // once add the maxnumber to the board
        try {
            Field f = getField(new Random().nextInt(getWidth()-1),getHeight()-1);
            f.setValue(current_max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if two fields are clickable
     * @param f the field that gets checkt
     * @return true if the field f can be clicked
     * @throws Exception if one of the neighbores is not in the board
     */
    public boolean isClickable(Field f) throws Exception {
        for(Field neighbor:getNeighbores(f)){
            if(isClickable(f,neighbor)){
                return true;
            }
        }
        return false;
    }

    private boolean isClickable(Field f1, Field f2){
        return f1.getValue() == f2.getValue();
    }

    /**
     * this function is used to get all neighbores of a field
     *
     * @param f the field which neighbores you need
     * @return an arraylist with all of the neighbores
     */
    public ArrayList<Field> getNeighbores(Field f) {
        int x = f.getX();
        int y = f.getY();
        ArrayList<Field> neighbores = new ArrayList<>();
        try {
            if (y < getHeight())
                neighbores.add(getField(x, y + 1));
            if (y > 0)
                neighbores.add(getField(x, y - 1));
            if (x < getWidth())
                neighbores.add(getField(x + 1, y));
            if (x > 0)
                neighbores.add(getField(x - 1, y));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return neighbores;
    }

    /**
     * used to get one field by coordinates
     *
     * @param x the x coordinate of the searched field
     * @param y the y coordinate of the searched field
     * @return the Field at the specified coordinates
     * @throws Exception if the coordinates are bigger than the board
     */
    public Field getField(int x, int y) throws Exception {
        if (x >= getWidth() || y >= getHeight())
            throw new Exception("x " + x + " or y " + y + " to high width " + width + " height " + height);
        return rows.get(x).get(y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Vector<Field>> getRows() {
        return rows;
    }

    public String toSting(){
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<getWidth();i++){
            for(Vector<Field> row : rows){
                result.append(row.get(i).toString());
                result.append(", ");
            }
            result.append("\n");

        }

        return result.toString();
    }

    /**
     * checks if the game is won
     * @return true if the current max is 13 else false
     */
    public boolean isWon(){
        return current_max == 13;
    }

    /**
     * checks each field if it is clickable
     * @return true if there is atleast one field clickable
     */
    public boolean isLost(){
        try {
            for(Vector<Field> row:rows){
                for(Field f: row){
                        if(isClickable(f))
                            return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * checks if the game is finished
     * @return true if it is won or lost
     */
    public boolean isFinished(){
        return isLost()||isWon();
    }

    /**
     * removes the neighboring fields of f if they have the same value
     * @param f the field whose neighbores shall get removed
     */
    void removeNeighbores(Field f){
        for(Field neighbore:getNeighbores(f)){
            if(isClickable(f,neighbore)){
                removeNeighbores(neighbore);
                removeField(neighbore);
            }
        }
    }

    /**
     * removes Field f from the board
     * @param f the field who shall get removed
     */
    private void removeField(Field f){
        Vector<Field> v = rows.get(f.getX());
        v.remove(f.getY());
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
