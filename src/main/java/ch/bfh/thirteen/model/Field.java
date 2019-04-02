package main.java.ch.bfh.thirteen.model;

public class Field {
    private int x, y;
    private int value;
    private Board parent;
    private boolean tobeRemoved = false;
    private boolean visited = false;

    public Field(int x, int y, int value, Board parent) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.parent = parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    void setValue(int value){
        this.value = value;
    }

    private void incrementValue(){
        this.value++;
    }

    /**
     * use this function to click this field
     */
    public void click(){
        parent.removeNeighbores(this);
        parent.addFields();
        parent.updateFieldPositions();
        incrementValue();
        if(parent.isWon()){
            parent.setGameState(GameState.WON);
            System.out.println("won");
        }else if(parent.isLost()){
            parent.setGameState(GameState.LOST);
            System.out.println("lost");
        }
        System.out.println(parent.toSting());

    }

    public String toString(){
        return String.valueOf(value);//+" "+String.valueOf(x)+" "+String.valueOf(y);
    }

    void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean getToBeRemoved(){
        return tobeRemoved;
    }

    void toBeRemoved(){
        tobeRemoved = true;
    }

    boolean isVisited() {
        return visited;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }
}
