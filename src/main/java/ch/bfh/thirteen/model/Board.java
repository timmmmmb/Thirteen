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
            Field f = getField(new Random().nextInt(getWidth()-1),0);
            f.setValue(current_max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * checks if two fields are clickable
     * @param f the field that gets checkt
     * @return true if the field f can be clicked
     */
    private boolean isClickable(Field f) {
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
            if (y+1 < getHeight())
                neighbores.add(getField(x, y + 1));
            if (y > 0)
                neighbores.add(getField(x, y - 1));
            if (x+1 < getWidth())
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
        if (x >= rows.size() || y >= rows.get(x).size())
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
        for(int i = getWidth()-1;i>=0;i--){
            for(Vector<Field> row : rows){
                result.append(row.get(i).toString());
                result.append(", ");
            }
            result.replace(result.length()-2,result.length(),"");
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * checks if the game is won
     * @return true if the current max is 13 else false
     */
    boolean isWon(){
        return current_max == 13;
    }

    /**
     * checks each field if it is clickable
     * @return true if there is atleast one field clickable
     */
    boolean isLost(){
        try {
            for(Vector<Field> row:rows){
                for(Field f: row){
                    if(isClickable(f))
                        return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
        f.setVisited(true);
        for(Field neighbore:getNeighbores(f)){
            if(!neighbore.isVisited()&&!neighbore.getToBeRemoved()&&isClickable(f,neighbore)){
                neighbore.toBeRemoved();
                removeNeighbores(neighbore);
                if(neighbore.getToBeRemoved()){
                    removeField(neighbore);
                }
            }
        }
    }

    /**
     * removes Field f from the board
     * @param f the field who shall get removed
     */
    private void removeField(Field f){
        Vector<Field> v = rows.get(f.getX());
        v.remove(f);
    }

    void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * update all of the positions of the fields to their position in the vector
     */
    void updateFieldPositions(){
        for(int i = 0; i<rows.size();i++){
            for(int j = 0; j<rows.get(i).size();j++){
                Field f = rows.get(i).get(j);
                f.setPosition(i,j);
                f.setVisited(false);

            }
        }
    }

    /**
     * adds new Fields while the rows are not full
     */
    void addFields(){
        for(Vector<Field> row:rows){
            while(row.size()<height){
                row.add(new Field(0, 0, wrng.getNumber(),this));
            }
        }

    }
}
