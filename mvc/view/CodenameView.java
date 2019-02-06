package mvc.view;

import javax.swing.JButton;

public class CodenameView extends JButton
{
    private static final long serialVersionUID = 1L;
    public static final int BTN_WIDTH = 50;
    public static final int BTN_HEIGHT = 30;

    public CodenameView()
    {
        this.setSize(BTN_WIDTH, BTN_HEIGHT);
        this.setText("<unnamed>");
    }
}