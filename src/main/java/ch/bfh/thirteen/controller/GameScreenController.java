package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.model.Board;
import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.FieldLabel;

import java.util.Vector;

public class GameScreenController {
    @FXML
    private HBox mainHBox;

    public static void addFadingIn(final Node node, final VBox parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(500), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setInterpolator(Interpolator.EASE_IN);
        parent.getChildren().add(node);
        transition.play();
    }

    public static void removeFadingOut(final Node node, final Group parent) {
        if (parent.getChildren().contains(node)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
            transition.setFromValue(node.getOpacity());
            transition.setToValue(0);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setOnFinished(finishHim -> {
                parent.getChildren().remove(node);
            });
            transition.play();
        }
    }

    /**
     * adds FieldLabels for each Field
     */
    public void addLabels(){
        Board b = ThirteenApplication.getBoard();
        for(Vector<Field> row:b.getRows()){
            VBox v = new VBox();
            for(int i = row.size()-1; i>=0;i--){
                FieldLabel fl = new FieldLabel();
                fl.setTextAndClass(String.valueOf(row.get(i)));
                addFadingIn(fl,v);
            }
            mainHBox.getChildren().add(v);
        }
    }

    @FXML
    void initialize() {
        addLabels();
    }
}
