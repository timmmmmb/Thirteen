package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
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
import java.util.ArrayList;

public class GameScreenController implements PropertyChangeListener {
    @FXML
    private AnchorPane gamePane;
    @FXML
    private Label gameStateLabel, scoreLabel;

    private ArrayList<FieldLabel> removalList = new ArrayList<>();
    private ArrayList<Transition> animationList = new ArrayList<>();

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
            FieldLabel fl = getFieldLabelByCoordinates(fp.getF(),fp.getX(), fp.getY());
            if (fl != null) {
                removeFadingOut(fl, gamePane);
            }
        } else if (evt.getPropertyName().equals("addedField")) {
            FieldPosition fp = (FieldPosition) evt.getOldValue();
            FieldLabel fl = FieldLabelFactory.createFieldLabel(fp);
            fl.setOnMouseClicked(this::click);
            addFadingIn(fl,gamePane);
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
                double distance = Settings.getFieldHeight()*(Integer) evt.getNewValue();
                tt.setByY(distance);
                animationList.add(tt);
            }
        }
    }

    @FXML
    private void initialize() {
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
    }

    private void addFadingIn(final Node node, final AnchorPane parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(500), node);
        node.setOpacity(0);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setInterpolator(Interpolator.EASE_IN);
        parent.getChildren().add(node);
        animationList.add(transition);
    }

    private void removeFadingOut(final Node node, final AnchorPane parent) {
        if (parent.getChildren().contains(node)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(500), node);
            transition.setFromValue(node.getOpacity());
            transition.setToValue(0);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            removalList.add((FieldLabel) node);
            animationList.add(transition);
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
                gamePane.getChildren().add(fl);
            }
        }
        gamePane.setPrefWidth((b.getWidth() - 1) * Settings.getFieldWidth());
        gamePane.setPrefHeight((b.getHeight() - 1) * Settings.getFieldHeight());
    }

    @FXML
    private void click(MouseEvent event) {
        Board b = Settings.getBoard();
        //get coordinates
        int x = (int) ((FieldLabel) event.getSource()).getBoundsInParent().getMinX() / Settings.getFieldWidth();
        int y = (int) ((FieldLabel) event.getSource()).getBoundsInParent().getMinY() / Settings.getFieldHeight();
        //click in board
        b.clickField(x, y);
        playFirstAnimation();
    }

    private void playFirstAnimation(){
        //this gets executed at the end of all
        if(animationList.isEmpty()){
            gamePane.getChildren().removeAll(removalList);
            removalList.clear();
            System.out.println("The UI matches the board: "+checkMatch());
            Settings.getBoard().finishAnimation();
            return;
        }
        Transition tt = animationList.get(0);
        tt.setOnFinished(actionEvent -> {
            animationList.remove(0);
            playFirstAnimation();
        });
        tt.play();
    }

    private boolean checkMatch(){
        if(gamePane.getChildren().size()!=25){
            System.out.println("Size does not match");
            return false;
        }
        Board b = Settings.getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                if(getFieldLabelByCoordinates(b.getField(x,y),x,y)==null){
                    System.out.println("Not able to find field: Value:"+b.getField(x,y).toString()+" X:"+x+" Y:"+y);
                    return false;
                }
            }
        }
        return true;
    }

    private FieldLabel getFieldLabelByCoordinates(Field f, int x, int y) {
        for (Node child : gamePane.getChildren()) {
            FieldLabel fl = (FieldLabel) child;
            int layoutX = (int) fl.getBoundsInParent().getMinX() / Settings.getFieldWidth();
            int layoutY = (int) fl.getBoundsInParent().getMinY() / Settings.getFieldHeight();
            if (layoutX == x &&  layoutY == y && String.valueOf(f.getValue()).equals(fl.getText())) {
                if(removalList.contains(child)){
                    continue;
                }
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
