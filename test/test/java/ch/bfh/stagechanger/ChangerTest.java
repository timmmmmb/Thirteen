package test.java.ch.bfh.stagechanger;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.java.ch.bfh.thirteen.stagechanger.StageChanger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class ChangerTest {
    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage stage) {
        Button button = new Button();
        button.setOnAction(ae -> StageChanger.changeStage(ae, "test.fxml"));
        stage.setScene(new Scene(new StackPane(button), 100, 100));
        stage.show();
    }

    @Test
    void stageChangeTest(FxRobot robot){
        robot.clickOn(".button");
        Assertions.assertThat(robot.lookup(".button").queryButton()).hasText("testButton");
    }
}
