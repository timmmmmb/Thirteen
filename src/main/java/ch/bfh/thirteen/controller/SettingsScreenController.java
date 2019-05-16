package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.saver.Saver;

import javax.xml.bind.JAXBException;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

/**
 * controller class for the settings screen
 */
public class SettingsScreenController {

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

    /**
     * lets the player turn on or off the music
     * changes text of toggle button
     */
    @FXML
    void musicOnOff() {
        if (ThirteenApplication.getSettings().isMusicOn()) {
            musicButton.setText("off");
            ThirteenApplication.getMusic().stop();
        } else if (!ThirteenApplication.getSettings().isMusicOn()) {
            musicButton.setText("on");
            ThirteenApplication.getMusic().play();
        }
        ThirteenApplication.getSettings().toggleMusic();
        try {
            Saver.saveSettings(ThirteenApplication.getSettings());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * lets the player switch to the info screen
     *
     * @param event change stage event
     */
    @FXML
    void switchInfoScreen(ActionEvent event) {
        changeStage(event, "fxml/InfoScreen.fxml");
    }

    /**
     * lets the player go back to the menu screen
     *
     * @param event change stage event
     */
    @FXML
    void switchMenuScreen(ActionEvent event) {
        changeStage(event, "fxml/menuScreen.fxml");
    }

    /**
     * initializing the settings screen
     */
    @FXML
    void initialize() {
        if (ThirteenApplication.getSettings().isMusicOn()) {
            musicButton.setText("on");
        } else if (!ThirteenApplication.getSettings().isMusicOn()) {
            musicButton.setText("off");
        }
        assert musicLabel != null : "fx:id=\"musicLabel\" was not injected: check your FXML file 'settingsScreen.fxml'.";
        assert musicButton != null : "fx:id=\"musicButton\" was not injected: check your FXML file 'settingsScreen.fxml'.";
        assert infoButton != null : "fx:id=\"infoButton\" was not injected: check your FXML file 'settingsScreen.fxml'.";
        assert infoLabel != null : "fx:id=\"infoLabel\" was not injected: check your FXML file 'settingsScreen.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'settingsScreen.fxml'.";

    }
}