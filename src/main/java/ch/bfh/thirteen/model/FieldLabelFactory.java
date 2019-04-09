package main.java.ch.bfh.thirteen.model;

public class FieldLabelFactory {
    public static FieldLabel createFieldLabel(Field f, int x, int y) {
        FieldLabel fl = new FieldLabel(x, y);
        fl.setTextAndClass(String.valueOf(f.getValue()));
        return fl;
    }

    public static FieldLabel createFieldLabel(FieldPosition fp) {
        return createFieldLabel(fp.getF(), fp.getX(), fp.getY());
    }
}
