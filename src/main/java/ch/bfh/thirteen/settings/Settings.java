package main.java.ch.bfh.thirteen.settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.model.Board;

import java.io.IOException;
import java.util.Objects;

public class Settings {

    private static Board gameBoard;
    private static final int fieldWidth = 64;
    private static final int fieldHeight = 64;
    private static final int boardHeight = 5;
    private static final int boardWidth = 5;
    private static Image gameIcon;

    /**
     * this function loads all of the resources at the start of the game
     */
    public static void loadResources() {
        gameIcon = new Image("images/13_logo.png");
    }

    public static int getFieldWidth() {
        return fieldWidth;
    }

    public static int getFieldHeight() {
        return fieldHeight;
    }

    private static int getBoardHeight() {
        return boardHeight;
    }

    private static int getBoardWidth() {
        return boardWidth;
    }

    public static Image getGameIcon() {
        return gameIcon;
    }

    public static void initializeBoard() {
        gameBoard = new Board(getBoardWidth(), getBoardHeight());
    }

    public static Board getBoard() {
        return gameBoard;
    }

    public static void changeStage(MouseEvent event, String filename){
        try {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Parent newroot = FXMLLoader.load(Objects.requireNonNull(Settings.class.getClassLoader().getResource(filename)));
            stage.setScene(new Scene(newroot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
