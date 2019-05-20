package main.java.ch.bfh.thirteen.exception;

/**
 * use this exception if the ui doesnt match the model
 */
public class UINotMatchingModelException extends Exception{
    /**
     * creates the exception
     * @param message String the message to get displayed in the exception
     */
    public UINotMatchingModelException(String message){
        super(message);
    }
}
