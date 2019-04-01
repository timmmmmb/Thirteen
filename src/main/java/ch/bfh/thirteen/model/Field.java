package main.java.ch.bfh.thirteen.model;

public class Field {
    private int x, y;
    private int value;
    private Board parent;

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

    public void setValue(int value){
        this.value = value;
    }

    private void incrementValue(){
        this.value++;
    }

    /**
     * use this function to click this field
     */
    public void click(){
        incrementValue();
        parent.removeNeighbores(this);
        if(parent.isWon()){
            parent.setGameState(GameState.WON);
        }else if(parent.isLost()){
            parent.setGameState(GameState.LOST);
        }

    }

    public String toString(){
        return String.valueOf(x)+" "+String.valueOf(y)+" "+String.valueOf(value);
    }
}
