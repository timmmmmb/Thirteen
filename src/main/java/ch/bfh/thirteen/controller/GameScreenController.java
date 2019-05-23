package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.exception.FieldLabelNotFoundException;
import main.java.ch.bfh.thirteen.exception.UINotMatchingModelException;
import main.java.ch.bfh.thirteen.fieldlabel.FieldLabel;
import main.java.ch.bfh.thirteen.fieldlabel.FieldLabelFactory;
import main.java.ch.bfh.thirteen.model.*;
import main.java.ch.bfh.thirteen.score.Score;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;

import static main.java.ch.bfh.thirteen.application.ThirteenApplication.getGame;
import static main.java.ch.bfh.thirteen.application.ThirteenApplication.getSettings;
import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

/**
 * controller class for the game screen
 */
public class GameScreenController implements PropertyChangeListener {
    @FXML
    public VBox endGamePane,confirmationPane;
    @FXML
    public Button undoButton,bombButton;
    @FXML
    protected AnchorPane gamePane, gameBackground;
    @FXML
    private Label gameStateLabel, timerLabel, starLabel, scoreInfoLabel, highScoreLabel, infoTextLabel;
    private ArrayList<FieldLabel> removalList = new ArrayList<>();
    private ArrayList<ArrayList<Transition>> animationList = new ArrayList<>();
    private boolean isRemovalMode = false;
    protected Duration animationTime = Duration.millis(250);
    protected boolean simulation = false;
    private boolean undoEvent = false;


    /**
     * This function is called when the observed board fires a change
     *
     * @param evt the PropertyChangeEvent that was fired it contains the new and old value
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "GameStateChange":
                gameOver(evt);
                break;
            case "StarsChanged":
                if(simulation)return;
                starLabel.setText(evt.getNewValue().toString());
                disableButton();
                break;
            case "removedField": {
                try {
                    Field f = (Field) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(f, f.getCoordinate().getX(), f.getCoordinate().getY());
                    removeFadingOut(fl, gamePane);
                } catch (FieldLabelNotFoundException e) {
                    e.printStackTrace();
                    ThirteenApplication.log.log("field label not found", Level.SEVERE);
                }
                break;
            }
            case "addedField": {
                Field f = (Field) evt.getOldValue();
                FieldLabel fl = FieldLabelFactory.createFieldLabel(f);
                fl.setOnMouseClicked(this::click);
                addFadingIn(fl, gamePane);
                break;
            }
            case "incrementedFieldValue": {
                try {
                    Field f = (Field) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(f, f.getCoordinate().getX(), f.getCoordinate().getY());
                    fl.getStyleClass().clear();
                    fl.setTextAndClass(String.valueOf(evt.getNewValue()));
                } catch (FieldLabelNotFoundException e) {
                    e.printStackTrace();
                    ThirteenApplication.log.log("field label not found", Level.SEVERE);
                }
                break;
            }
            case "movedField": {
                try {
                    Field f = (Field) evt.getOldValue();
                    FieldLabel fl = getFieldLabelByCoordinates(f, f.getCoordinate().getX(), f.getCoordinate().getY());
                    TranslateTransition tt = new TranslateTransition(animationTime.multiply(2), fl);
                    double distance = ThirteenApplication.getSettings().getFieldHeight() * (Integer) evt.getNewValue();
                    tt.setByY(distance);
                    animationList.get(1).add(tt);
                } catch (FieldLabelNotFoundException e) {
                    ThirteenApplication.log.log("field label not found", Level.SEVERE);
                    e.printStackTrace();
                }
                break;
            }
            case "newMaxValue":
                resetStyle();
                break;
            case "t":
                if(simulation)return;
                timerLabel.setText(evt.getNewValue().toString());
                break;
        }
    }

    /**
     * displays at game over if the game is won or lost
     * @param evt the change event that said what changed
     */
    private void gameOver(PropertyChangeEvent evt) {
        if (evt.getNewValue() == GameState.WON) {
            ThirteenApplication.log.log("game won", Level.INFO);
            gameStateLabel.setText("Won");
            createEndscreen();
        } else if (evt.getNewValue() == GameState.LOST) {
            ThirteenApplication.log.log("game lost", Level.INFO);
            gameStateLabel.setText("Lost");
            createEndscreen();
        }
    }

