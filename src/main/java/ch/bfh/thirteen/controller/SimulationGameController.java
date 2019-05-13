package main.java.ch.bfh.thirteen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.bots.BotInterface;
import main.java.ch.bfh.thirteen.bots.RandomBot;
import main.java.ch.bfh.thirteen.model.GameState;

public class SimulationGameController extends GameScreenController {
    private BotInterface bot;
    @FXML
    private ChoiceBox botSelector;
    private final Duration animationTime = Duration.millis(100);

    @FXML
    public void runSimulation(){
        bot = new RandomBot();
        doTurn();
    }
    @Override
    public void endTurn(){
        super.endTurn();
        doTurn();

    }

    private void doTurn(){
        if(ThirteenApplication.getGame().getBoard().getGameState()!= GameState.LOST&&ThirteenApplication.getGame().getBoard().getGameState()!= GameState.WON){
            bot.doTurn(ThirteenApplication.getGame());
            super.playAnimations(0);
        }else{
            System.out.println("Bot Simulation over");
        }
        gameBackground.getChildren().clear();
    }
    @Override
    protected void click(MouseEvent event) {
    }

}
