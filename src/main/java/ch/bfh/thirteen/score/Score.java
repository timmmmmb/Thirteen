package main.java.ch.bfh.thirteen.score;

import java.util.Calendar;
import java.util.Date;

public class Score {
    private final int highestnumber;
    private final int moves;
    private final int time;
    private final Boolean win;
    private final Date date = Calendar.getInstance().getTime();

    public Score(int highestnumber, int moves, int time, Boolean win) {
        this.highestnumber = highestnumber;
        this.moves = moves;
        this.time = time;
        this.win = win;
    }

    public int getHighestnumber() {
        return highestnumber;
    }

    public int getMoves() {
        return moves;
    }

    public int getTime() {
        return time;
    }

    public Boolean getWin() {
        return win;
    }

    public Date getDate() {
        return date;
    }
}
