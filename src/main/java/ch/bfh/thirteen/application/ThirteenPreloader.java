package main.java.ch.bfh.thirteen.application;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ThirteenPreloader extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/loadingScreen.fxml")));
        Scene scene = new Scene(root);


        preloaderStage.getIcons().add(new Image("images/13_logo.png"));
        preloaderStage.setTitle("");
        preloaderStage.setResizable(false);
        preloaderStage.setScene(scene);
        preloaderStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
