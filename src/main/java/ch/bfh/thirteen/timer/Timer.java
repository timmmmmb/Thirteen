package main.java.ch.bfh.thirteen.timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeSupport;

@XmlRootElement(name = "game")
public class Timer {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    @XmlElement(name = "t")
    private int t = 0;
    Timeline tl;

    public Timer() {
        tl = new Timeline(new KeyFrame(Duration.millis(1000), e -> pcs.firePropertyChange("t", t, ++t)));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    public void restart() {
        t = 0;
    }

    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    public String toString() {
        return String.valueOf(t);
    }

    public int getTime() {
        return t;
    }

    public void pause(){
        tl.pause();
    }

    public void play(){
        tl.play();
    }
}
