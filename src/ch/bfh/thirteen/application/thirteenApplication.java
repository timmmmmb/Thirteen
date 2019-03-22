package ch.bfh.thirteen.application;

import ch.bfh.thirteen.model.main.Board;
import ch.bfh.thirteen.settings.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class thirteenApplication extends Application {

    @Override
    public void start(Stage gameStage) throws Exception {
        // loads the fxml from the view package
        Parent root = FXMLLoader.load(getClass().getResource("../view/loadingScreen.fxml"));
        Board gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());
        Scene gameScene = new Scene(root, 320, 340);

        gameStage.getIcons().add(new Image("images/icon.png"));
        gameStage.setTitle("");
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gameStage.show();

    }
}
