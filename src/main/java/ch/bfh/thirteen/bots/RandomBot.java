package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;

import java.util.ArrayList;
import java.util.Random;

/**
 * this bots clicks a random clickable field
 */
public class RandomBot implements BotInterface{
    private Random r = new Random();
    /**
     * does the turn which is selecting a random clickable field
     * @param game the current game
     */
    @Override
    public void doTurn(Game game) {
        ArrayList<Field> cf = game.getBoard().getClickableFields();
        Field f = cf.get(r.nextInt(cf.size()));
        System.out.println("RandomBot clicked Field: "+f.toString());
        game.clickField(f);
        game.getBoard().finishAnimation();
    }
}
