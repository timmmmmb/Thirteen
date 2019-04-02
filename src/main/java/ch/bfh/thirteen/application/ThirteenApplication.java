package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.model.Board;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.util.Objects;

public class ThirteenApplication extends Application {
    private Board gameBoard;
    @Override
    public void init() throws Exception{
        Settings.loadResources();
        gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());
        System.out.println(gameBoard.toSting());
        Thread.sleep(1000);
    }

    @Override
    public void start(Stage gameStage) throws Exception {
        // loads the fxml from the view package
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/menuScreen.fxml")));

        Scene gameScene = new Scene(root);

        //gameScene.getStylesheets().add(getClass().getResource("css/menuScreen.css").toExternalForm());

        gameStage.getIcons().add(Settings.getGameIcon());
        gameStage.setTitle("");
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gameStage.show();


    }
}
