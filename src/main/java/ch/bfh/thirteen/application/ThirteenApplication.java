package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.logger.FileLogger;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.MusicPlayer;
import main.java.ch.bfh.thirteen.saver.Saver;
import main.java.ch.bfh.thirteen.settings.Settings;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.logging.Level;

public class ThirteenApplication extends Application {
    private static Game game;
    private static MusicPlayer music;
    private static Settings settings;
    public static FileLogger log = new FileLogger();

    @Override
    public void init()  {
        try {
            settings = Loader.loadSettings();
        } catch (FileNotFoundException fe) {
            log.log("settings File not found",Level.SEVERE);
            settings = new Settings();
            try {
                Saver.saveSettings(settings);
            } catch (JAXBException e) {
                log.log(e.toString(),Level.SEVERE);
            }
        } catch (JAXBException e) {
            log.log(e.toString(),Level.SEVERE);
        }
        settings.loadResources();
        game = new Game();
        music = new MusicPlayer("resources/music/bensound-dreams.mp3");
        if (getSettings().isMusicOn()) {
            music.play();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage gameStage) throws Exception {
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

    public static MusicPlayer getMusic() {
        return music;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        ThirteenApplication.game = game;
    }



}
