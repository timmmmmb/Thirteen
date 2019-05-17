package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.saver.Saver;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.logging.Level;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

/**
 * The controller class for the menu screen
 */
public class MenuScreenController {

    /**
     * loads the game screen to start a new game
     * @param event gets the fxml for the game screen
     */
    @FXML
    void switchGame(ActionEvent event) {
        changeStage(event, "fxml/gameScreen.fxml");
        ThirteenApplication.log.log("new game started", Level.INFO);
    }

    /**
     * loads the autoplay screen
     * @param event gets the fxml for the autoplay game screen
     */
    @FXML
    void switchAutoplay(ActionEvent event) {
        changeStage(event, "fxml/simulationScreen.fxml");
        ThirteenApplication.log.log("new autoplay game started", Level.INFO);
    }

    /**
     * lets the player switch to the high score screen
     * @param event the event to get the fxml for the high score screen
     */
    @FXML
    void switchHighScore(ActionEvent event) {
        changeStage(event, "fxml/highScoreScreen.fxml");
    }

    /**
     * lets the player switch to the settings screen
     * @param event the event to get the fxml for the settings screen
     */
    @FXML
    void switchSettings(ActionEvent event) {
        changeStage(event, "fxml/settingsScreen.fxml");
    }

    /**
     * lets the player save the game
     * will write to the log file if saving was successful
     */
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

    /**
     * lets the player loadGame the game
     * will write to the log file if loading was successful
     */
    @FXML
    void load() {
        try {
            ThirteenApplication.setGame(Loader.loadGame());
            ThirteenApplication.getGame().addPCL();
            ThirteenApplication.log.log("game loaded successfully", Level.FINE);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("loading game failed", Level.SEVERE);
        }
    }
}

