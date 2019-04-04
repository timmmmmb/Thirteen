package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.model.Board;
import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.FieldLabel;
import main.java.ch.bfh.thirteen.model.GameState;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class GameScreenController implements PropertyChangeListener {
    @FXML
    private HBox mainHBox;
    @FXML
    private Label gameStateLabel;

    public static void addFadingIn(final Node node, final VBox parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
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
    private void addLabels(){
        Board b = Settings.getBoard();
        int x = 0;
        for(Vector<Field> row:b.getRows()){
            VBox v = new VBox();
            int y = 0;
            for(int i = row.size()-1; i>=0;i--){
                FieldLabel fl = new FieldLabel(x,y);
                fl.setTextAndClass(String.valueOf(row.get(i)));
                fl.setOnMouseClicked(this::click);
                //addFadingIn(fl,v);
                v.getChildren().add(fl);
                y++;
            }
            x++;
            mainHBox.getChildren().add(v);
        }
    }

    private void removeLabels(ObservableList<Node> tobeRemoved){
        mainHBox.getChildren().removeAll(tobeRemoved);
    }

    @FXML
    private void click(MouseEvent event){
        Board b = Settings.getBoard();
        //get coordinates
        int x = ((FieldLabel)event.getSource()).getX();
        int y = Settings.getBoardHeight()-((FieldLabel)event.getSource()).getY()-1;
        //click in board
        try {
            Field field = b.getField(x,y);
            if(!b.isClickable(field)){
                return;
            }
            field.click();
            //remove all removedLabels
            removeLabels(mainHBox.getChildren());
            //add all newLabels
            addLabels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();

    }



    @FXML
    private void restart(){
        Settings.getBoard().getPcs().removePropertyChangeListener(this);
        Settings.initializeBoard();
        mainHBox.getChildren().removeAll(mainHBox.getChildren());
        Settings.getBoard().getPcs().addPropertyChangeListener(this);
        addLabels();
        gameStateLabel.setText("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getNewValue() instanceof GameState){
            if(evt.getNewValue() == GameState.WON){
                gameStateLabel.setText("Won");
            }else if(evt.getNewValue() == GameState.LOST){
                gameStateLabel.setText("Lost");
            }
        }
    }
}
