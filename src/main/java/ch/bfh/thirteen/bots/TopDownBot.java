package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;

import java.util.ArrayList;

/**
 * this bot clicks the first clickable field
 */
public class TopDownBot implements BotInterface {
    /**
     * does the turn which is clicking the first clickable field
     *
     * @param game the current game
     */
    @Override
    public void doTurn(Game game) {
        ArrayList<Field> cf = game.getBoard().getClickableFields();
        if (cf != null) {
            Field f = cf.get(0);
            System.out.println("TopDownBot clicked Field: " + f.toString());
            game.clickField(f);
            game.getBoard().finishAnimation();
        }
    }
}
