package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.bots.BotInterface;
import main.java.ch.bfh.thirteen.bots.BottomUpBot;
import main.java.ch.bfh.thirteen.bots.RandomBot;
import main.java.ch.bfh.thirteen.bots.TopDownBot;
import main.java.ch.bfh.thirteen.model.GameState;

/**
 * controller class for the simulated autoplay game
 */
public class SimulationGameController extends GameScreenController {
    private BotInterface bot;
    @FXML
    private ChoiceBox botSelector;
    private boolean running = false;
    @FXML
    private Button startButton;

    /**
     * initializing the screen for the autoplay game
     */
    @FXML
    protected void initialize() {
        simulation = true;
        super.initialize();
        animationTime = Duration.millis(5);
    }

    /**
     * runs simulation according to the selected bot
     */
    @FXML
    public void runSimulation() {
        if (running) {
            switchRunning();
            return;
        }
        if (botSelector.getValue().equals("Random")) {
            bot = new RandomBot();
        } else if (botSelector.getValue().equals("TopDown")) {
            bot = new TopDownBot();
        } else if (botSelector.getValue().equals("BottomUp")) {
            bot = new BottomUpBot();
        }
        switchRunning();
        doTurn();
    }

    /**
     * sets the text on the button to let the player start or stop the game
     */
    private void switchRunning() {
        running = !running;
        if (!running) {
            startButton.setText("Start");
        } else {
            startButton.setText("Stop");
            if (ThirteenApplication.getGame().getBoard().getGameState() == GameState.LOST || ThirteenApplication.getGame().getBoard().getGameState() == GameState.WON) {
                restart();
            }
        }
    }

    /**
     * lets bot ends the turn and go to next turn
     */
    @Override
    public void endTurn() {
        super.endTurn();
        doTurn();
    }

    /**
     * checks if the game is not lost and then does the next turn with the animations
     * if the game is lost the simulation will end
     */
    private void doTurn() {
        if (running && ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            bot.doTurn(ThirteenApplication.getGame());
            super.playAnimations(0);
        } else {
            System.out.println("Bot Simulation over");
            if (running) {
                switchRunning();
            }
        }
        gameBackground.getChildren().clear();
    }

    /**
     * lets the player switch back to the menu
     * @param event stage change event
     */
    @Override
    protected void switchMenu(ActionEvent event) {
        switchRunning();
        restart();
        // call the switch from the parent
        super.switchMenu(event);
    }


}
