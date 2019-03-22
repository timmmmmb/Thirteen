package ch.bfh.thirteen.model.main;

import java.util.ArrayList;
import java.util.Vector;

public class Board {
    private int current_max = 6;
    private int current_min = 1;
    private int width;
    private int height;
    private ArrayList<Vector<Field>> rows = new ArrayList<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i = 0; i < width; i++){
            rows.add(new Vector<>());
        }
        int x = 0;
        for(Vector<Field> row: rows){
            for(int i = 0; i < height; i++){
                row.add(new Field(x,i,1));
            }
            x++;
        }
    }
}
