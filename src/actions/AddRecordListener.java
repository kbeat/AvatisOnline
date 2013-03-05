package actions;

import kz.avatis.online.RecordsTableModel;
import kz.avatis.online.model.ObjectFactory;
import kz.avatis.online.model.Record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Home
 * Date: 05.03.13
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */
public class AddRecordListener implements ActionListener{

    private final RecordsTableModel rtm;

    public AddRecordListener(RecordsTableModel rtm) {
        super();
        this.rtm = rtm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ObjectFactory of = new ObjectFactory();
        Record record = of.createRecord();

        rtm.addRow(record);
        rtm.fireTableDataChanged();

        // save data to site
    }
}
