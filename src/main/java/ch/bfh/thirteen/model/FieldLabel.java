package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;


public class FieldLabel extends Label {

    public FieldLabel() {
        super();
    }

    public void setTextAndClass(String s){
        super.setText(s);
        getStyleClass().add("field"+s);
    }
}
