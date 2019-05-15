package main.java.ch.bfh.thirteen.stagechanger;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class StageChanger {
    /**
     * this function changes the stage of the application to another fxml file
     * @param event an ActionEvent that was used when pressing the change stage button
     * @param filename the filename to the new fxml file that should get loaded
     */
    public static void changeStage(ActionEvent event, String filename){
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(StageChanger.class.getClassLoader().getResource(filename)));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
            ThirteenApplication.log.log("failed to change stage", Level.SEVERE);
        }
    }
}
