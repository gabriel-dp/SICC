package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.UserAuthenticator;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField pwfUser;

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fazer login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(209, 11, 105, 21);
		contentPane.add(lblNewLabel);
		
		tfUser = new JTextField();
		tfUser.setBackground(SystemColor.inactiveCaption);
		tfUser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tfUser.setBounds(173, 67, 181, 28);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		pwfUser = new JPasswordField();
		pwfUser.setEchoChar('*');
		pwfUser.setBackground(SystemColor.inactiveCaption);
		pwfUser.setToolTipText("");
		pwfUser.setFont(new Font("Times New Roman", Font.BOLD, 16));
		pwfUser.setBounds(173, 123, 181, 28);
		contentPane.add(pwfUser);
		
		JButton btnLogin = new JButton("Entrar na conta");
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String username = tfUser.getText();
		        char[] passwordChars = pwfUser.getPassword();
		        String password = new String(passwordChars);

		        UserAuthenticator auth = new UserAuthenticator();
		        User authenticatedUser = auth.authenticate(username, password);
                System.out.println(authenticatedUser);

		        if (authenticatedUser != null) {
		            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
		        } else {
		            JOptionPane.showMessageDialog(null, "Falha no login. Verifique suas credenciais.");
		        }
		    }
		});
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLogin.setBounds(178, 202, 158, 28);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(86, 74, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(86, 130, 57, 14);
		contentPane.add(lblNewLabel_1_1);
	}
}