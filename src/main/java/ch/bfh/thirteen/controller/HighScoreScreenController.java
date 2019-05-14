package main.java.ch.bfh.thirteen.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.score.Score;

import static main.java.ch.bfh.thirteen.stagechanger.StageChanger.changeStage;

public class HighScoreScreenController {


    @FXML
    private AnchorPane pane;

    @FXML
    private Label title;

    @FXML
    private TableView<Score> scoreTable;

    @FXML
    private TableColumn<Score,Number> maxNumRow,movesRow,timeRow;
    @FXML
    private TableColumn<Score, String> dateRow;


    @FXML
    void switchMenu(ActionEvent event) {
        changeStage(event, "fxml/menuScreen.fxml");
    }

    @FXML
    void initialize() {
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'highScoreScreen.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'highScoreScreen.fxml'.";
        maxNumRow.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getHighestnumber()));
        movesRow.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getMoves()));
        timeRow.setCellValueFactory(c->new SimpleIntegerProperty(c.getValue().getTime()));
        dateRow.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getDate().toString()));

        scoreTable.getItems().addAll(ThirteenApplication.getSettings().getScores());
    }
}