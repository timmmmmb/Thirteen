package main.java.ch.bfh.thirteen.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "field")
public class Field{
    @XmlElement(name = "value")
    private int value;
    private boolean tobeRemoved = false;
    private boolean visited = false;
    @XmlElement(name = "coordinate")
    private Coordinate coordinate;

    /**
     * this is only used for the xml
     */
    public Field(){

    }

    public Field(int value, Coordinate coordinate) {
        this.value = value;
        this.coordinate = coordinate;
    }

    Field(Field field) {
        this.value = field.value;
        this.coordinate = field.getCoordinate();
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    void incrementValue() {
        this.value++;
    }

    public String toString() {
        return String.valueOf(value)+" "+String.valueOf(coordinate.getX())+" "+String.valueOf(coordinate.getY());
    }

    boolean getToBeRemoved() {
        return tobeRemoved;
    }

    void toBeRemoved() {
        tobeRemoved = true;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
