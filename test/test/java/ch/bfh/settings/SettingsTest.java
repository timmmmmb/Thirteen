package test.java.ch.bfh.settings;

import main.java.ch.bfh.thirteen.score.Score;
import main.java.ch.bfh.thirteen.settings.Settings;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
     * tests if the togglemusic function works
     */
    @Test
    void toggleMusic(){
        Settings settings = new Settings();
        assertTrue(settings.isMusicOn());
        settings.toggleMusic();
        assertTrue(!settings.isMusicOn());
        settings.toggleMusic();
        assertTrue(settings.isMusicOn());
    }
}
