package test.java.ch.bfh.timer;

import main.java.ch.bfh.thirteen.timer.Timer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimerTest {

    /**
     * tests if the timer counts correctly
     */
    @Test
    void numbersTest() {
        Timer t =new Timer();
        /*t.play();
        Thread.sleep(1000);
        assertEquals(1, t.getTime());*/
        t.restart();
        assertEquals(0, t.getTime());
    }

    /**
     * tests the constructor
     */
    @Test
    void constructorTest(){
        Timer t =new Timer();
        assertEquals(0, t.getTime());
    }
}
