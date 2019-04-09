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
    private AnchorPane gamePane, gameBackground;
    @FXML
    private Label gameStateLabel, scoreLabel;

    private ArrayList<FieldLabel> removalList = new ArrayList<>();
    private ArrayList<ArrayList<Transition>> animationList = new ArrayList<>();


    /**
     * This function is called when the observed board fires a change
     *
     * @param evt the PropertyChangeEvent that was fired it contains the new and old value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "GameStateChange":
                if (evt.getNewValue() == GameState.WON) {
                    gameStateLabel.setText("Won");
                } else if (evt.getNewValue() == GameState.LOST) {
                    gameStateLabel.setText("Lost");
                }
                break;
            case "ScoreChanged":
                scoreLabel.setText(evt.getNewValue().toString());
                break;
            case "removedField": {
                FieldPosition fp = (FieldPosition) evt.getOldValue();
                FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                if (fl != null) {
                    removeFadingOut(fl, gamePane);
                }
                break;
            }
            case "addedField": {
                FieldPosition fp = (FieldPosition) evt.getOldValue();
                FieldLabel fl = FieldLabelFactory.createFieldLabel(fp);
                fl.setOnMouseClicked(this::click);
                addFadingIn(fl, gamePane);
                break;
            }
            case "incrementedFieldValue": {
                FieldPosition fp = (FieldPosition) evt.getOldValue();
                FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                if (fl != null)
                    fl.setTextAndClass(String.valueOf(evt.getNewValue()));
                break;
            }
            case "movedField": {
                FieldPosition fp = (FieldPosition) evt.getOldValue();
                FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                if (fl != null) {
                    TranslateTransition tt = new TranslateTransition(Duration.millis(500), fl);
                    double distance = Settings.getFieldHeight() * (Integer) evt.getNewValue();
                    tt.setByY(distance);
                    animationList.get(1).add(tt);
                }
                break;
            }
            case "newMaxValue":
                resetStyle();
                break;
        }
    }

    /**
     * this fxml function is called at the initialization of the fxml
     */
    @FXML
    private void initialize() {
        animationList.add(new ArrayList<>());
        animationList.add(new ArrayList<>());
        animationList.add(new ArrayList<>());
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
        createBackground();
    }

    /**
     * this function gets called when a field gets clicked
     *
     * @param event the mouseEvent that was triggered when clicking the field
     */
    @FXML
    private void click(MouseEvent event) {
        gameBackground.getChildren().clear();
        Board b = Settings.getBoard();
        //get coordinates
        int x = (int) ((FieldLabel) event.getSource()).getBoundsInParent().getMinX() / Settings.getFieldWidth();
        int y = (int) ((FieldLabel) event.getSource()).getBoundsInParent().getMinY() / Settings.getFieldHeight();
        //click in board
        b.clickField(x, y);
        playAnimations(0);
    }

    /**
     * this function gets called when the restart button is pressed
     * it creates a new board and resets all of the game variables
     */
    @FXML
    private void restart() {
        gameBackground.getChildren().clear();
        Settings.getBoard().getPcs().removePropertyChangeListener(this);
        Settings.initializeBoard();
        gamePane.getChildren().removeAll(gamePane.getChildren());
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
        createBackground();
        gameStateLabel.setText("");
        scoreLabel.setText("0");
    }

    /**
     * this function gets called when there is a new max value.
     * it resetts the styleclass of all fields to their correct styleclass
     */
    private void resetStyle() {
        for (Node fl : gamePane.getChildren()) {
            ((FieldLabel) fl).setTextAndClass(((FieldLabel) fl).getText());
        }
    }

    /**
     * used to add a new node to a parent with a nice fadein animation
     *
     * @param node   the new node that gets added
     * @param parent the parent of the new node
     */
    private void addFadingIn(final Node node, final AnchorPane parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
        node.setOpacity(0);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setInterpolator(Interpolator.EASE_IN);
        parent.getChildren().add(node);
        animationList.get(2).add(transition);
    }

    /**
     * removes a node from a parent with a fade out animation
     *
     * @param node   the node that shall get removed
     * @param parent the parent of the node
     */
    private void removeFadingOut(final Node node, final AnchorPane parent) {
        if (parent.getChildren().contains(node)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
            transition.setFromValue(node.getOpacity());
            transition.setToValue(0);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            removalList.add((FieldLabel) node);
            animationList.get(0).add(transition);
        }
    }

    /**
     * this function creates labels for all of the fields in the board and adds them to the gamePane
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
        gameBackground.setPrefWidth((b.getWidth() - 1) * Settings.getFieldWidth());
        gameBackground.setPrefHeight((b.getHeight() - 1) * Settings.getFieldHeight());
    }

    /**
     * plays all of the animations in the animation animationlist that are at the position i
     * after the last animation is finished it will conitnue with the next one by calling itself recursivly with i+1 till i > animationList.size
     *
     * @param i the position of the animations
     */
    private void playAnimations(int i) {
        //this gets executed at the end of all
        if (i >= animationList.size()) {
            gamePane.getChildren().removeAll(removalList);
            removalList.clear();
            checkMatch();
            Settings.getBoard().finishAnimation();
            createBackground();
            return;
        }

        ArrayList<Transition> animation = animationList.get(i);
        if (animation.size() != 0) {
            animation.get(animation.size() - 1).setOnFinished(actionEvent -> {
                animationList.get(i).clear();
                playAnimations(i + 1);
            });
            for (Transition tt : animation) {
                tt.play();
            }
        } else {
            playAnimations(i + 1);
        }
    }

    /**
     * compares the board with the ui and says if they are in sync or not
     */
    private void checkMatch() {
        if (gamePane.getChildren().size() != 25) {
            System.out.println("Size does not match");
            return;
        }
        Board b = Settings.getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                if (getFieldLabelByCoordinates(b.getField(x, y), x, y) == null) {
                    System.out.println("Not able to find field: Value:" + b.getField(x, y).toString() + " X:" + x + " Y:" + y);
                    return;
                }
            }
        }
    }

    /**
     * finds a fieldLabel in the ui with parameters from the board
     *
     * @param f the field that shall get found
     * @param x the x coordinate of the field in the board
     * @param y the y coordinate of the field in the board
     * @return the fieldlabel in the ui
     */
    private FieldLabel getFieldLabelByCoordinates(Field f, int x, int y) {
        for (Node child : gamePane.getChildren()) {
            FieldLabel fl = (FieldLabel) child;
            int layoutX = (int) fl.getBoundsInParent().getMinX() / Settings.getFieldWidth();
            int layoutY = (int) fl.getBoundsInParent().getMinY() / Settings.getFieldHeight();
            if (layoutX == x && layoutY == y && String.valueOf(f.getValue()).equals(fl.getText())) {
                if (removalList.contains(child)) {
                    continue;
                }
                return fl;
            }
        }
        return null;
    }

    /**
     * this function connects fieldLabels creating connector elements in the background
     */
    private void createBackground(){
        Board b = Settings.getBoard();
        for(int x = 0; x < b.getWidth(); x++){
            for(int y = 0; y < b.getHeight(); y++){
                Field f = b.getField(x,y);
                if(y != b.getHeight()-1){
                    Field fh = b.getField(x,y+1); // get the field below
                    if(fh.getValue()==f.getValue()){
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f,x,y,2,1));
                    }
                }
                if(x != b.getWidth()-1){
                    Field fr = b.getField(x+1,y); // get the field right
                    if(fr.getValue()==f.getValue()){
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f,x,y,1,2));
                    }
                }
            }
        }
    }
}
