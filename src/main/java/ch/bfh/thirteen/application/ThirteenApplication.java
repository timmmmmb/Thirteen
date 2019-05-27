package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.logger.FileLogger;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.musicplayer.MusicPlayer;
import main.java.ch.bfh.thirteen.saver.Saver;
import main.java.ch.bfh.thirteen.settings.Settings;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.logging.Level;

public class ThirteenApplication extends Application {
    private static Game game;
    private static MusicPlayer music;
    private static Settings settings;
    public static final FileLogger log = new FileLogger();

    /**
     * this function is called before the application is started
     * it loads music and images
     */
    @Override
    public void init() {
        try {
            settings = Loader.loadSettings();
        } catch (FileNotFoundException fe) {
            log.log("settings File not found", Level.SEVERE);
            settings = new Settings();
            try {
                Saver.saveSettings(settings);
            } catch (JAXBException e) {
                log.log(e.toString(), Level.SEVERE);
            }
        } catch (JAXBException e) {
            log.log(e.toString(), Level.SEVERE);
        }
        settings.loadResources();
        game = new Game();
        try {
            music = new MusicPlayer("music/bensound-dreams.mp3");
            if (getSettings().isMusicOn()) {
                music.play();
            }
        } catch (URISyntaxException e) {
            log.log(e.getMessage(), Level.SEVERE);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * this function is called after the init it creates the ui and displays it
     *
     * @param gameStage the stage that shall display the application
     * @throws IOException if the fxml file was not found
     */
    @Override
    public void start(Stage gameStage) throws IOException {
        // loads the fxml from the resources
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/menuScreen.fxml")));

        Scene menuScene = new Scene(root);

        gameStage.getIcons().add(settings.getGameIcon());
        gameStage.setTitle("");
        gameStage.setResizable(false);
        gameStage.setScene(menuScene);
        gameStage.show();

        log.log("application started", Level.INFO);


    }

    /**
     * used to get the musicPlayer from the application
     *
     * @return the music player of the application
     */
    public static MusicPlayer getMusic() {
        return music;
    }

    /**
     * gets the settings from the application
     *
     * @return the settings of the application
     */
    public static Settings getSettings() {
        return settings;
    }

    /**
     * gets the game from the application
     *
     * @return the game of the application
     */
    public static Game getGame() {
        return game;
    }

    /**
     * sets the game from in application
     */
    public static void setGame(Game game) {
        ThirteenApplication.game = game;
    }


}
