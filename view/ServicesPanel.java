package view;

import java.awt.event.*;
import javax.swing.*;

import controller.AppController;

public class ServicesPanel extends JPanel {

    public ServicesPanel() {

        JLabel lblNewLabel = new JLabel(
                String.format("Welcome - %s", AppController.getInstance().getUserAuthenticated()));
        this.add(lblNewLabel);

        switch (AppController.getInstance().getUserAuthenticated().getRole()) {
            case "Admin":
                this.add(new ServicesAdminPanel());
                break;
            case "Student":
                this.add(new ServicesStudentPanel());
                break;
            default:
                System.out.println("Vagabundo");
                break;
        }

        JButton btnLogin = new JButton("Sair");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });
        this.add(btnLogin);
    }

    private void handleLogoutButtonClick() {
        logout();
    }

    private void logout() {
        AppController.getInstance().logout();
        AppView.getInstance().showAuth();
    }

}
