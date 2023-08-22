package view;

import java.awt.*;
import javax.swing.*;

import controller.AppController;

public class AppFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    public AppFrame(AppController controller) {
        // Default frame settings
        this.setTitle("System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        cardPanel.add(new AuthPanel(this, controller), "auth");
        cardPanel.add(new ServicesPanel(this, controller), "services");
        this.add(cardPanel);
        showPanel("auth");
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

}
