package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;

import java.util.ArrayList;

/**
 * this bot always uses the last clickable field
 */
public class BottomUpBot implements BotInterface {
    /**
     * does the turn which is clicking the last field
     * @param game the current game
     */
    @Override
    public void doTurn(Game game) {
        ArrayList<Field> cf = game.getBoard().getClickableFields();
        if(cf != null){
            Field f = cf.get(cf.size()-1);
            System.out.println("BottomUpBot clicked Field: "+f.toString());
            game.clickField(f);
            game.getBoard().finishAnimation();
        }
    }
}
