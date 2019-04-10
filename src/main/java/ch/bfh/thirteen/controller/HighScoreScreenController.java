package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static main.java.ch.bfh.thirteen.settings.Settings.changeStage;

public class HighScoreScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label title;

    @FXML
    private Button backButton;

    @FXML
    void switchMenu(ActionEvent event) {
        changeStage(event, "fxml/menuScreen.fxml");
    }

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'HighScoreScreen.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'HighScoreScreen.fxml'.";

    }
}