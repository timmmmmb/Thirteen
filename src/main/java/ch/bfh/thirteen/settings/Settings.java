package main.java.ch.bfh.thirteen.settings;

import javafx.scene.image.Image;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
        if(this.stars >= stars){
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
}
