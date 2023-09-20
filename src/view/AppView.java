package src.view;

import javax.swing.*;

public class AppView extends JFrame {

    private static AppView instance = null;

    private AppView() {
        // Default frame settings
        this.setTitle("SICC - Sistema Integrado de Componentes Curriculares");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        // Initial panel
        showAuth();
    }

    // Singleton lazy initialization
    public static AppView getInstance() {
        if (instance == null)
            instance = new AppView();
        return instance;
    }

    public void showAuth() {
        this.setContentPane(new AuthPanel());
        this.revalidate();
    }

    public void showServices() {
        this.setContentPane(new ServicesPanelGeneral());
        this.revalidate();
    }

}
