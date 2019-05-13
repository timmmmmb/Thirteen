package main.java.ch.bfh.thirteen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "Coordinate")
public class Coordinate {
    @XmlElement(name = "x")
    private final int x;
    @XmlElement(name = "y")
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        this(0,0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
