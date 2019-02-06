package mvc.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LogModel implements ViewNotifier
{
    private ArrayList<String> log;
    private PropertyChangeListener logListener;

    public LogModel()
    {
        log = new ArrayList<>();
    }

    public void addLog(String text)
    {
        log.add(text);
        notifyView(logListener, "LogAdded", null, this);
    }

    public void clearLog()
    {
        log.clear();
        notifyView(logListener, "LogClear", null, this);
    }

    public ArrayList<String> getLog()
    {
        return log;
    }

    public void setLogListener(PropertyChangeListener logListener)
    {
        this.logListener = logListener;
    }
}