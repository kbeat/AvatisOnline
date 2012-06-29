package kz.avatis.online;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import kz.avatis.online.models.RecordType;
import kz.avatis.online.models.Records;
import kz.avatis.online.tools.DataProxy;
import kz.avatis.online.tools.Translator;

public class MainPanel extends JPanel {
	
	JTable recordsTable;
	DefaultTableModel dtm;
	
	public MainPanel() {
		setLayout(new GridBagLayout());
				
		String [] dataHeaders = {Translator.t("Time"), Translator.t("Date"), Translator.t("Service"), Translator.t("Master"), Translator.t("Client Name"), Translator.t("Client Phone")};
		dtm = new DefaultTableModel(dataHeaders, 0);
		
		recordsTable = new JTable(dtm);
		recordsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane jsp = new JScrollPane(recordsTable);
		add(jsp, new GridBagConstraints(0, 0, 6, 1, 1, 1, GridBagConstraints.NORTHWEST,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button1"), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button2"), new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button3"), new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button4"), new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button5"), new GridBagConstraints(4, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		add(new JLabel("button6"), new GridBagConstraints(5, 1, 6, 1, 1, 1, GridBagConstraints.SOUTH,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		
		add(new JLabel(Translator.t("Connection status:")), new GridBagConstraints(0, 2, 6, 1, 1, 1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
	}
	
	public void fillTable(DataProxy dp) {
		Records r = dp.getRecords();
		List<RecordType> lrt = r.getRecord();
		
		Vector<String> rowData = new Vector<String>();
		for (RecordType rt : lrt) {
			rowData.add(rt.getStartTime().toString());
			rowData.add(rt.getStartTime().toString());
			rowData.add(rt.getService().getValue());
			rowData.add(rt.getExpert().getValue());
			rowData.add(rt.getClientName());
			rowData.add(rt.getClientPhone());
			dtm.addRow(rowData);
		}
	}
	
	
}
