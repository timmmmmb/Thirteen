package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;


public class FieldLabel extends Label {

    FieldLabel(int x, int y) {
        super();

        this.setPrefHeight(ThirteenApplication.getSettings().getFieldHeight());
        this.setPrefWidth(ThirteenApplication.getSettings().getFieldWidth());
        this.setLayoutX(x * ThirteenApplication.getSettings().getFieldWidth());
        this.setLayoutY(y * ThirteenApplication.getSettings().getFieldHeight());
    }

    public void setTextAndClass(String s) {
        super.setText(s);
        if (Integer.parseInt(s) == ThirteenApplication.getGame().getBoard().getCurrent_max()) {
            getStyleClass().add("fieldMax");
        } else {
            getStyleClass().add("field" + s);
        }
    }

    public String toString() {
        return this.getLayoutX() + " " + this.getLayoutY() + " " + this.getText();
    }
}
