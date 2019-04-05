package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;
import main.java.ch.bfh.thirteen.settings.Settings;


public class FieldLabel extends Label {

    public FieldLabel(int x, int y) {
        super();

        this.setPrefHeight(Settings.getFieldHeight());
        this.setPrefWidth(Settings.getFieldWidth());
        this.setLayoutX(x*Settings.getFieldWidth());
        this.setLayoutY(y*Settings.getFieldHeight());
    }

    public void setTextAndClass(String s){
        super.setText(s);
        getStyleClass().add("field"+s);
    }
}
