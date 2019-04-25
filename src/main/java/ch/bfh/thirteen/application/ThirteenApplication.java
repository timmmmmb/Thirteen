package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.MusicPlayer;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.util.Objects;

public class ThirteenApplication extends Application {
    public static Game game;
    public static MusicPlayer music;
    @Override
    public void init() throws Exception {
        Settings.loadResources();
        game = new Game();
        music = new MusicPlayer("resources/music/bensound-dreams.mp3");
        music.play();
        Thread.sleep(1000);
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        // loads the fxml from the resources
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/menuScreen.fxml")));

        Scene gameScene = new Scene(root);

        gameStage.getIcons().add(Settings.getGameIcon());
        gameStage.setTitle("");
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gameStage.show();
    }

}
