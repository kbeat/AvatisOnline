package kz.avatis.online;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import kz.avatis.online.model.Record;
import kz.avatis.online.tools.Log;
import kz.avatis.online.tools.Translator;

public class RecordsTableModel extends AbstractTableModel {
	
	Vector<Record> rowData;
    int initialCapacity;
    boolean saved = true;
	String [] dataHeaders = {Translator.t("Time"), Translator.t("Date"), Translator.t("Service"), Translator.t("Master"), Translator.t("Client Name"), Translator.t("Client Phone")};
	Class[] columnClasses = {String.class, String.class, String.class,String.class,String.class,String.class};
	public RecordsTableModel() {
		rowData = new Vector<Record>();
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
		Record rt = rowData.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return rt.getStartTime();
		case 1:
			return rt.getEndTime();
		case 2:
			return (rt.getService() != null) ? rt.getService().getValue() : "";
		case 3:
			return (rt.getExpert() != null) ? rt.getExpert().getValue() : "";
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
        Record rt = rowData.get(rowIndex);
        Log.m("editing "+rowIndex+":"+columnIndex+" with "+aValue);
        switch (columnIndex) {
            case 0:
                rt.setStartTime(Integer.parseInt(aValue.toString()));
            case 1:
                rt.setEndTime(1);
            case 2:
                //rt.getService().setValue(aValue.toString());
            case 3:
                //rt.getExpert().setValue(aValue.toString());
            case 4:
                rt.setClientName(aValue.toString());
            case 5:
                rt.setClientPhone(aValue.toString());
            default:
                saved = false;
        }
	}
	
	public void addRow(Record rt) {
		rowData.add(rt);
	}
	
	public void setRowData(Vector v) {
		rowData = v;
        initialCapacity = v.capacity();
	}

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // allow modifying only new cells
        return (columnIndex > initialCapacity);

    }

    public void saveData() {
        saved = true;
    }
}
