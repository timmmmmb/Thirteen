package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;


public class FieldLabel extends Label {

    /**
     * creates a new fieldLabel for the ui
     * @param x the x coordinate
     * @param y the y coordinate
     */
    FieldLabel(int x, int y) {
        super();

        this.setPrefHeight(ThirteenApplication.getSettings().getFieldHeight());
        this.setPrefWidth(ThirteenApplication.getSettings().getFieldWidth());
        this.setLayoutX(x * ThirteenApplication.getSettings().getFieldWidth());
        this.setLayoutY(y * ThirteenApplication.getSettings().getFieldHeight());
    }

    /**
     * changes the textvalue of the label and also changes the Class
     * @param s the new value String
     */
    public void setTextAndClass(String s) {
        super.setText(s);
        if (Integer.parseInt(s) == ThirteenApplication.getGame().getBoard().getCurrent_max()) {
            getStyleClass().add("fieldMax");
        } else {
            getStyleClass().add("field" + s);
        }
    }

    /**
     * the toString function
     * @return this Object as a String
     */
    public String toString() {
        return this.getLayoutX() + " " + this.getLayoutY() + " " + this.getText();
    }
}
