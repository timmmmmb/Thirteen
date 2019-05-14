package main.java.ch.bfh.thirteen.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CustomPropertyChangeSupport extends PropertyChangeSupport {
    /**
     * Constructs a {@code PropertyChangeSupport} object.
     *
     * @param sourceBean The bean to be given as the source for any events.
     */
    public CustomPropertyChangeSupport(Object sourceBean) {
        super(sourceBean);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        clearListeners();
        super.addPropertyChangeListener(listener);
    }

    private void clearListeners(){
        for(PropertyChangeListener pcs: getPropertyChangeListeners()){
            this.removePropertyChangeListener(pcs);
        }
    }
}
