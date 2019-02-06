package mvc.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface ViewNotifier
{
    default void notifyView(PropertyChangeListener listener, String name, Object oldValue, Object newValue)
    {
        if(listener != null)
        {
            PropertyChangeEvent evt = new PropertyChangeEvent(this, name, oldValue, newValue);
            listener.propertyChange(evt);
        }
    }
}