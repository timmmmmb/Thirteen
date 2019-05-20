package main.java.ch.bfh.thirteen.score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    /**
     * compares to scores and returns the difference
     *
     * @param o1 the first score
     * @param o2 the second score
     * @return Returns a positive integer, zero,
     * or a negative integer as the first argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(Score o1, Score o2) {
        if (o1.getHighestnumber() != o2.getHighestnumber()) {
            return o2.getHighestnumber() - o1.getHighestnumber();
        }

        if (o1.getMoves() != o2.getMoves()) {
            return o2.getMoves() - o1.getMoves();
        }

        if (o1.getTime() != o2.getTime()) {
            return o2.getTime() - o1.getTime();
        }

        return o2.getDate().compareTo(o1.getDate());
    }
}
