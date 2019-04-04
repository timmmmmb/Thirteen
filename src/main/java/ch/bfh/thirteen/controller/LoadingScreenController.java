package main.java.ch.bfh.thirteen.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

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

        RotateTransition rt = new RotateTransition(Duration.millis(3000), loadImage);
        rt.setByAngle(360);
        rt.setCycleCount(10);

        rt.play();
    }
}
