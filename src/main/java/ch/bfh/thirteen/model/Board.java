package main.java.ch.bfh.thirteen.model;

import java.util.ArrayList;
import java.util.Vector;

public class Board {
    private WeightedRandomNumberGenerator wrng;
    private int current_max = 6;
    private int current_min = 1;
    private int width;
    private int height;
    private ArrayList<Vector<Field>> rows = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        wrng = new WeightedRandomNumberGenerator(current_max - 1, current_min, new int[]{3, 3, 3, 2, 1});
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i <= width; i++) {
            rows.add(new Vector<>());
        }
        int x = 0;
        for (Vector<Field> row : rows) {
            for (int i = 0; i <= height; i++) {
                row.add(new Field(x, i, wrng.getNumber()));
            }
            x++;
        }

        // once add the maxnumber to the board

    }

    /**
     * this function is used to get all neighbores of a field
     * @param f the field which neighbores you need
     * @return an arraylist with all of the neighbores
     */
    public ArrayList<Field> getNeighbores(Field f) throws Exception {
        int x = f.getX();
        int y = f.getY();
        ArrayList<Field> neighbores = new ArrayList<>();
        if(y<getHeight())
            neighbores.add(getField(x,y+1));
        if(y>0)
            neighbores.add(getField(x,y-1));
        if(x<getWidth())
            neighbores.add(getField(x+1,y));
        if(x>0)
            neighbores.add(getField(x-1,y));
        return neighbores;
    }

    /**
     * used to get one field by coordinates
     * @param x the x coordinate of the searched field
     * @param y the y coordinate of the searched field
     * @return the Field at the specified coordinates
     * @throws Exception if the coordinates are bigger than the board
     */
    public Field getField(int x, int y) throws Exception {
        if(x>getWidth()||y>getHeight())
            throw new Exception("x "+x+" or y "+y+" to high width "+width+" height "+height);
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
}
