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

    /**
     * creates a new score
     *
     * @param highestnumber the max number reached
     * @param moves         the amount of moves needed to get there
     * @param time          the amount of time to get there
     * @param win           if the game was won(highestnumber = 13)
     */
    public Score(int highestnumber, int moves, int time, Boolean win) {
        this.highestnumber = highestnumber;
        this.moves = moves;
        this.time = time;
        this.win = win;
    }

    /**
     * used for jaxb default constructor
     */
    @SuppressWarnings("unused")
    public Score() {
        this(0, 0, 0, false);
    }

    /**
     * gets the highestnumber of the score
     *
     * @return the highestnumber
     */
    public int getHighestnumber() {
        return highestnumber;
    }

    /**
     * gets the moves of the score
     *
     * @return the moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * gets the time of the score
     *
     * @return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * gets if won of the score
     *
     * @return true if won
     */
    public Boolean getWin() {
        return win;
    }

    /**
     * gets the date of the score
     *
     * @return the date of the score
     */
    public Date getDate() {
        return date;
    }
}
