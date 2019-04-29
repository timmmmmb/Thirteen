package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.exception.FieldLabelNotFoundException;
import main.java.ch.bfh.thirteen.exception.UINotMatchingModelException;
import main.java.ch.bfh.thirteen.model.*;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class GameScreenController implements PropertyChangeListener {
    @FXML
    private AnchorPane gamePane, gameBackground;
    @FXML
    private Label gameStateLabel, scoreLabel;

    private ArrayList<FieldLabel> removalList = new ArrayList<>();
    private ArrayList<ArrayList<Transition>> animationList = new ArrayList<>();
    private boolean isRemovalMode = false;

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
                try {
                    FieldPosition fp = (FieldPosition) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                    removeFadingOut(fl, gamePane);
                } catch (FieldLabelNotFoundException e) {
                    e.printStackTrace();
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
                try {
                    FieldPosition fp = (FieldPosition) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                    fl.getStyleClass().clear();
                    fl.setTextAndClass(String.valueOf(evt.getNewValue()));
                } catch (FieldLabelNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "movedField": {
                try {
                    FieldPosition fp = (FieldPosition) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(fp.getF(), fp.getX(), fp.getY());
                    TranslateTransition tt = new TranslateTransition(Duration.millis(500), fl);
                    double distance = Settings.getFieldHeight() * (Integer) evt.getNewValue();
                    tt.setByY(distance);
                    animationList.get(1).add(tt);
                } catch (FieldLabelNotFoundException e) {
                    e.printStackTrace();
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
        ThirteenApplication.game.getBoard().getPcs().addPropertyChangeListener(this);
        scoreLabel.setText(String.valueOf(ThirteenApplication.game.getBoard().getScore()));
        addLabels();
        createBackground();
    }

    /**
     * this function gets called when the restart button is pressed
     * it creates a new board and resets all of the game variables
     */
    @FXML
    private void restart() {
        gameBackground.getChildren().clear();
        ThirteenApplication.game.getBoard().getPcs().removePropertyChangeListener(this);
        ThirteenApplication.game.restartGame();
        gamePane.getChildren().removeAll(gamePane.getChildren());
        ThirteenApplication.game.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
        createBackground();
        gameStateLabel.setText("");
        scoreLabel.setText("0");
    }

    /**
     * this function gets called when the restart button is pressed
     * it creates a new board and resets all of the game variables
     */
    @FXML
    private void reload() {
        gameBackground.getChildren().clear();
        gamePane.getChildren().removeAll(gamePane.getChildren());
        addLabels();
        createBackground();
    }

    /**
     * this function gets called when there is a new max value.
     * it resetts the styleclass of all fields to their correct styleclass
     */
    private void resetStyle() {
        for (Node fl : gamePane.getChildren()) {
            fl.getStyleClass().clear();
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
        Board b = ThirteenApplication.game.getBoard();
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
            try {
                checkMatch();
            } catch (UINotMatchingModelException e) {
                e.printStackTrace();
            }
            ThirteenApplication.game.getBoard().finishAnimation();
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
     * compares the board with the ui and says throws an exception if they are not in sync
     *
     * @throws UINotMatchingModelException if the field was not found in the ui
     */
    private void checkMatch() throws UINotMatchingModelException {
        if (gamePane.getChildren().size() != ThirteenApplication.game.getBoard().getHeight()*ThirteenApplication.game.getBoard().getWidth()) {
            throw new UINotMatchingModelException("Size does not match");
        }
        try {
            Board b = ThirteenApplication.game.getBoard();
            for (int x = 0; x < b.getWidth(); x++) {
                for (int y = 0; y < b.getHeight(); y++) {
                    getFieldLabelByCoordinates(b.getField(x, y), x, y);
                }
            }
        } catch (FieldLabelNotFoundException e) {
            throw new UINotMatchingModelException(e.getMessage());
        }
    }

    /**
     * finds a fieldLabel in the ui with parameters from the board
     *
     * @param f the field that shall get found
     * @param x the x coordinate of the field in the board
     * @param y the y coordinate of the field in the board
     * @return the fieldlabel in the ui
     * @throws FieldLabelNotFoundException if the field was not found in the ui
     */
    private FieldLabel getFieldLabelByCoordinates(Field f, int x, int y) throws FieldLabelNotFoundException {
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
        throw new FieldLabelNotFoundException("The following field was not found in the ui" + f.toString() + " X:" + x + " Y:" + y);
    }

    /**
     * this function connects fieldLabels creating connector elements in the background
     */
    private void createBackground() {
        Board b = ThirteenApplication.game.getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                Field f = b.getField(x, y);
                if (y != b.getHeight() - 1) {
                    Field fh = b.getField(x, y + 1); // get the field below
                    if (fh.getValue() == f.getValue()) {
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f, x, y, 2, 1));
                    }
                }
                if (x != b.getWidth() - 1) {
                    Field fr = b.getField(x + 1, y); // get the field right
                    if (fr.getValue() == f.getValue()) {
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f, x, y, 1, 2));
                    }
                }
            }
        }
    }

    @FXML
    private void switchMenu(ActionEvent event) {
        changeStage(event, "fxml/menuScreen.fxml");
    }

    @FXML
    private void switchRemovalMode(){
        switchRemovalMode(false);
    }

    private void switchRemovalMode(boolean click) {
        if(isRemovalMode){
            if(!click){
                createBackground();
            }
            resetStyle();
        }else{
            gameBackground.getChildren().clear();
            for (Node fl : gamePane.getChildren()) {
                fl.getStyleClass().add("fieldBombMode");
            }
        }
        isRemovalMode = !isRemovalMode;
    }

    @FXML
    private void undo() {
        ThirteenApplication.game.undo();
        reload();
    }

    /**
     * this function gets called when a field gets clicked
     *
     * @param event the mouseEvent that was triggered when clicking the field
     */
    @FXML
    private void click(MouseEvent event) {
        gameBackground.getChildren().clear();
        FieldLabel fl = (FieldLabel)event.getSource();
        if(isRemovalMode){
            ThirteenApplication.game.removeField(fl);
            switchRemovalMode(true);
        }else{
            ThirteenApplication.game.clickField(fl);
        }
        playAnimations(0);
    }
}
