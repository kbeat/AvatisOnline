package kz.avatis.online;

import java.util.List;
import java.util.Vector;

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
		
		String [] dataHeaders = {Translator.t("Time"), Translator.t("Date"), Translator.t("Service"), Translator.t("Master"), Translator.t("Client Name"), Translator.t("Client Phone")};
		dtm = new DefaultTableModel(dataHeaders, 0);
	
		recordsTable = new JTable(dtm);
		
		add(new JScrollPane(recordsTable));
		
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
