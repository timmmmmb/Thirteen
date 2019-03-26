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
        for (int i = 0; i < width; i++) {
            rows.add(new Vector<>());
        }
        int x = 0;
        for (Vector<Field> row : rows) {
            for (int i = 0; i < height; i++) {
                row.add(new Field(x, i, wrng.getNumber()));
            }
            x++;
        }

        // once add the maxnumber to the board

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
