package view;

import javax.swing.JPanel;

public abstract class AppPanel extends JPanel {

    protected AppFrame frame;

    protected AppPanel(AppFrame frame) {
        this.frame = frame;
    }

}
