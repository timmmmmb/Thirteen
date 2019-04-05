package main.java.ch.bfh.thirteen.model;

public class Field {
    private int x, y;
    private int value;
    private boolean tobeRemoved = false;
    private boolean visited = false;

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

    void setValue(int value){
        this.value = value;
    }

    void incrementValue(){
        this.value++;
    }

    public String toString(){
        return String.valueOf(value);//+" "+String.valueOf(x)+" "+String.valueOf(y);
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean getToBeRemoved(){
        return tobeRemoved;
    }

    void toBeRemoved(){
        tobeRemoved = true;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }
}
