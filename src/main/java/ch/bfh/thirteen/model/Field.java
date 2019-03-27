package main.java.ch.bfh.thirteen.model;

public class Field {
    private int x, y;
    private int value;

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public void incrementValue(){
        this.value++;
    }
}
