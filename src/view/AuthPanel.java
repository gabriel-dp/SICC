package src.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.controller.AppController;
import src.controller.auth.UserCredentialsException;
import src.controller.auth.UserNotFoundException;

public class AuthPanel extends JPanel {

    private JTextField tfUser;
    private JPasswordField pfPass;

    public AuthPanel() {
        this.setLayout(new GridBagLayout()); // Layout to center the content
        this.add(createLoginPanel());
    }

    private JPanel createLoginPanel() {
        // Panel to group and centralize objects in Y axis
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Login title
        JLabel labelTitle = new JLabel("LOGIN");
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD, 20));
        mainPanel.add(labelTitle);

        // Vertical margin
        mainPanel.add(Box.createVerticalStrut(20));

        // Username field
        JPanel subpanelUser = new JPanel();
        subpanelUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelUser.setBorder(BorderFactory.createTitledBorder("Usuário"));
        tfUser = new JTextField();
        tfUser.setPreferredSize(new Dimension(200, 20));
        subpanelUser.add(tfUser);
        mainPanel.add(subpanelUser);

        // Password field
        JPanel subpanelPass = new JPanel();
        subpanelPass.setBorder(BorderFactory.createTitledBorder("Senha"));
        subpanelPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        pfPass = new JPasswordField();
        pfPass.setEchoChar('*');
        pfPass.setPreferredSize(new Dimension(200, 20));
        subpanelPass.add(pfPass);
        mainPanel.add(subpanelPass);

        // Vertical margin
        mainPanel.add(Box.createVerticalStrut(20));

        // Login button
        JButton buttonLogin = new JButton("ENTRAR");
        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonLogin.setPreferredSize(new Dimension(150, 30));
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });
        mainPanel.add(buttonLogin);

        return mainPanel;
    }

    private void handleLoginButtonClick() {
        String username = tfUser.getText();
        String password = new String(pfPass.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            loginFailure("Preencha todos os campos");
        } else {
            attemptLogin(username, password);
        }
    }

    private void attemptLogin(String username, String password) {
        try {
            AppController.login(username, password);
            AppView.getInstance().showServices();
        } catch (UserNotFoundException ex) {
            loginFailure("Usuário não encontrado");
        } catch (UserCredentialsException ex) {
            loginFailure("Credenciais inválidas");
        }
    }

    private void loginFailure(String message) {
        JOptionPane.showMessageDialog(this, message, "Falha no login", JOptionPane.WARNING_MESSAGE);
    }

}
