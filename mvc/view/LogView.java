package mvc.view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import mvc.model.LogModel;

public class LogView extends JScrollPane implements PropertyChangeListener 
{
    private static final long serialVersionUID = 1L;
    private JTextArea viewport;

    public LogView() 
    {
        viewport = new JTextArea();
        viewport.setEditable(false);
        viewport.setOpaque(false);
        viewport.setLineWrap(true);
        viewport.setWrapStyleWord(true);

        this.setViewportView(viewport);
        this.setPreferredSize(new Dimension(200, 100));
        this.setHorizontalScrollBar(null);
        Border border = BorderFactory.createTitledBorder("Game Log");
        this.setBorder(border);
    }

    public void addLog(String log) 
    {
        viewport.append(log + "\n\n");
        viewport.setCaretPosition(viewport.getDocument().getLength());
    }

    public void clearLog() 
    {
        viewport.setText("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) 
    {
        clearLog();

        LogModel logModel = (LogModel)evt.getSource();
        
        ArrayList<String> logList = logModel.getLog();

        for(String text : logList)
        {
            addLog(text);
        }
    }
}