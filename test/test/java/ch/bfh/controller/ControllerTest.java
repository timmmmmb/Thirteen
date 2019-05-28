package test.java.ch.bfh.controller;

import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.application.ThirteenApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest {

    @Start
    private void start(Stage stage) throws IOException {
        new ThirteenApplication().init();
        new ThirteenApplication().start(stage);
    }

    /**
     * tests if the menu has all of the required buttons and if they work
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

    @Test
    void optionsTest(FxRobot robot) {
        robot.clickOn("#infoButton");
        assertNotNull(robot.lookup("#musicButton").queryParent());
        Assertions.assertThat(((ToggleButton)robot.lookup("#musicButton").queryParent())).hasText("on");
        Assertions.assertThat(robot.lookup("#infoButton").queryButton()).hasText("?");
        Assertions.assertThat(robot.lookup("#backButton").queryButton()).hasText("Back");
        robot.clickOn("#musicButton");
        Assertions.assertThat(((ToggleButton)robot.lookup("#musicButton").queryParent())).hasText("off");
        robot.clickOn("#musicButton");
        Assertions.assertThat(((ToggleButton)robot.lookup("#musicButton").queryParent())).hasText("on");
    }
}
