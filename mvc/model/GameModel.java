package mvc.model;

import java.beans.PropertyChangeListener;

public class GameModel implements ViewNotifier
{
    private SpymasterModel blueSpymaster;
    private SpymasterModel redSpymaster;

    private OperativeModel blueOperative;
    private OperativeModel redOperative;

    private GridModel grid;
    private KeycardModel keycard;

    private LogModel log;

    private boolean started;

    private PropertyChangeListener gameListener;

    public GameModel()
    {
        grid = new GridModel();
        keycard = new KeycardModel();

        blueSpymaster = new SpymasterModel(Team.BLUE, keycard, grid);
        redSpymaster = new SpymasterModel(Team.RED, keycard, grid);

        blueOperative = new OperativeModel(Team.BLUE, grid);
        redOperative = new OperativeModel(Team.RED, grid);

        log = new LogModel();

        started = false;
    }

    public SpymasterModel getBlueSpymaster()
    {
        return this.blueSpymaster;
    }

    public SpymasterModel getRedSpymaster()
    {
        return this.redSpymaster;
    }

    public OperativeModel getBlueOperative()
    {
        return this.blueOperative;
    }

    public OperativeModel getRedOperative()
    {
        return this.redOperative;
    }

    public GridModel getGrid()
    {
        return this.grid;
    }

    public KeycardModel getKeycard()
    {
        return this.keycard;
    }

    public LogModel getLog()
    {
        return this.log;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void start()
    {
        started = true;
        notifyView(gameListener, "GameStarted", null, this);
    }

    public void stop()
    {
        started = false;
        notifyView(gameListener, "GameStarted", null, this);
    }

    public void setGameListener(PropertyChangeListener gameListener)
    {
        this.gameListener = gameListener;
    }
}