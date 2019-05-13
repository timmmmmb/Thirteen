package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Game;

public interface BotInterface {
    /**
     * use this function to implement the logic of the bot
     * @param game the current game
     */
    void doTurn(Game game);
}
