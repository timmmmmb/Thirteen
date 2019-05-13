package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.loader.Loader;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.MusicPlayer;
import main.java.ch.bfh.thirteen.saver.Saver;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.io.FileNotFoundException;
import java.util.Objects;

public class ThirteenApplication extends Application {
    private static Game game;
    private static MusicPlayer music;
    private static Settings settings;
    @Override
    public void init() throws Exception {
        try{
            settings = Loader.loadSettings();
        }catch (FileNotFoundException fe){
            System.out.println("Settings File not found");
            settings = new Settings();
            Saver.saveSettings(settings);
        }
        settings.loadResources();
        game = new Game();
        music = new MusicPlayer("resources/music/bensound-dreams.mp3");
        music.play();
        Thread.sleep(1000);
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
        ThirteenApplication.game =game;
    }

}
