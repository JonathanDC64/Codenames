package mvc.view;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GameView extends JFrame
{

    private static final long serialVersionUID = -557909777700545933L;

    private GridView gridView;
    private KeycardView keycardView;
    private StartView startView;
    private LogView logView;

    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;

    public GameView()
    {
        super();

        gridView = new GridView();
        keycardView = new KeycardView();
        startView = new StartView();
        logView = new LogView();

        mainPanel = new JPanel();

        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 1));
        topPanel.add(gridView);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 3));
        bottomPanel.add(keycardView);
        bottomPanel.add(logView);
        bottomPanel.add(startView);

        this.add(mainPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);


        this.setTitle("Codenames Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //this.addComponentListener(new ComponentAdapter() {
        //    public void componentResized(ComponentEvent e)
        //    {
        //        System.out.println(e.getComponent().getSize());
        //    }
        //});

        this.setSize(645, 510);
        this.setResizable(false);
        this.setFocusable(true);
    }

    public void display()
    {
        this.setVisible(true);
    }

    public GridView getGridView()
    {
        return gridView;
    }

    public KeycardView getKeycardView()
    {
        return keycardView;
    }

    public StartView getStartView()
    {
        return startView;
    }

    public LogView getLogView()
    {
        return logView;
    }

}