package main.java.ch.bfh.thirteen.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LoadingScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox hbox;

    @FXML
    private ImageView loadImage;

    @FXML
    void initialize() {
        assert hbox != null : "fx:id=\"hbox\" was not injected: check your FXML file 'loadingScreen.fxml'.";
        assert loadImage != null : "fx:id=\"loadImage\" was not injected: check your FXML file 'loadingScreen.fxml'.";

    }
}
