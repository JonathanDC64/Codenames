package mvc.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mvc.model.CardType;
import mvc.model.KeycardModel;
import mvc.model.Team;

public class KeycardView extends JPanel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    private JPanel cardPanel;
    private ArrayList<JPanel> cardCells;
    //private static final Color[] colors = { Color.BLUE, Color.RED, Color.YELLOW, Color.BLACK };

    //private static final int CELL_WIDTH = 10;
    //private static final int CELL_HEIGHT = 10;
    private static final int BORDER_THICKNESS = 5;

    public static final Map<CardType, Color> cardTypeColors = new HashMap<CardType, Color>()
    {
        private static final long serialVersionUID = 1L;
        {
            put(CardType.BLUE_AGENT, Color.BLUE);
            put(CardType.RED_AGENT, Color.RED);
            put(CardType.BYSTANDER, Color.YELLOW);
            put(CardType.ASSASSIN, Color.GRAY);
        }
    };

    public KeycardView() {
        cardPanel = new JPanel();
        cardCells = new ArrayList<>();
        
        cardPanel.setLayout(new GridLayout(5, 5));

        for (int i = 0; i < 25; ++i) {
            JPanel cell = new JPanel();
            //cell.setSize(CELL_WIDTH, CELL_HEIGHT);
            //cell.setBackground(colors[(int) (Math.random() * colors.length)]);
            Border border = BorderFactory.createStrokeBorder(new BasicStroke());
            cell.setBorder(border);
            cardCells.add(cell);
            cardPanel.add(cell);
        }
        this.add(cardPanel);

        Border border = BorderFactory.createLineBorder(Color.BLACK, BORDER_THICKNESS);
        cardPanel.setBorder(border);

        this.setLayout(new GridLayout(0, 1));
        border = BorderFactory.createTitledBorder("Keycard");
        this.setBorder(border);

        //this.setPreferredSize(new Dimension(50, 100));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        KeycardModel keycardModel = (KeycardModel)evt.getSource();

        Color color;
        if(keycardModel.getStartTeam() == Team.BLUE)
        {
            color = Color.BLUE;
        }
        else
        {
            color = Color.RED;
        }

        Border border = BorderFactory.createLineBorder(color, BORDER_THICKNESS);
        cardPanel.setBorder(border);

        for(int i = 0 ; i < 25 ; ++i)
        {
            CardType cardType = keycardModel.getCardType(i);
            color = cardTypeColors.get(cardType);
            cardCells.get(i).setBackground(color);
        }
    }
}