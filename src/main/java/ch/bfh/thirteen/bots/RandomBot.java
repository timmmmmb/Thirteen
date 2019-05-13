package main.java.ch.bfh.thirteen.bots;

import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot implements BotInterface{
    private Random r = new Random();
    @Override
    public void doTurn(Game game) {
        ArrayList<Field> cf = game.getBoard().getClickableFields();
        Field f = cf.get(r.nextInt(cf.size()));
        game.clickField(f);
        System.out.println(game.getBoard().toSting());
    }
}
