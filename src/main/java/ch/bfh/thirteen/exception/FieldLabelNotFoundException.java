package main.java.ch.bfh.thirteen.exception;

/**
 * use this exception if a field was not found in the ui
 */
public class FieldLabelNotFoundException extends Exception {
    /**
     * creates the exception
     * @param message String the message to get displayed in the exception
     */
    public FieldLabelNotFoundException(String message){
        super(message);
    }
}
