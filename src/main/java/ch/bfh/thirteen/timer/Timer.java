package main.java.ch.bfh.thirteen.timer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import main.java.ch.bfh.thirteen.observer.CustomPropertyChangeSupport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeSupport;

@XmlRootElement(name = "timer")
public class Timer {
    @XmlElement(name = "t")
    private int t = 0;
    private Timeline tl;
    private CustomPropertyChangeSupport pcs = new CustomPropertyChangeSupport(this);

    /**
     * the timer counts the variable t up by 1 each second
     */
    public Timer() {
        tl = new Timeline(new KeyFrame(Duration.millis(1000), e -> pcs.firePropertyChange("t", t, ++t)));
        tl.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * restarts the timer
     */
    public void restart() {
        t = 0;
    }

    /**
     * gets the pcs
     *
     * @return the pcs from the timer
     */
    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    /**
     * the toString function returns the current t as string
     *
     * @return String t
     */
    public String toString() {
        return String.valueOf(t);
    }

    /**
     * gets the time as int
     *
     * @return the time as int
     */
    public int getTime() {
        return t;
    }

    /**
     * pauses the timeline
     */
    public void pause() {
        tl.pause();
    }

    /**
     * starts the timeline
     */
    public void play() {
        tl.play();
    }
}
