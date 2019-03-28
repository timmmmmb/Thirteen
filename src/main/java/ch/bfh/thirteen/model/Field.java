package main.java.ch.bfh.thirteen.model;

public class Field {
    private int x, y;
    private int value;
    private Board parent;

    public Field(int x, int y, int value, Board parent) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.parent = parent;
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

    public void click(){
        incrementValue();
    }

    public String toString(){
        return String.valueOf(x)+" "+String.valueOf(y)+" "+String.valueOf(value);
    }
}
