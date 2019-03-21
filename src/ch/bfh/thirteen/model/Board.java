package ch.bfh.thirteen.model;

import java.util.ArrayList;
import java.util.Vector;

public class Board {
    private int current_max = 6;
    private int current_min = 1;
    private int x_size;
    private int y_size;
    private ArrayList<Vector<Field>> rows = new ArrayList<>();

    public Board(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i = 0; i < x_size; i++){
            rows.add(new Vector<>());
        }
        int x = 0;
        for(Vector<Field> row: rows){
            for(int i = 0; i < y_size; i++){
                row.add(new Field(x,i,1));
            }
            x++;
        }
    }
}
