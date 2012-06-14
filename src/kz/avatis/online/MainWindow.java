package kz.avatis.online;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.datatype.DatatypeConfigurationException;

import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Translator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 7727711190347516051L;
		
	public static void main(String [] args) throws DatatypeConfigurationException {
		final MainWindow mw = new MainWindow();
		final LoginPanel lp;
		final MainPanel mp;
		
		
		final CardLayout mcl = new MyCardLayout();
		mw.setLayout(mcl);
		
		
		DataProxy dp = new DataProxy("http://192.168.1.3");
		dp.justCheck();
		mp = new MainPanel();
		lp = new LoginPanel();
		lp.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Map<String, String> pars = new HashMap<String, String>();
				pars.put("login", lp.getLogin());
				pars.put("password", lp.getPassword());
				pars.put("type", "login");
				DataProxy dp = new DataProxy("http://192.168.1.103/avest/");
								
				if (dp.login(pars)) {
					mp.fillTable(dp);
					mcl.show(mw.getContentPane(), "main");
					mw.setExtendedState(Frame.MAXIMIZED_BOTH);
					mw.resize(1000, 1000);
					mw.repaint();
					mw.pack();
				} else {
					lp.setErrorMessage(Translator.t("Incorrect login or password!"));
				}
				
			}
		});
		mw.add(lp);
		mw.add(mp, "main");
		mw.pack();
		mw.setVisible(true);
	}

}
