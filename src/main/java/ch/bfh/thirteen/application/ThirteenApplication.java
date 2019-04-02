package main.java.ch.bfh.thirteen.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.model.Board;
import main.java.ch.bfh.thirteen.settings.Settings;

import java.util.Objects;

public class ThirteenApplication extends Application {
    private static Board gameBoard;
    @Override
    public void init() throws Exception{
        Settings.loadResources();
        gameBoard = new Board(Settings.getBoardWidth(), Settings.getBoardHeight());
        //System.out.println(gameBoard.toSting());
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

        /*Scanner userinput = new Scanner(System.in);
        //use this to test the logic
        while(true){
            String coordinates = userinput.nextLine();
            int x = Integer.parseInt(coordinates.split(",")[0]);
            int y = Integer.parseInt(coordinates.split(",")[1]);
            Field f = gameBoard.getField(x-1,gameBoard.getHeight()-(y));
            if(gameBoard.isClickable(f)){
                System.out.println(f.toString());
                f.click();
            }else{
                System.out.println("Invalid Field");
            }
        }*/
    }

    public static Board getBoard(){
        return gameBoard;
    }
}
