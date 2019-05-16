package main.java.ch.bfh.thirteen.application;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ThirteenPreloader extends Preloader {
    private Stage preloaderStage;

    /**
     * loads and displays a loadingscreen
     *
     * @param primaryStage the primary stage of the preloader
     * @throws IOException when the file is not found
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.preloaderStage = primaryStage;

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/loadingScreen.fxml")));
        Scene scene = new Scene(root);

        preloaderStage.getIcons().add(new Image("images/13_logo.png"));
        preloaderStage.setTitle("");
        preloaderStage.setResizable(false);
        preloaderStage.setScene(scene);
        preloaderStage.show();
    }

    /**
     * when the main application this function gets called and the preloader hides
     *
     * @param stateChangeNotification the notification that tells the state has changed
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}
