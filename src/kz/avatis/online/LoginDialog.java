package kz.avatis.online;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Translator;

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8212817567290982934L;
	
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private boolean succeeded;
	private DataProxy _dp;

    public boolean authenticate(String login, String pass) {
    	if (_dp != null) {
    		return _dp.login(login, pass);
    	} else return false;
    }
	
    public void setDataProxy(DataProxy dp) {
    	_dp = dp;
    }
    
	public LoginDialog(Frame parent) {

	     super(parent, Translator.t("Login"), true);

	     JPanel panel = new JPanel(new GridBagLayout());

	     GridBagConstraints cs = new GridBagConstraints();

	     cs.fill = GridBagConstraints.HORIZONTAL;
	     lbUsername = new JLabel(Translator.t("Username: "));
	     cs.gridx = 0;
	     cs.gridy = 0;
	     cs.gridwidth = 1;
	     panel.add(lbUsername, cs);

	     tfUsername = new JTextField(20);
	     cs.gridx = 1;
	     cs.gridy = 0;
	     cs.gridwidth = 2;
	     panel.add(tfUsername, cs);

	     lbPassword = new JLabel(Translator.t("Password: "));
	     cs.gridx = 0;
	     cs.gridy = 1;
	     cs.gridwidth = 1;
	     panel.add(lbPassword, cs);

	    pfPassword = new JPasswordField(20);
	    cs.gridx = 1;
	    cs.gridy = 1;
	    cs.gridwidth = 2;

	    panel.add(pfPassword, cs);
	    panel.setBorder(new LineBorder(Color.GRAY));

	    btnLogin = new JButton(Translator.t("Login"));
	    btnLogin.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        	if (authenticate(getUsername(), getPassword())) {
        		succeeded = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginDialog.this,
                			Translator.t("Invalid username or password"),
                			"Login",
                			JOptionPane.ERROR_MESSAGE);

	                 pfPassword.setText("");
	                 succeeded = false;
	             }
	         }
	     });

	     btnCancel = new JButton(Translator.t("Cancel"));
	     btnCancel.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e) {
	    		 dispose();
	         	}
	     });

	     JPanel bp = new JPanel();
	     bp.add(btnLogin);
	     bp.add(btnCancel);

	     getContentPane().add(panel, BorderLayout.CENTER);
	     getContentPane().add(bp, BorderLayout.PAGE_END);

	     pack();
	     setResizable(false);
	     setLocationRelativeTo(parent);

	 }

	 public String getUsername() {
	     return tfUsername.getText().trim();
	 }

	 public String getPassword() {
	     return new String(pfPassword.getPassword());
	 }

	 public boolean isSucceeded() {
	     return succeeded;
     }

}
