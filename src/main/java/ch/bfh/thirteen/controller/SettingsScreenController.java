package main.java.ch.bfh.thirteen.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    void switchMenuScreen(MouseEvent event) {

        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/menuScreen.fxml")));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
        }

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