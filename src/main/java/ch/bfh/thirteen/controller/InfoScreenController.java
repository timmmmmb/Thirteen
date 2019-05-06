package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class InfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;

    @FXML
    private Label description;

    @FXML
    private Label credits;

    @FXML
    void switchMenuScreen(ActionEvent event) {
        changeStage(event, "fxml/settingsScreen.fxml");
    }



    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert credits != null : "fx:id=\"credits\" was not injected: check your FXML file 'infoScreen.fxml'.";

    }
}
