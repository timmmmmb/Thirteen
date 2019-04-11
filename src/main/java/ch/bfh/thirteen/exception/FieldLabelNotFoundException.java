package main.java.ch.bfh.thirteen.exception;

/**
 * use this exception if a field was not found in the ui
 */
public class FieldLabelNotFoundException extends Exception {
    public FieldLabelNotFoundException(String message){
        super(message);
    }
}
