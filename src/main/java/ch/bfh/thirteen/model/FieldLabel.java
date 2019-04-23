package main.java.ch.bfh.thirteen.model;

import javafx.scene.control.Label;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.settings.Settings;


public class FieldLabel extends Label {

    FieldLabel(int x, int y) {
        super();

        this.setPrefHeight(Settings.getFieldHeight());
        this.setPrefWidth(Settings.getFieldWidth());
        this.setLayoutX(x * Settings.getFieldWidth());
        this.setLayoutY(y * Settings.getFieldHeight());
    }

    public void setTextAndClass(String s) {
        super.setText(s);
        getStyleClass().clear();
        if (Integer.parseInt(s) == ThirteenApplication.game.getBoard().getCurrent_max()) {
            getStyleClass().add("fieldMax");
        } else {
            getStyleClass().add("field" + s);
        }
    }

    public String toString() {
        return this.getLayoutX() + " " + this.getLayoutY() + " " + this.getText();
    }
}
