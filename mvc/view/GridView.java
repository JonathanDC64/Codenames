package mvc.view;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mvc.model.CodenameModel;
import mvc.model.GridModel;

public class GridView extends JPanel implements PropertyChangeListener 
{
    private static final long serialVersionUID = 1L;
    ArrayList<CodenameView> codenames;

    public GridView() 
    {
        codenames = new ArrayList<>();
        this.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 25; ++i) {
            CodenameView codenameView = new CodenameView();
            codenames.add(codenameView);
            this.add(codenameView);
        }

        Border border = BorderFactory.createTitledBorder("Cards");
        this.setBorder(border);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) 
    {
        GridModel gridModel = (GridModel)evt.getSource();

        for(int i = 0 ; i < 25 ; ++i)
        {
            CodenameView codenameView = codenames.get(i);
            CodenameModel codename = gridModel.getCodename(i);
            codenameView.setText(codename.getName());

            if(codename.isRevealed())
            {
                codenameView.setBackground(KeycardView.cardTypeColors.get(codename.getCardType()));
            }
            else
            {
                codenameView.setBackground(null);
            }
        }
    }
}