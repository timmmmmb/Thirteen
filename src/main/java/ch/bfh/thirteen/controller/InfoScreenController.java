package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

/**
 * controller class for the info screen
 */
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

    /**
     * lets the player change back to the menu screen
     * @param event change stage event
     */
    @FXML
    void switchMenuScreen(ActionEvent event) {
        changeStage(event, "fxml/settingsScreen.fxml");
    }

    /**
     * initializing the info screen
     */
    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'infoScreen.fxml'.";
        assert credits != null : "fx:id=\"credits\" was not injected: check your FXML file 'infoScreen.fxml'.";


    }
}
