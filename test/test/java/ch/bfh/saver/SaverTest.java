package test.java.ch.bfh.saver;

import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.saver.Saver;
import main.java.ch.bfh.thirteen.settings.Settings;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SaverTest extends ApplicationTest {
    @Override public void start(Stage stage) {
        new ThirteenApplication().init();
    }


    @Test
    void saveGameTest() throws JAXBException, FileNotFoundException {
        Game game = new Game();
        Saver.save(game);
        Game loadedGame = Loader.loadGame();
        assertNotNull(loadedGame);
        assertEquals(game.toString(),loadedGame.toString());
    }

    @Test
    void saveSettingsTest() throws JAXBException, FileNotFoundException {
        Settings settings = new Settings();
        Settings loadedSettings = Loader.loadSettings();
        assertNotNull(loadedSettings);
        assertEquals(loadedSettings.toString(),settings.toString());
    }
}
