package kz.avatis.online;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574419462129743526L;

	public LoginPanel() {
		setSize(400, 200);
		JButton calo = new JButton();
		calo.addActionListener(this);
		add(new JButton("calo"));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
