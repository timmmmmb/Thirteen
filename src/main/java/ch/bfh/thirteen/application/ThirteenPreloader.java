package main.java.ch.bfh.thirteen.application;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.util.Objects;

public class ThirteenPreloader extends Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/loadingScreen.fxml")));
        Scene scene = new Scene(root);


        primaryStage.getIcons().add(new Image("images/icon.png"));
        primaryStage.setTitle("");
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