    /**
     * shows up at game over to display and save score
     */
    private void createEndscreen(){
        getGame().getTimer().pause();
        // save the statistic if not a botgame
        if (!simulation) {
            getSettings().setScore(new Score(ThirteenApplication.getGame().getBoard().getCurrent_max(), ThirteenApplication.getGame().getMoves(), ThirteenApplication.getGame().getTimer().getTime(), ThirteenApplication.getGame().getBoard().getGameState() == GameState.WON));
            scoreInfoLabel.setText("Your Score: "+String.valueOf(getGame().getBoard().getCurrent_max())+": "+String.valueOf(getGame().getMoves()));
            highScoreLabel.setText("Highscore: "+String.valueOf(getSettings().getHighscore().getHighestnumber())+": "+String.valueOf(getSettings().getHighscore().getMoves()));
            endGamePane.setVisible(true);
        }
        gameStateLabel.setVisible(true);
    }

    /**
     * this fxml function is called at the initialization of the fxml
     */
    @FXML
    protected void initialize() {
        disableButton();
        animationList.add(new ArrayList<>());
        animationList.add(new ArrayList<>());
        animationList.add(new ArrayList<>());
        if(!simulation){
            timerLabel.setText(String.valueOf(getGame().getTimer().getTime()));
            starLabel.setText(String.valueOf(ThirteenApplication.getSettings().getStars()));
        }
        getGame().getTimer().play();
        getGame().getPcs().addPropertyChangeListener(this);
        addLabels();
        createBackground();
    }

