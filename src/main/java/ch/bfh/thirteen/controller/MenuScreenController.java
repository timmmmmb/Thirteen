package main.java.ch.bfh.thirteen.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    void autoplayEvent(ActionEvent event) {

    }

    @FXML
    void startEvent(ActionEvent event) {

    }

    @FXML
    void hiScoreEvent(ActionEvent event) {

    }

    @FXML
    void infoEvent(ActionEvent event) {

    }

    @FXML
    void switchGame(MouseEvent event){
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/gameScreen.fxml")));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchAutoplay(MouseEvent event){

    }

    @FXML
    void switchHighScore(MouseEvent event){

        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/HighScoreScreen.fxml")));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchSettings(MouseEvent event){

        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/SettingsScreen.fxml")));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert autoplayButton != null : "fx:id=\"autoplayButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'menuScreen.fxml'.";

    }
}

