package view;

import javax.swing.*;

import controller.AppController;

public class AppFrame extends JFrame {

    private AppController controller;

    public AppFrame(AppController controller) {
        this.controller = controller;

        // Default frame settings
        this.setTitle("System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        showAuth();
    }

    public void showAuth() {
        this.setContentPane(new AuthPanel(this));
        this.revalidate();
    }

    public void showServices() {
        this.setContentPane(new ServicesPanel(this));
        this.revalidate();
    }

    public AppController getController() {
        return controller;
    }

}