    /**
     * this function gets called when the restart button is pressed
     * it creates a new board and resets all of the game variables
     */
    @FXML
    protected void restart() {
        gameBackground.getChildren().clear();
        getGame().restartGame();
        gamePane.getChildren().removeAll(gamePane.getChildren());
        addLabels();
        createBackground();
        gameStateLabel.setText("");
        if(!simulation){
            endGamePane.setVisible(false);
            starLabel.setText(String.valueOf(ThirteenApplication.getSettings().getStars()));
            timerLabel.setText("0");
        }
        gameStateLabel.setVisible(false);
        ThirteenApplication.log.log("game restarted", Level.INFO);
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
        final FadeTransition transition = new FadeTransition(animationTime, node);
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
            final FadeTransition transition = new FadeTransition(animationTime, node);
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
        Board b = getGame().getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                FieldLabel fl = FieldLabelFactory.createFieldLabel(b.getField(x, y));
                fl.setOnMouseClicked(this::click);
                gamePane.getChildren().add(fl);
            }
        }
        gamePane.setPrefWidth((b.getWidth() - 1) * ThirteenApplication.getSettings().getFieldWidth());
        gamePane.setPrefHeight((b.getHeight() - 1) * ThirteenApplication.getSettings().getFieldHeight());
        gameBackground.setPrefWidth((b.getWidth() - 1) * ThirteenApplication.getSettings().getFieldWidth());
        gameBackground.setPrefHeight((b.getHeight() - 1) * ThirteenApplication.getSettings().getFieldHeight());
    }

    /**
     * plays all of the animations in the animation animationlist that are at the position i
     * after the last animation is finished it will conitnue with the next one by calling itself recursivly with i+1 till i > animationList.size
     *
     * @param i the position of the animations
     */
    void playAnimations(int i) {
        //this gets executed at the end of all
        if (i >= animationList.size()) {
            endTurn();
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
     * ends the current turn
     */
    public void endTurn() {
        gamePane.getChildren().removeAll(removalList);
        removalList.clear();
        try {
            checkMatch();
        } catch (UINotMatchingModelException e) {
            e.printStackTrace();
        }
        getGame().getBoard().finishAnimation();
        createBackground();
    }

    /**
     * compares the board with the ui and says throws an exception if they are not in sync
     *
     * @throws UINotMatchingModelException if the field was not found in the ui
     */
    private void checkMatch() throws UINotMatchingModelException {
        if (gamePane.getChildren().size() != getGame().getBoard().getHeight() * getGame().getBoard().getWidth()) {
            throw new UINotMatchingModelException("Size does not match");
        }
        try {
            Board b = getGame().getBoard();
            for (int x = 0; x < b.getWidth(); x++) {
                for (int y = 0; y < b.getHeight(); y++) {
                    getFieldLabelByCoordinates(b.getField(x, y), x, y);
                }
            }
        } catch (FieldLabelNotFoundException e) {
            ThirteenApplication.log.log("field label not found", Level.SEVERE);
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
            int layoutX = (int) fl.getBoundsInParent().getMinX() / ThirteenApplication.getSettings().getFieldWidth();
            int layoutY = (int) fl.getBoundsInParent().getMinY() / ThirteenApplication.getSettings().getFieldHeight();
            if (layoutX == x && layoutY == y && String.valueOf(f.getValue()).equals(fl.getText())) {
                if (removalList.contains(child)) {
                    continue;
                }
                return fl;
            }
        }
        throw new FieldLabelNotFoundException("The following field was not found in the ui " + f.toString() + " X:" + x + " Y:" + y);
    }

    /**
     * this function connects fieldLabels creating connector elements in the background
     */
    private void createBackground() {
        Board b = getGame().getBoard();
        for (int x = 0; x < b.getWidth(); x++) {
            for (int y = 0; y < b.getHeight(); y++) {
                Field f = b.getField(x, y);
                if (y != b.getHeight() - 1) {
                    Field fh = b.getField(x, y + 1); // get the field below
                    if (fh.getValue() == f.getValue()) {
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f, 2, 1));
                    }
                }
                if (x != b.getWidth() - 1) {
                    Field fr = b.getField(x + 1, y); // get the field right
                    if (fr.getValue() == f.getValue()) {
                        gameBackground.getChildren().add(FieldLabelFactory.createFieldLabel(f, 1, 2));
                    }
                }
            }
        }
    }

    /**
     * lets the player go back to the menu
     * will first check if game is lost or won to restart
     * pauses active game
     * @param event stage change event
     */
    @FXML
    protected void switchMenu(ActionEvent event) {
        // restart the game if it is over
        if(getGame().getBoard().getGameState()==GameState.LOST||getGame().getBoard().getGameState()==GameState.WON){
            getGame().restartGame();
        }
        getGame().getPcs().removePropertyChangeListener(this);

        getGame().getTimer().pause();
        changeStage(event, "fxml/menuScreen.fxml");

    }

    /**
     *lets the user see a decision screen when wanting to remove a tile
     */
    @FXML
    private void switchRemovalMode() {
        if(isRemovalMode){
            return;
        }
        infoTextLabel.setText("This Action costs: "+getGame().getBombcost());
        undoEvent = false;
        confirmationPane.setVisible(true);
    }


    /**
     * lets the user remove a tile if they have enough stars
     * @param click sees if a field is clicked
     */
    private void switchRemovalMode(boolean click) {
        if (isRemovalMode) {
            if (!click) {
                createBackground();
            }
            resetStyle();
            disableButton();
        } else {
            //decreases the stars and returns if there are not enough of them
            if (getSettings().decreaseStars(getGame().getBombcost())) {
                return;
            }
            gameBackground.getChildren().clear();
            for (Node fl : gamePane.getChildren()) {
                fl.getStyleClass().add("fieldBombMode");
            }
        }
        isRemovalMode = !isRemovalMode;
    }

    /**
     * shows the screen to decide id the user wants to undo an action and does the undo event
     */
    @FXML
    private void undo() {
        infoTextLabel.setText("This Action costs: "+getGame().getUndocost());
        undoEvent = true;
        confirmationPane.setVisible(true);
    }

    /**
     * this function gets called when a field gets clicked
     *
     * @param event the mouseEvent that was triggered when clicking the field
     */
    @FXML
    private void click(MouseEvent event) {
        // dont allow clicking if simulation
        if (simulation) {
            return;
        }
        gameBackground.getChildren().clear();
        FieldLabel fl = (FieldLabel) event.getSource();
        if (isRemovalMode) {
            getGame().removeField(fl);
            switchRemovalMode(true);
        } else {
            getGame().clickField(fl);
        }
        playAnimations(0);
    }

    /**
     *does the undo when it was confirmed or else goes back
     *sets the decision screen invisible again
     */
    public void agree() {
        if(undoEvent){
            getGame().undo();
            reload();
        }else{
            switchRemovalMode(false);

        }
        confirmationPane.setVisible(false);
        disableButton();
    }

    /**
     * sets the decision screen invisible if undo was rejected
     */
    public void reject() {
        confirmationPane.setVisible(false);
    }

    /**
     * if the user has not enough stars for the bomb or the undo the button will be disabled
     */
    private void disableButton(){
        if(simulation){
            return;
        }
        if(getSettings().getStars()>=getGame().getBombcost()){
            bombButton.setDisable(false);
        }else{
            bombButton.setDisable(true);
        }

        if(getSettings().getStars()>=getGame().getUndocost()&&getGame().hasHistory()){
            undoButton.setDisable(false);
        }else{
            undoButton.setDisable(true);
        }
    }
}
