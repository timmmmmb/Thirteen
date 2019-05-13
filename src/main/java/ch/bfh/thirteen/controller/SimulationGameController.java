package main.java.ch.bfh.thirteen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.bots.BotInterface;
import main.java.ch.bfh.thirteen.bots.RandomBot;
import main.java.ch.bfh.thirteen.model.GameState;

import static main.java.ch.bfh.thirteen.application.ThirteenApplication.getGame;
import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class SimulationGameController extends GameScreenController {
    private BotInterface bot;
    @FXML
    private ChoiceBox botSelector;
    private final Duration animationTime = Duration.millis(50);
    private boolean running = false;
    @FXML
    private Button startButton;

    @FXML
    public void runSimulation() {
        if(running){
            switchRunning();
            return;
        }
        if (botSelector.getValue().equals("RandomBot")) {
            bot = new RandomBot();
        } else if (botSelector.getValue().equals("TopDown")) {
            bot = new RandomBot();
        } else if (botSelector.getValue().equals("BottomUp")) {
            bot = new RandomBot();
        }
        switchRunning();
        doTurn();
    }

    private void switchRunning(){
        running = !running;
        if(!running){
            startButton.setText("Start");
        }else{
            startButton.setText("Stop");
        }
    }

    @Override
    public void endTurn() {
        super.endTurn();
        doTurn();
    }

    private void doTurn() {
        System.out.println(getGame().toString());
        if (running && ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            bot.doTurn(ThirteenApplication.getGame());
            super.playAnimations(0);
        } else {
            System.out.println("Bot Simulation over");
            if(running){
                switchRunning();
            }
        }
        gameBackground.getChildren().clear();
    }

    @Override
    protected void click(MouseEvent event) {
    }

    @Override
    protected void switchMenu(ActionEvent event) {
        switchRunning();
        getGame().getPcs().removePropertyChangeListener(this);
        restart();
        changeStage(event, "fxml/menuScreen.fxml");
    }



}
