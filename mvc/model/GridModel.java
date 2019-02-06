package mvc.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GridModel implements ViewNotifier
{
    private ArrayList<CodenameModel> grid;
    //private final int MAX_CODENAMES = 25;
    
    private PropertyChangeListener gridListener;

    public GridModel()
    {
        grid = new ArrayList<>();
        for(int i = 0 ; i < 25 ; ++i)
        {
            grid.add(new CodenameModel("<unnamed>", i));
        }
    }

    public void addCodename(CodenameModel codename)
    {
        grid.add(codename);
        notifyView(gridListener, "CodenameAdded", null, this);
    }

    public void setCodename(int index, CodenameModel codenameModel)
    {
        grid.set(index, codenameModel);
        notifyView(gridListener, "CodenameSet", null, this);
    }

    public CodenameModel getCodename(int index)
    {
        return grid.get(index);
    }

    public void clear()
    {
        for(int i = 0 ; i < grid.size() ; ++i)
        {
            CodenameModel codename = grid.get(i);
            codename.setCardType(null);
            codename.setRevealed(false);
        }
        notifyView(gridListener, "CodenamesCleared", null, this);
    }

    public void setGridListener(PropertyChangeListener gridListener)
    {
        this.gridListener = gridListener;
    }

    public void update()
    {
        notifyView(gridListener, "GridUpdate", null, this);
    }
}