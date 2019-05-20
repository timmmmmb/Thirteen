package main.java.ch.bfh.thirteen.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "field")

/**
 * class for the field, stores and increments a value in the field and lets also set the position
 */
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
    @SuppressWarnings("unused")
    public Field(){

    }

    /**
     * constructor for the field
     * @param value value of the field
     * @param coordinate position of the field
     */
    public Field(int value, Coordinate coordinate) {
        this.value = value;
        this.coordinate = coordinate;
    }

    /**
     * copy constructor for the field
     * @param field other field
     */
    Field(Field field) {
        this.value = field.value;
        this.coordinate = field.getCoordinate();
    }

    /**
     * gets the value in the field
     * @return value of field
     */
    public int getValue() {
        return value;
    }

    /**
     * sets the value in the field
     */
    void setValue(int value) {
        this.value = value;
    }

    /**
     * increments the value of the field
     */
    void incrementValue() {
        this.value++;
    }

    /**
     * to string method for the field
     * @return String with the field information
     */
    public String toString() {
        return String.valueOf(value)+" "+String.valueOf(coordinate.getX())+" "+String.valueOf(coordinate.getY());
    }

    /**
     * gets if the field is to be removed
     * @return to be removed
     */
    boolean getToBeRemoved() {
        return tobeRemoved;
    }

    /**
     * sets the field to be removed
     */
    void toBeRemoved() {
        tobeRemoved = true;
    }

    /**
     * checks if field is visited
     * @return
     */
    boolean isVisited() {
        return visited;
    }

    /**
     * sets field to visited or not visited
     * @param visited true if visited, false if not
     */
    void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * gets the position of the field
     * @return field coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * sets position of the field
     * @param coordinate field coordinate
     */
    void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
