package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.AppController;
import model.User;

public class ServicesPanel extends JPanel {

    private User user = AppController.getInstance().getUserAuthenticated();
    private String userFullName = user.getFirstName() + ' ' + user.getLastName();
    private String userRole;

    public ServicesPanel() {
        this.setLayout(new BorderLayout());

        switch (AppController.getInstance().getUserAuthenticated().getRole()) {
            case ADMIN:
                this.add(new ServicesPanelAdmin(), BorderLayout.CENTER);
                userRole = "Administrador";
                break;
            case STUDENT:
                this.add(new ServicesPanelStudent(), BorderLayout.CENTER);
                userRole = "Estudante";
                break;
            default:
                userRole = "Cargo desconhecido";
                break;
        }

        JPanel generalPanel = new JPanel(new BorderLayout());
        generalPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        generalPanel.setBackground(new Color(10, 200, 150));

        JLabel labelUser = new JLabel(String.format("%s (%s)", userFullName, userRole));
        generalPanel.add(labelUser, BorderLayout.CENTER);

        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogoutButtonClick();
            }
        });
        generalPanel.add(btnLogout, BorderLayout.LINE_END);

        this.add(generalPanel, BorderLayout.PAGE_END);
    }

    private void handleLogoutButtonClick() {
        logout();
    }

    private void logout() {
        AppController.getInstance().logout();
        AppView.getInstance().showAuth();
    }

}
