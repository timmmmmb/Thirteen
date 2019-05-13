package main.java.ch.bfh.thirteen.settings;

import javafx.scene.image.Image;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.model.GameState;
import main.java.ch.bfh.thirteen.score.Score;
import main.java.ch.bfh.thirteen.score.ScoreComparator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "Settings")
public class Settings {

    @XmlElement(name = "BOMBINCREMENTCOST")
    private final int BOMBINCREMENTCOST = 50;
    @XmlElement(name = "UNDOINCREMENTCOST")
    private final int UNDOINCREMENTCOST = 50;

    @XmlElement(name = "fieldWidth")
    private final int fieldWidth = 64;
    @XmlElement(name = "fieldHeight")
    private final int fieldHeight = 64;
    @XmlElement(name = "boardHeight")
    private final int boardHeight = 5;
    @XmlElement(name = "boardWidth")
    private final int boardWidth = 5;
    private Image gameIcon;
    @XmlElement(name = "Stars")
    private int stars = 0;

    @XmlElement(name = "scores")
    private ArrayList<Score> scores = new ArrayList<>();
    public Settings(){

    }

    public int getStars() {
        return this.stars;
    }

    /**
     * decreses the stars with the amount specified and returns true if there are enough stars and false if there aren't
     * @param stars the amount of stars to decrease the stars with
     * @return true if the stars were decreased and false if there are not enough stars
     */
    public boolean decreaseStars(int stars) {
        if(this.stars < stars){
            return false;
        }
        this.stars += stars;
        return true;
    }


    public void increaseStars(int stars) {
        this.stars += stars;
    }

    /**
     * this function loads all of the resources at the start of the game
     */
    public void loadResources() {
        gameIcon = new Image("images/13_logo.png");
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public Image getGameIcon() {
        return gameIcon;
    }

    public int getBOMBINCREMENTCOST() {
        return BOMBINCREMENTCOST;
    }

    public int getUNDOINCREMENTCOST() {
        return UNDOINCREMENTCOST;
    }

    public int getHighscore() {
        return scores.get(0).getMoves();
    }

    public void setHighscore() {
        scores.add(new Score(ThirteenApplication.getGame().getMoves(),ThirteenApplication.getGame().getBoard().getCurrent_max(),ThirteenApplication.getGame().getTimer().getTime(),ThirteenApplication.getGame().getBoard().getGameState()== GameState.WON));
        scores.sort(new ScoreComparator());
    }
}
