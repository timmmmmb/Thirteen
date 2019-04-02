package main.java.ch.bfh.thirteen.controller;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;

public class GameScreenController {
    public static void addFadingIn(final Node node, final Group parent) {
        final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setInterpolator(Interpolator.EASE_IN);
        parent.getChildren().add(node);
        transition.play();
    }

    public static void removeFadingOut(final Node node, final Group parent) {
        if (parent.getChildren().contains(node)) {
            final FadeTransition transition = new FadeTransition(Duration.millis(250), node);
            transition.setFromValue(node.getOpacity());
            transition.setToValue(0);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setOnFinished(finishHim -> {
                parent.getChildren().remove(node);
            });
            transition.play();
        }
    }
}
