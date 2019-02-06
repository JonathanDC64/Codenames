package mvc.controller;

import mvc.model.LogModel;
import mvc.view.LogView;

public class LogController
{
    private LogModel logModel;
    private LogView logView;
    
    public LogController(LogModel logModel, LogView logView)
    {
        this.logModel = logModel;
        this.logView = logView;

        this.logModel.setLogListener(this.logView);
    }

    public void addLog(String text)
    {
        this.logModel.addLog(text);
    }
}