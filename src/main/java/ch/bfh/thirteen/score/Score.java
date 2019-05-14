package main.java.ch.bfh.thirteen.score;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.Date;

@XmlRootElement(name = "Score")
public class Score {
    @XmlElement(name = "highestnumber")
    private final int highestnumber;
    @XmlElement(name = "moves")
    private final int moves;
    @XmlElement(name = "time")
    private final int time;
    @XmlElement(name = "win")
    private final Boolean win;
    @XmlElement(name = "date")
    private final Date date = Calendar.getInstance().getTime();

    public Score(int highestnumber, int moves, int time, Boolean win) {
        this.highestnumber = highestnumber;
        this.moves = moves;
        this.time = time;
        this.win = win;
    }

    public Score() {
        this(0, 0, 0, false);
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
