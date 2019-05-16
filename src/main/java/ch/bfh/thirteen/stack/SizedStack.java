package main.java.ch.bfh.thirteen.stack;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Stack;

@XmlRootElement(name = "SizedStack")
public class SizedStack<T> extends Stack<T> {
    private int maxSize;

    /**
     * create a new stack with a maximum size
     *
     * @param size the size of the stack
     */
    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    /**
     * this constuctor is for the jaxb
     */
    @SuppressWarnings("unused")
    public SizedStack() {
        this(10);
    }

    /**
     * pushes an object ontop of the stack if the stack has reached its maxsize it removes its oldest element
     *
     * @param object the object to get added to the stack
     * @return the argument
     */
    @Override
    public T push(T object) {
        //If the stack is too big, remove elements until it's the right size.
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}
