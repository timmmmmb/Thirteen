package main.java.ch.bfh.thirteen.model;

import main.java.ch.bfh.thirteen.application.ThirteenApplication;

public class FieldLabelFactory {
    public static FieldLabel createFieldLabel(Field f, int x, int y) {
        FieldLabel fl = new FieldLabel(x, y);
        fl.setTextAndClass(String.valueOf(f.getValue()));
        return fl;
    }

    public static FieldLabel createFieldLabel(FieldPosition fp) {
        return createFieldLabel(fp.getF(), fp.getX(), fp.getY());
    }

    public static FieldLabel createFieldLabel(Field f, int x, int y, int height, int width) {
        FieldLabel fl = createFieldLabel(f, x, y);
        fl.setPrefHeight(ThirteenApplication.getSettings().getFieldHeight() * height);
        fl.setPrefWidth(ThirteenApplication.getSettings().getFieldWidth() * width);
        fl.setText("");
        fl.setStyle("-fx-background-radius: 10 10 10 10; -fx-background-insets: 1px;");
        return fl;
    }
}
