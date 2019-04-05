package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.model.*;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameScreenController implements PropertyChangeListener {
    @FXML
    private AnchorPane gamePane;
    @FXML
    private Label gameStateLabel, scoreLabel;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GameState && evt.getPropertyName().equals("GameStateChange")) {
            if (evt.getNewValue() == GameState.WON) {
                gameStateLabel.setText("Won");
            } else if (evt.getNewValue() == GameState.LOST) {
                gameStateLabel.setText("Lost");
            }
        } else if (evt.getPropertyName().equals("ScoreChanged")) {
            scoreLabel.setText(evt.getNewValue().toString());
        } else if (evt.getPropertyName().equals("removedField")) {
            FieldPosition fp = (FieldPosition) evt.getOldValue();
            FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
            if (fl != null) {
                //removeFadingOut(fl, gamePane);
                gamePane.getChildren().remove(fl);
            }
        } else if (evt.getPropertyName().equals("addedField")) {
            FieldPosition fp = (FieldPosition) evt.getOldValue();
            FieldLabel fl = FieldLabelFactory.createFieldLabel(fp);
            fl.setOnMouseClicked(this::click);
            addFadingIn(fl,gamePane);
            //gamePane.getChildren().add(fl);

        } else if (evt.getPropertyName().equals("incrementedFieldValue")) {
            FieldPosition fp = (FieldPosition) evt.getOldValue();
            FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
            if (fl != null)
                fl.setTextAndClass(String.valueOf(evt.getNewValue()));
        } else if (evt.getPropertyName().equals("movedField")) {
            FieldPosition fp = (FieldPosition) evt.getOldValue();
            FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
            if (fl != null) {
                TranslateTransition tt = new TranslateTransition(Duration.millis(500), fl);
                tt.setByY(Settings.getFieldHeight()*(Integer) evt.getNewValue());
                tt.setCycleCount(1);
                fl.setLayoutY(fl.getLayoutY() + Settings.getFieldHeight());
                //tt.play();
            }
        }
    }

    @FXML
    private void initialize() {
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
    }

    private static void addFadingIn(final Node node, final AnchorPane parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(500), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setInterpolator(Interpolator.EASE_IN);
        parent.getChildren().add(node);
        transition.play();
    }

    private static void removeFadingOut(final Node node, final AnchorPane parent) {
        if (parent.getChildren().contains(node)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), node);
            transition.setFromValue(node.getOpacity());
            transition.setToValue(0);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setOnFinished(finishHim -> parent.getChildren().remove(node));
            transition.play();
        }
    }

    /**
     * adds FieldLabels for each Field
     */
    private void addLabels() {
        Board b = Settings.getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                FieldLabel fl = FieldLabelFactory.createFieldLabel(b.getField(x, y), x, y);
                fl.setOnMouseClicked(this::click);
                addFadingIn(fl, gamePane);
            }
        }
        gamePane.setPrefWidth((b.getWidth() - 1) * Settings.getFieldWidth());
        gamePane.setPrefHeight((b.getHeight() - 1) * Settings.getFieldHeight());
    }

    @FXML
    private void click(MouseEvent event) {
        Board b = Settings.getBoard();
        //get coordinates
        int x = (int) ((FieldLabel) event.getSource()).getLayoutX() / Settings.getFieldWidth();
        int y = (int) ((FieldLabel) event.getSource()).getLayoutY() / Settings.getFieldWidth();
        //click in board
        b.clickField(x, y);
    }

    private FieldLabel getFieldLabelByCoordinates(Field f, int x, int y) {
        if (f == null)
            return null;
        for (Node child : gamePane.getChildren()) {
            FieldLabel fl = (FieldLabel) child;
            if (((int) fl.getLayoutX() / Settings.getFieldWidth()) == x && ((int) fl.getLayoutY() / Settings.getFieldHeight()) == y && fl.getText().equals(String.valueOf(f.getValue()))) {
                return fl;
            }
        }
        return null;
    }

    @FXML
    private void restart() {
        Settings.getBoard().getPcs().removePropertyChangeListener(this);
        Settings.initializeBoard();
        gamePane.getChildren().removeAll(gamePane.getChildren());
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
        gameStateLabel.setText("");
        scoreLabel.setText("0");
    }
}
