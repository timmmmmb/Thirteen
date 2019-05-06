package main.java.ch.bfh.thirteen.score;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    @Override
    public int compare(Score o1, Score o2) {
        if(o1.getHighestnumber()!=o2.getHighestnumber()){
            return o1.getHighestnumber()-o2.getHighestnumber();
        }

        if(o1.getMoves()!=o2.getMoves()){
            return o1.getMoves()-o2.getMoves();
        }

        if(o1.getTime()!=o2.getTime()){
            return o1.getTime()-o2.getTime();
        }

        return o1.getDate().compareTo(o2.getDate());
    }
}
