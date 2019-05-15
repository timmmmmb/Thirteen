package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.saver.Saver;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class MenuScreenController {



    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton, loadButton, autoplayButton, hiScoreButton, infoButton, saveButton;


    @FXML
    private ImageView image;

    @FXML
    void hiScoreEvent(ActionEvent event) {

    }

    @FXML
    void infoEvent(ActionEvent event) {

    }

    @FXML
    void switchGame(ActionEvent event) {
        changeStage(event, "fxml/gameScreen.fxml");
        ThirteenApplication.log.log("new game started", Level.INFO);
    }

    @FXML
    void switchAutoplay(ActionEvent event) {
        changeStage(event, "fxml/simulationScreen.fxml");
        ThirteenApplication.log.log("new autoplay game started", Level.INFO);
    }

    @FXML
    void switchHighScore(ActionEvent event) {
        changeStage(event, "fxml/highScoreScreen.fxml");
    }

    @FXML
    void switchSettings(ActionEvent event) {
        changeStage(event, "fxml/settingsScreen.fxml");
    }

    @FXML
    void save() {
        try {
            Saver.save(ThirteenApplication.getGame());
        } catch (JAXBException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("saving game failed", Level.SEVERE);
        }
        ThirteenApplication.log.log("game saved successfully", Level.FINE);
    }

    @FXML
    void load() {
        try {
            ThirteenApplication.setGame(Loader.load());
            ThirteenApplication.getGame().addPCL();
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("loading game failed", Level.SEVERE);
        }
        ThirteenApplication.log.log("game loaded successfully", Level.FINE);
    }


    @FXML
    void initialize() {
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert autoplayButton != null : "fx:id=\"autoplayButton\" was not injected: check your FXML file 'menuScreen.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'menuScreen.fxml'.";
    }
}

