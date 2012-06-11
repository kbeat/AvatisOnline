package kz.avatis.online;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Translator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 7727711190347516051L;
		
	 
	
	public static void main(String [] args) {
		final MainWindow mw = new MainWindow();
		final LoginPanel lp;
		MainPanel mp;
		
		final CardLayout mcl = new MyCardLayout();
		mw.setLayout(mcl);
		
		DataProxy dp = new DataProxy("http://192.168.1.3");
		
		lp = new LoginPanel();
		lp.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Map<String, String> pars = new HashMap<String, String>();
				pars.put("login", lp.getLogin());
				pars.put("password", lp.getPassword());
				
				DataProxy dp = new DataProxy("http://192.168.1.103/avest/");
								
				if (dp.login(pars)) {
					System.out.println(dp.getToken());
					mcl.show(mw.getContentPane(), "main");
					mw.pack();
				} else {
					lp.setErrorMessage(Translator.t("Incorrect login or password!"));
				}
				
			}
		});
		mw.add(lp);
		
		mp = new MainPanel();
		mw.add(mp, "main");
		mw.pack();
		mw.setVisible(true);
	}

}
