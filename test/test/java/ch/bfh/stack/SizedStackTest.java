package test.java.ch.bfh.stack;

import main.java.ch.bfh.thirteen.stack.SizedStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SizedStackTest {
    @Test
    void constructorTest(){
        SizedStack stack = new SizedStack(5);
        assertNotNull(stack);
        assertTrue(stack.size()<=5);
    }

    @Test
    void sizeTest(){
        SizedStack<Integer> stack = new SizedStack<>(10);
        for(int i = 0; i<20; i++){
            stack.push(i);
        }
        assertEquals(10, stack.size());
    }
}
