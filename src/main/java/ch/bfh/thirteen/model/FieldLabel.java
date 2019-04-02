package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;


public class FieldLabel extends Label {

    private int x, y;
    public FieldLabel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public void setTextAndClass(String s){
        super.setText(s);
        getStyleClass().add("field"+s);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
