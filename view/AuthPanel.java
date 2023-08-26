package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.AppController;

public class AuthPanel extends JPanel {

    private JTextField tfUser;
    private JPasswordField tfPass;

    public AuthPanel() {
        // Layout to center the content
        this.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20));
        mainPanel.add(titleLabel);

        // Vertical margin
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel subpanelUser = new JPanel();
        subpanelUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        subpanelUser.setBorder(BorderFactory.createTitledBorder("Usuário"));
        tfUser = new JTextField();
        tfUser.setPreferredSize(new Dimension(200, 20));
        subpanelUser.add(tfUser);
        mainPanel.add(subpanelUser);

        JPanel subpanelPass = new JPanel();
        subpanelPass.setBorder(BorderFactory.createTitledBorder("Senha"));
        subpanelPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        tfPass = new JPasswordField();
        tfPass.setEchoChar('*');
        tfPass.setPreferredSize(new Dimension(200, 20));
        subpanelPass.add(tfPass);
        mainPanel.add(subpanelPass);

        // Vertical margin
        mainPanel.add(Box.createVerticalStrut(20));

        JButton btnLogin = new JButton("ENTRAR");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setPreferredSize(new Dimension(150, 30));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });
        mainPanel.add(btnLogin);

        this.add(mainPanel);
    }

    public void handleLoginButtonClick() {
        String username = tfUser.getText();
        String password = new String(tfPass.getPassword());

        // More username and password verifications

        attemptLogin(username, password);
    }

    private void attemptLogin(String username, String password) {
        if (AppController.getInstance().login(username, password)) {
            AppView.getInstance().showServices();
        } else {
            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
