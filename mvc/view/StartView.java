package mvc.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.model.GameModel;

public class StartView extends JPanel implements PropertyChangeListener 
{
    private static final long serialVersionUID = 1L;
    private JButton startButton;
    private JButton stepButton;

    public StartView() 
    {
        startButton = new JButton();
        startButton.setText("Start Game");

        stepButton = new JButton();
        stepButton.setText("Step");
        stepButton.setEnabled(false);

        this.add(startButton);
        this.add(stepButton);
    }

    public JButton getStartButton() 
    {
        return startButton;
    }

    public JButton getStepButton() 
    {
        return stepButton;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) 
    {
        GameModel gameModel = (GameModel)evt.getSource();

        if(gameModel.isStarted())
        {
            startButton.setEnabled(false);
            stepButton.setEnabled(true);
        }
        else
        {
            startButton.setEnabled(true);
            stepButton.setEnabled(false);
        }
    }
}