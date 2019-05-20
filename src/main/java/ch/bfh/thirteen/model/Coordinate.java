package main.java.ch.bfh.thirteen.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "Coordinate")
public class Coordinate {
    @XmlElement(name = "x")
    private final int x;
    @XmlElement(name = "y")
    private final int y;

    /**
     * creates a new coordinate
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * used for jaxb
     */
    @SuppressWarnings("unused")
    public Coordinate() {
        this(0,0);
    }

    /**
     * get the x coordinate
     * @return int x
     */
    public int getX() {
        return x;
    }
    /**
     * get the y coordinate
     * @return int y
     */
    public int getY() {
        return y;
    }
}
