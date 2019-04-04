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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HighScoreScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label title;

    @FXML
    private Button backButton;

    @FXML
    void switchMenu(MouseEvent event) {

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
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'HighScoreScreen.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'HighScoreScreen.fxml'.";

    }
}