package test.java.ch.bfh.score;

import main.java.ch.bfh.thirteen.score.Score;
import main.java.ch.bfh.thirteen.score.ScoreComparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScoreTest {
    @Test
    void constructorTest(){
        Score score = new Score(1,2,3,false);
        assertEquals(1,score.getHighestnumber());
        assertEquals(2,score.getMoves());
        assertEquals(3,score.getTime());
        assertFalse(score.getWin());
    }

    /**
     * tests if the comparator works
     */
    @Test
    void comparatorTest(){
        Score score1 = new Score(1,2,3,false);
        Score score2 = new Score(2,2,3,false);
        assertEquals(1,new ScoreComparator().compare(score1,score2));
        assertEquals(-1,new ScoreComparator().compare(score2,score1));
        assertEquals(0,new ScoreComparator().compare(score1,score1));
    }
}
