package main.java.ch.bfh.thirteen.stack;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Stack;

@XmlRootElement(name = "SizedStack")
public class SizedStack<T> extends Stack<T> {
    private int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    public SizedStack() {
        this(10);
    }

    @Override
    public T push(T object) {
        //If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}
