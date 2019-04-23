package main.java.ch.bfh.thirteen.model;

public class Field{
    private int value;
    private boolean tobeRemoved = false;
    private boolean visited = false;

    public Field(int value) {
        this.value = value;
    }

    Field(Field field) {
        this.value = field.value;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    void incrementValue() {
        this.value++;
    }

    public String toString() {
        return String.valueOf(value);//+" "+String.valueOf(x)+" "+String.valueOf(y);
    }

    boolean getToBeRemoved() {
        return tobeRemoved;
    }

    void toBeRemoved() {
        tobeRemoved = true;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }
}
