package kz.avatis.online;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import kz.avatis.online.models.RecordType;
import kz.avatis.online.tools.Translator;

public class RecordsTableModel extends AbstractTableModel {
	
	Vector<RecordType> rowData;
	String [] dataHeaders = {Translator.t("Time"), Translator.t("Date"), Translator.t("Service"), Translator.t("Master"), Translator.t("Client Name"), Translator.t("Client Phone")};
	Class[] columnClasses = {String.class, String.class, String.class,String.class,String.class,String.class};
	public RecordsTableModel() {
		rowData = new Vector<RecordType>();
	}
	@Override
	public int getRowCount() {
		return rowData.size();
	}

	@Override
	public int getColumnCount() {
		return dataHeaders.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RecordType rt = rowData.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return rt.getStartTime();
		case 1:
			return rt.getEndTime();
		case 2:
			return rt.getService().getValue();
		case 3:
			return rt.getExpert().getValue();
		case 4:
			return rt.getClientName();
		case 5:
			return rt.getClientPhone();
		default:
			return null;
		}
		
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return dataHeaders[column];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RecordType rt = rowData.get(rowIndex);
		if (columnIndex == 1) {
			
		}
	}
	
	public void addRow(RecordType rt) {
		rowData.add(rt);
	}
	
	public void setRowData(Vector v) {
		rowData = v;
	}

}
