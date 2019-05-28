package test.java.ch.bfh.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import main.java.ch.bfh.thirteen.fieldlabel.FieldLabel;
import main.java.ch.bfh.thirteen.model.Field;
import main.java.ch.bfh.thirteen.model.Game;
import main.java.ch.bfh.thirteen.model.GameState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest {

    /**
     * used to initialize the UI
     *
     * @param stage a stage
     * @throws IOException thrown if a fxml file was not found
     */
    @Start
    private void start(Stage stage) throws IOException {
        new ThirteenApplication().init();
        new ThirteenApplication().start(stage);
    }

    /**
     * tests if the menu has all of the required buttons and if they work
     *
     * @param robot the robot that tests everything
     */
    @Test
    void menuTest(FxRobot robot) {
        // tests if all buttons exist
        Assertions.assertThat(robot.lookup("#saveButton").queryButton()).hasText("");
        Assertions.assertThat(robot.lookup("#loadButton").queryButton()).hasText("");
        Assertions.assertThat(robot.lookup("#startButton").queryButton()).hasText("Start");
        Assertions.assertThat(robot.lookup("#autoplayButton").queryButton()).hasText("Autoplay");
        Assertions.assertThat(robot.lookup("#hiScoreButton").queryButton()).hasText("");
        Assertions.assertThat(robot.lookup("#infoButton").queryButton()).hasText("");
    }

    /**
     * tests if you can click the options
     *
     * @param robot the robot that tests everything
     */
    @Test
    void optionsTest(FxRobot robot) {
        robot.clickOn("#infoButton");
        assertNotNull(robot.lookup("#musicButton").queryParent());
        Assertions.assertThat(((ToggleButton) robot.lookup("#musicButton").queryParent())).hasText("on");
        Assertions.assertThat(robot.lookup("#infoButton").queryButton()).hasText("?");
        Assertions.assertThat(robot.lookup("#backButton").queryButton()).hasText("Back");
        robot.clickOn("#musicButton");
        Assertions.assertThat(((ToggleButton) robot.lookup("#musicButton").queryParent())).hasText("off");
        robot.clickOn("#musicButton");
        Assertions.assertThat(((ToggleButton) robot.lookup("#musicButton").queryParent())).hasText("on");
        robot.clickOn("#infoButton");
        //it is here in the info screen
        Assertions.assertThat(robot.lookup("#backButton").queryButton()).hasText("Back");
        robot.clickOn("#backButton");

        // goes back to the menu
        Assertions.assertThat(robot.lookup("#backButton").queryButton()).hasText("Back");
        robot.clickOn("#backButton");
    }

    /**
     * tests if you can click the statistics
     *
     * @param robot the robot that tests everything
     */
    @Test
    void statisticsTest(FxRobot robot) {
        robot.clickOn("#hiScoreButton");

        Assertions.assertThat(robot.lookup("#backButton").queryButton()).hasText("Back");
        robot.clickOn("#backButton");
    }

    /**
     * tests if you can run the simulation with all bots
     *
     * @param robot the robot that tests everything
     */
    @Test
    void simulationTest(FxRobot robot) {
        robot.clickOn("#autoplayButton");
        Assertions.assertThat(robot.lookup("#startButton").queryButton()).hasText("Start");
        Assertions.assertThat(robot.lookup("#restartButton").queryButton()).hasText("Restart");
        Game currentgame = ThirteenApplication.getGame();
        robot.clickOn("#restartButton");
        assertSame(ThirteenApplication.getGame(), currentgame);
        robot.clickOn("#startButton");
        //wait until the game is over
        while (ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            robot.sleep(500);
        }
        robot.clickOn("#botSelector");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);

        robot.sleep(1000);
        robot.clickOn("#startButton");
        //wait until the game is over
        while (ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            robot.sleep(500);
        }

        robot.clickOn("#botSelector");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#startButton");
        //wait until the game is over
        while (ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            robot.sleep(500);
        }
        Assertions.assertThat(robot.lookup("#menuButton").queryButton()).hasText("");
        robot.clickOn("#menuButton");
    }

    /**
     * tests if you can run the game tests stars, bomb, undo
     *
     * @param robot the robot that tests everything
     */
    @Test
    void gameTest(FxRobot robot) {
        robot.clickOn("#startButton");
        //test if everything is here
        Assertions.assertThat(robot.lookup("#restartButton").queryButton()).hasText("Restart");
        Assertions.assertThat(robot.lookup("#undoButton").queryButton()).hasText("");
        Assertions.assertThat(robot.lookup("#deleteButton").queryButton()).hasText(" ");
        int oldstars = ThirteenApplication.getSettings().getStars();
        robot.clickOn("#restartButton");
        Assertions.assertThat(robot.lookup("#undoButton").queryButton()).isDisabled();
        if (oldstars < ThirteenApplication.getSettings().getBOMBINCREMENTCOST()) {
            Assertions.assertThat(robot.lookup("#deleteButton").queryButton()).isDisabled();
        }
        //set stars to 100
        Platform.runLater(() -> ThirteenApplication.getSettings().increaseStars(100 - ThirteenApplication.getSettings().getStars() + 1));

        int i = 0;
        //play the game
        while (i < 10 && ThirteenApplication.getGame().getBoard().getGameState() != GameState.LOST && ThirteenApplication.getGame().getBoard().getGameState() != GameState.WON) {
            // test the undo
            if (i == 5) {
                robot.clickOn("#undoButton");
                robot.clickOn("#rejectButton");
                robot.clickOn("#undoButton");
                robot.clickOn("#agreeButton");
                robot.sleep(1000);
                i++;
                continue;
            }
            //test the delete
            if (i == 3) {
                robot.clickOn("#deleteButton");
                robot.clickOn("#rejectButton");
                robot.clickOn("#deleteButton");
                robot.clickOn("#agreeButton");
                FieldLabel fl = getFieldLabel((robot.lookup("#gamePane")).queryParent().getChildrenUnmodifiable(), ThirteenApplication.getGame().getBoard().getClickableFields().get(0));
                robot.clickOn(fl);
                robot.sleep(1000);
                i++;
                continue;
            }
            //click field
            FieldLabel fl = getFieldLabel((robot.lookup("#gamePane")).queryParent().getChildrenUnmodifiable(), ThirteenApplication.getGame().getBoard().getClickableFields().get(0));
            oldstars = ThirteenApplication.getSettings().getStars();
            robot.clickOn(fl);
            assertEquals(oldstars + 1, ThirteenApplication.getSettings().getStars());
            robot.sleep(1000);
            i++;
        }

        Assertions.assertThat(robot.lookup("#menuButton").queryButton()).hasText("");
        robot.clickOn("#menuButton");
    }

    /**
     * used to get a field label
     *
     * @param fls a list of nodes
     * @param f   the searched field
     * @return the field as fl or null if not found
     */
    private FieldLabel getFieldLabel(ObservableList<Node> fls, Field f) {
        for (Node child : fls) {
            FieldLabel fl = (FieldLabel) child;
            int layoutX = (int) fl.getBoundsInParent().getMinX() / ThirteenApplication.getSettings().getFieldWidth();
            int layoutY = (int) fl.getBoundsInParent().getMinY() / ThirteenApplication.getSettings().getFieldHeight();
            if (layoutX == f.getCoordinate().getX() && layoutY == f.getCoordinate().getY() && String.valueOf(f.getValue()).equals(fl.getText())) {
                return fl;
            }
        }
        return null;
    }

    /**
     * tests if you can click the load button
     *
     * @param robot the robot that tests everything
     */
    @Test
    void loadTest(FxRobot robot) {
        robot.clickOn("#saveButton");
    }

    /**
     * tests if you can click the save button
     *
     * @param robot the robot that tests everything
     */
    @Test
    void saveTest(FxRobot robot) {
        robot.clickOn("#loadButton");
    }

}
