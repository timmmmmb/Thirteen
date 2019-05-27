package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

/**
 * controller class for the info screen
 */
public class InfoScreenController {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button backButton;

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
    }
}
