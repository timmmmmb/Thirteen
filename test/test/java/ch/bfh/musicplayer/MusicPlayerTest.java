package test.java.ch.bfh.musicplayer;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.musicplayer.MusicPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class MusicPlayerTest {

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) {
        stage.setScene(new Scene(new StackPane(), 100, 100));
    }

    /**
     * tests the constructor
     */
    @Test
    void constructorTest() throws URISyntaxException {
        MusicPlayer musicPlayer = new MusicPlayer("music/bensound-dreams.mp3");
        assertNotNull(musicPlayer);
    }

    /**
     * tests if you can play and pause without exception
     */
    @Test
    void playTest() throws URISyntaxException {
        MusicPlayer musicPlayer = new MusicPlayer("music/bensound-dreams.mp3");
        musicPlayer.play();
        musicPlayer.stop();
        // it only gets here if there was no exception
        assertTrue(true);
    }
}
