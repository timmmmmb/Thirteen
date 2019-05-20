package main.java.ch.bfh.thirteen.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * this class allows to only add one pcl
 * the reason for this is to prevent the multiple addition of the same pcl to this pcs
 */
public class CustomPropertyChangeSupport extends PropertyChangeSupport {
    /**
     * Constructs a {@code PropertyChangeSupport} object.
     *
     * @param sourceBean The bean to be given as the source for any events.
     */
    public CustomPropertyChangeSupport(Object sourceBean) {
        super(sourceBean);
    }

    /**
     * overrides the property change listener to only allow one listener at the same time
     * @param listener the listener to get added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        clearListeners();
        super.addPropertyChangeListener(listener);
    }

    /**
     * clears all of the listeners of the pcs
     */
    private void clearListeners(){
        for(PropertyChangeListener pcs: getPropertyChangeListeners()){
            this.removePropertyChangeListener(pcs);
        }
    }
}
