package test.java.ch.bfh.settings;

import main.java.ch.bfh.thirteen.score.Score;
import main.java.ch.bfh.thirteen.settings.Settings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest {
    /**
     * tests if the constructor works and gets all of the default values
     */
    @Test
    void constructorTest(){
        Settings settings = new Settings();
        assertEquals(settings.getScores(), new ArrayList<Score>());
        assertEquals(settings.getFieldHeight(), 64);
        assertEquals(settings.getFieldWidth(), 64);
        assertEquals(settings.getStars(), 0);
        assertEquals(settings.getBoardHeight(), 5);
        assertEquals(settings.getBoardWidth(), 5);
        assertTrue(settings.isMusicOn());
        assertEquals(settings.getBOMBINCREMENTCOST(), 50);
        assertEquals(settings.getUNDOINCREMENTCOST(), 50);
    }

    /**
     * tests if the toggle music function works
     */
    @Test
    void toggleMusicTest(){
        Settings settings = new Settings();
        assertTrue(settings.isMusicOn());
        settings.toggleMusic();
        assertTrue(!settings.isMusicOn());
        settings.toggleMusic();
        assertTrue(settings.isMusicOn());
    }

    /**
     * tests if the toggle music function works
     */
    @Test
    void increaseStarsTest(){
        Settings settings = new Settings();
        assertEquals(0,settings.getStars());
        settings.increaseStars(50);
        assertEquals(settings.getStars(),50);
        assertFalse(settings.decreaseStars(20));
        assertEquals(settings.getStars(),30);
        assertTrue(settings.decreaseStars(100));
        assertEquals(settings.getStars(),30);
    }

    /**
     * tests if the score functions work
     */
    @Test
    void scoreFunctionsTest(){
        Settings settings = new Settings();
        assertNull(settings.getHighscore());
        Score sc1 = new Score(6, 1, 10, false);
        Score sc2 = new Score(7, 1, 10, false);
        Score sc3 = new Score(7, 20, 10, false);
        Score sc4 = new Score(11, 1, 10, false);
        settings.setScore(sc1);
        assertEquals(sc1,settings.getHighscore());
        settings.setScore(sc2);
        assertEquals(sc2,settings.getHighscore());
        settings.setScore(sc3);
        assertEquals(sc3,settings.getHighscore());
        settings.setScore(sc4);
        assertEquals(sc4,settings.getHighscore());
    }
}
