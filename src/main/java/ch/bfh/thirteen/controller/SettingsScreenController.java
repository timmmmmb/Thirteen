package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static main.java.ch.bfh.thirteen.settings.Settings.changeStage;

public class SettingsScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label langageLabel;

    @FXML
    private Label musicLabel;

    @FXML
    private ToggleButton musicButton;

    @FXML
    private Button infoButton;

    @FXML
    private Label infoLabel;

    @FXML
    private Button backButton;

    @FXML
    void musicOnOff(MouseEvent event) {

    }

    @FXML
    void selectLanguage(MouseEvent event) {

    }

    @FXML
    void switchInfoScreen(MouseEvent event) {

    }

    @FXML
    void switchMenuScreen(ActionEvent event) {
        changeStage(event, "fxml/menuScreen.fxml");
    }

    @FXML
    void initialize() {
        assert langageLabel != null : "fx:id=\"langageLabel\" was not injected: check your FXML file 'SettingsScreen.fxml'.";
        assert musicLabel != null : "fx:id=\"musicLabel\" was not injected: check your FXML file 'SettingsScreen.fxml'.";
        assert musicButton != null : "fx:id=\"musicButton\" was not injected: check your FXML file 'SettingsScreen.fxml'.";
        assert infoButton != null : "fx:id=\"infoButton\" was not injected: check your FXML file 'SettingsScreen.fxml'.";
        assert infoLabel != null : "fx:id=\"infoLabel\" was not injected: check your FXML file 'SettingsScreen.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'SettingsScreen.fxml'.";

    }
}