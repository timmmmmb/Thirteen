package main.java.ch.bfh.thirteen.settings;

import javafx.scene.image.Image;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.model.GameState;
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

@XmlRootElement(name = "Settings")
public class Settings {

    @XmlElement(name = "musicOn")
    private boolean musicOn = true;
    @SuppressWarnings("")
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
    private CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);

    public Settings() {

    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public int getStars() {
        return this.stars;
    }

    /**
     * decreases the stars with the amount specified and returns true if there are enough stars and false if there aren't
     *
     * @param stars the amount of stars to decrease the stars with
     * @return true if the stars were decreased and false if there are not enough stars
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

    public Score getHighscore() {
        return scores.get(0);
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setHighscore() {
        scores.add(new Score(ThirteenApplication.getGame().getBoard().getCurrent_max(), ThirteenApplication.getGame().getMoves(), ThirteenApplication.getGame().getTimer().getTime(), ThirteenApplication.getGame().getBoard().getGameState() == GameState.WON));
        scores.sort(new ScoreComparator());
        try {
            Saver.saveSettings(this);
        } catch (JAXBException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("failed to set high score", Level.SEVERE);
        }
        ThirteenApplication.log.log("high score set", Level.FINE);
    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public void toggleMusic() {
        musicOn = !musicOn;
    }
}
