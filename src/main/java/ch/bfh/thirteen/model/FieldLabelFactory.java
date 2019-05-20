package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;

public class FieldLabelFactory {
    /**
     * creates a fieldLabel
     * @param f the field from the model
     * @return a new generated fieldlabel
     */
    public static FieldLabel createFieldLabel(Field f) {
        FieldLabel fl = new FieldLabel(f.getCoordinate().getX(), f.getCoordinate().getY());
        fl.setTextAndClass(String.valueOf(f.getValue()));
        return fl;
    }

    /**
     * creates a fieldLabel
     * @param f the field
     * @param height the height of the fl
     * @param width the width of the fl
     * @return a new fieldlabel
     */
    public static FieldLabel createFieldLabel(Field f, int height, int width) {
        FieldLabel fl = createFieldLabel(f);
        fl.setPrefHeight(ThirteenApplication.getSettings().getFieldHeight() * height);
        fl.setPrefWidth(ThirteenApplication.getSettings().getFieldWidth() * width);
        fl.setText("");
        fl.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-insets: 1px;");
        return fl;
    }
}
