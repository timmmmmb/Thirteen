package test.java.ch.bfh.loader;

import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.settings.Settings;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoaderTest extends ApplicationTest {
    @Override public void start(Stage stage) {
        new ThirteenApplication().init();
    }


    @Test
    void loadGameTest() throws JAXBException, FileNotFoundException {
        Game game = (Game) Loader.load("testResources/ThirteenSave.xml", JAXBContext.newInstance(Game.class));
        assertNotNull(game);
        assertEquals(game.toString(),"2 0 0, 3 1 0, 1 2 0, 3 3 0, 2 4 0\n" +
                "2 0 1, 1 1 1, 3 2 1, 2 3 1, 2 4 1\n" +
                "1 0 2, 1 1 2, 2 2 2, 4 3 2, 4 4 2\n" +
                "2 0 3, 1 1 3, 2 2 3, 1 3 3, 2 4 3\n" +
                "4 0 4, 6 1 4, 8 2 4, 7 3 4, 4 4 4\n");
    }

    @Test
    void loadSettingsTest() throws JAXBException, FileNotFoundException {
        Settings settings = (Settings) Loader.load("testResources/Settings.xml", JAXBContext.newInstance(Settings.class));
        assertNotNull(settings);
        assertEquals(3120,settings.getStars());
    }
}
