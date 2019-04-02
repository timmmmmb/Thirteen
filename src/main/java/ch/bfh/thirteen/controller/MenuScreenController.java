package main.java.ch.bfh.thirteen.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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
    void autoplayEvent(ActionEvent event) {

    }

    @FXML
    void startEvent(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert autoplayButton != null : "fx:id=\"autoplayButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'menuScreen.fxml'.";

    }
}

