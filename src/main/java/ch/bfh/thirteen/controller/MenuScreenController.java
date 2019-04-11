package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class MenuScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Button autoplayButton;

    @FXML
    private ImageView image;

    @FXML
    private Button hiScoreButton;

    @FXML
    private Button infoButton;

    @FXML
    void hiScoreEvent(ActionEvent event) {

    }

    @FXML
    void infoEvent(ActionEvent event) {

    }

    @FXML
    void switchGame(ActionEvent event){
        changeStage(event,"fxml/gameScreen.fxml");
    }

    @FXML
    void switchAutoplay(ActionEvent event){

    }

    @FXML
    void switchHighScore(ActionEvent event){
        changeStage(event,"fxml/HighScoreScreen.fxml");
    }

    @FXML
    void switchSettings(ActionEvent event){
        changeStage(event,"fxml/SettingsScreen.fxml");
    }




    @FXML
    void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert autoplayButton != null : "fx:id=\"autoplayButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'menuScreen.fxml'.";
    }
}

