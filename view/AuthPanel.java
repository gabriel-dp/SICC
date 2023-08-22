package view;

import java.awt.event.*;
import javax.swing.*;

public class AuthPanel extends AppPanel {

    private JTextField tfUser;
    private JPasswordField pwfUser;

    public AuthPanel(AppFrame frame) {
        super(frame);

        JLabel lblNewLabel = new JLabel("Fazer login");
        this.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Usuário");
        this.add(lblNewLabel_1);

        tfUser = new JTextField();
        tfUser.setColumns(10);
        this.add(tfUser);

        JLabel lblNewLabel_1_1 = new JLabel("Senha");
        this.add(lblNewLabel_1_1);

        pwfUser = new JPasswordField();
        pwfUser.setEchoChar('*');
        pwfUser.setToolTipText("");
        pwfUser.setColumns(10);
        this.add(pwfUser);

        JButton btnLogin = new JButton("Entrar na conta");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLoginButtonClick();
            }
        });
        this.add(btnLogin);
    }

    public void handleLoginButtonClick() {
        String username = tfUser.getText();
        String password = new String(pwfUser.getPassword());

        // More username and password verifications

        attemptLogin(username, password);
    }

    private void attemptLogin(String username, String password) {
        if (frame.getController().login(username, password)) {
            frame.showServices();
        } else {
            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.");
        }
    }

}
