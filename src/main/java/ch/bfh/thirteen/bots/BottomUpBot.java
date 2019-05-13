package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;

import java.util.ArrayList;

public class BottomUpBot implements BotInterface {
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
