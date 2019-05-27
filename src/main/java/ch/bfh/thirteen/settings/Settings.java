package main.java.ch.bfh.thirteen.settings;

import javafx.scene.image.Image;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.observer.CustomPropertyChangeSupport;
import main.java.ch.bfh.thirteen.saver.Saver;
import main.java.ch.bfh.thirteen.score.Score;
import main.java.ch.bfh.thirteen.score.ScoreComparator;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * class to change some of the settings such as stars, music and high scores in the game
 */
@SuppressWarnings("SpellCheckingInspection")
@XmlRootElement(name = "Settings")
public class Settings {

    @XmlElement(name = "musicOn")
    private boolean musicOn = true;
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
    private final ArrayList<Score> scores = new ArrayList<>();
    private final CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);

    public Settings() {
    }

    /**
     * checks if music is on
     * @return boolean if the music is on
     */
    public boolean isMusicOn() {
        return musicOn;
    }

    /**
     * gets the amount of stars
     * @return int of the amount of stars
     */
    public int getStars() {
        return this.stars;
    }

    /**
     * decreases the stars with the amount specified and returns true if there are enough stars and false if there aren't
     *
     * @param stars the amount of stars to decrease the stars with
     * @return false if the stars were decreased and true if there are not enough stars
     */
    public boolean decreaseStars(int stars) {
        if (this.stars < stars) {
            return true;
        }
        getPcs().firePropertyChange("StarsChanged", this.stars, this.stars - stars);
        this.stars -= stars;
        // save the settings
        try {
            Saver.saveSettings(this);
        } catch (JAXBException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("failed to decrease stars", Level.SEVERE);
        }
        return false;
    }


    /**
     * increases the amount of stars and saves it
     * @param stars the amount to increase the stars by
     */
    public void increaseStars(int stars) {
        getPcs().firePropertyChange("StarsChanged", this.stars, this.stars + stars);
        this.stars += stars;
        // save the settings
        try {
            Saver.saveSettings(this);
        } catch (JAXBException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("failed to increase stars", Level.SEVERE);
        }
    }

    /**
     * this function loads all of the resources at the start of the game
     */
    public void loadResources() {
        gameIcon = new Image("images/13_logo.png");
    }

    /**
     * gets the width of the field
     * @return field width
     */
    public int getFieldWidth() {
        return fieldWidth;
    }

    /**
     * gets the height of the field
     * @return field height
     */
    public int getFieldHeight() {
        return fieldHeight;
    }

    /**
     * gets the height of the game board
     * @return game board height
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * gets the width of the game board
     * @return game board width
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * gets the game icon
     * @return game icon
     */
    public Image getGameIcon() {
        return gameIcon;
    }

    /**
     * gets the cost of stars for the bomb
     * @return amount of stars
     */
    public int getBOMBINCREMENTCOST() {
        return BOMBINCREMENTCOST;
    }

    /**
     * gets the cost of stars for the undo
     * @return amount of stars
     */
    public int getUNDOINCREMENTCOST() {
        return UNDOINCREMENTCOST;
    }

    /**
     * gets the best high score
     * @return high score if no scores return null
     */
    public Score getHighscore() {
        if(scores.isEmpty())
            return null;
        return scores.get(0);
    }

    /**
     * gets the list with the high scores
     * @return list of high scores
     */
    public ArrayList<Score> getScores() {
        return scores;
    }

    /**
     * compares the high score and saves it to the list if it was higher than a previous one in the list
     */
    public void setScore(Score score) {
        scores.add(score);
        scores.sort(new ScoreComparator());
        try {
            Saver.saveSettings(this);
        } catch (JAXBException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("failed to set high score", Level.SEVERE);
        }
        ThirteenApplication.log.log("high score set", Level.FINE);
    }

    /**
     * gets the property change support
     * @return property change support
     */
    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    /**
     * toggles the music
     */
    public void toggleMusic() {
        musicOn = !musicOn;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "musicOn=" + musicOn +
                ", BOMBINCREMENTCOST=" + BOMBINCREMENTCOST +
                ", UNDOINCREMENTCOST=" + UNDOINCREMENTCOST +
                ", fieldWidth=" + fieldWidth +
                ", fieldHeight=" + fieldHeight +
                ", boardHeight=" + boardHeight +
                ", boardWidth=" + boardWidth +
                ", gameIcon=" + gameIcon +
                ", stars=" + stars +
                '}';
    }
}
