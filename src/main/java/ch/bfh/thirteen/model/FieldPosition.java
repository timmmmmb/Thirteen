package main.java.ch.bfh.thirteen.model;

public class FieldPosition {
    private Field f;
    private int x, y;

    FieldPosition(Field f, int x, int y) {
        this.f = f;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Field getF() {
        return f;
    }
}
