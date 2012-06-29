package kz.avatis.online;

import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.datatype.DatatypeConfigurationException;

import kz.avatis.online.tools.Constants;
import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Log;
import kz.avatis.online.tools.PeriodicChecker;
import kz.avatis.online.tools.Translator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 7727711190347516051L;

	static PeriodicChecker pc;
	static DataProxy dp;
	
	public MainWindow() {
		// maximized
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
	}
	
	public void createAndShowGUI() {
			
		MainPanel mp = new MainPanel();
		dp = new DataProxy(Constants.CONNECTION_URL);
		LoginDialog ld = new LoginDialog(this);
		ld.setDataProxy(dp);
		ld.setVisible(true);

		if (ld.isSucceeded()) {
			
			Log.m("Succesfully connected to "+Constants.CONNECTION_URL+" with token = "+dp.getToken());
			
			// fill the records table
			mp.fillTable(dp);
			add(mp);
			setVisible(true);
			
		}	
		
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  if (pc!=null && pc.isAlive()) pc.interruptProcess();
		    	  System.exit(0);
		      }
			}
		);
	}
	
	public static void main(String [] args) throws DatatypeConfigurationException {
		
		MainWindow mw = new MainWindow();
		mw.createAndShowGUI();
	}
	
	

}
