package kz.avatis.online;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

import kz.avatis.online.models.RecordType;
import kz.avatis.online.models.Records;
import kz.avatis.online.tools.Constants;
import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Log;
import kz.avatis.online.tools.PeriodicChecker;
import kz.avatis.online.tools.Translator;

public class FrontFrame extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private RecordsTableModel dtm;
	PeriodicChecker pc;
	
	/**
	 * Fill the records table
	 * @param dp
	 */
	public void fillTable(DataProxy dp) {
		Records r = dp.getData(Constants.QUERY_GETRECORDS);
		List<RecordType> lrt = r.getRecord();
		
		Vector<RecordType> rowData = new Vector<RecordType>(lrt);
		dtm.setRowData(rowData);
	}
	
	/**
	 * the periodic checker stuff
	 */
	public void startTicking() {
		pc = new PeriodicChecker();
		pc.start();
	}
	public void stopTicking() {
		pc.interrupt();
	}
	public PeriodicChecker getPeriodicChecker() {
		return pc;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Show the GUI
	 */
	private static void createAndShowGUI() {
		
		final FrontFrame frame = new FrontFrame();
		
		DataProxy dp = new DataProxy(Constants.CONNECTION_URL);
		LoginDialog ld = new LoginDialog(frame);
		ld.setDataProxy(dp);
		ld.setVisible(true);

		if (ld.isSucceeded()) {
			
			Log.m("Succesfully connected to "+Constants.CONNECTION_URL+" with token = "+dp.getToken());
			
			// fill the records table
			frame.fillTable(dp);
			frame.setSize(1000, 1000);
			frame.pack();
			
			frame.setVisible(true);
			frame.startTicking();
		}	
		
		frame.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  if (frame.getPeriodicChecker()!=null && frame.getPeriodicChecker().isAlive()) frame.stopTicking();
		    	  System.exit(0);
		      }
			}
		);
	}
	
	/**
	 * Create the frame.
	 */
	public FrontFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblMyRecords = new JLabel("My records");
		GridBagConstraints gbc_lblMyRecords = new GridBagConstraints();
		gbc_lblMyRecords.anchor = GridBagConstraints.WEST;
		gbc_lblMyRecords.insets = new Insets(0, 0, 5, 0);
		gbc_lblMyRecords.gridx = 0;
		gbc_lblMyRecords.gridy = 0;
		contentPane.add(lblMyRecords, gbc_lblMyRecords);
		
		JScrollPane tablePanel = new JScrollPane();
		GridBagConstraints gbc_tablePanel = new GridBagConstraints();
		gbc_tablePanel.insets = new Insets(0, 0, 5, 0);
		gbc_tablePanel.fill = GridBagConstraints.BOTH;
		gbc_tablePanel.gridx = 0;
		gbc_tablePanel.gridy = 1;
		contentPane.add(tablePanel, gbc_tablePanel);
		
		/*
		 * Some stuff for filling the records data table
		 */
		
		dtm = new RecordsTableModel();
		
		dataTable = new JTable(dtm);
		tablePanel.setViewportView(dataTable);
		
		JPanel buttonsPanel = new JPanel();
		FlowLayout fl_buttonsPanel = (FlowLayout) buttonsPanel.getLayout();
		fl_buttonsPanel.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_buttonsPanel = new GridBagConstraints();
		gbc_buttonsPanel.insets = new Insets(0, 0, 5, 0);
		gbc_buttonsPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonsPanel.gridx = 0;
		gbc_buttonsPanel.gridy = 2;
		contentPane.add(buttonsPanel, gbc_buttonsPanel);
		
		JButton btnNewButton = new JButton("Create Record");
		buttonsPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Selected");
		buttonsPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		buttonsPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		buttonsPanel.add(btnNewButton_3);
		
		JPanel statusPanel = new JPanel();
		GridBagConstraints gbc_statusPanel = new GridBagConstraints();
		gbc_statusPanel.anchor = GridBagConstraints.SOUTH;
		gbc_statusPanel.fill = GridBagConstraints.BOTH;
		gbc_statusPanel.gridx = 0;
		gbc_statusPanel.gridy = 3;
		contentPane.add(statusPanel, gbc_statusPanel);
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Connection status:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		statusPanel.add(lblNewLabel);
	}
}
