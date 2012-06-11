package kz.avatis.online;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kz.avatis.online.tools.Translator;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = -2574419462129743526L;
	public JButton loginButton = new JButton(Translator.t("Login"));
	private JTextField loginField, passField;
	private JLabel errorMessage = new JLabel(" ");
	
	public LoginPanel() {
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
		
		setLayout(new GridBagLayout());
		errorMessage.setFont(new Font("sdf", 1,22));
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0; gbc.gridy = 0;gbc.gridwidth = 2;
		add(errorMessage, gbc);
		gbc.gridwidth = 1;
		loginField = new JTextField(20); passField = new JTextField(20);
		gbc.gridx = 0; gbc.gridy = 1;add(new JLabel(Translator.t("Username")+": "),gbc);
		gbc.gridx = 1; gbc.gridy = 1;add(loginField, gbc);
		gbc.gridx = 0; gbc.gridy = 2;add(new JLabel(Translator.t("Password")+": "),gbc);
		gbc.gridx = 1; gbc.gridy = 2;add(passField,gbc);
		gbc.gridx = 0; gbc.gridy = 3;gbc.gridwidth = 2;add(loginButton,gbc);		
	}
	
	public String getLogin() {
		return loginField.getText();
	}
	
	public String getPassword() {
		return passField.getText();
	}
	
	public void setErrorMessage(String msg) {
		errorMessage.setText(msg);
	}
}
