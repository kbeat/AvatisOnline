package kz.avatis.online;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import kz.avatis.online.models.ObjectFactory;
import kz.avatis.online.models.SpecializationType;
import kz.avatis.online.models.Specializations;
import kz.avatis.online.tools.DataProxy;

public class Main {
	public static void main(String [] args) {
		
		MainWindow main = new MainWindow();
		JDialog loginBox = new JDialog(main);
		
		loginBox.setVisible(true);
		
	}
}
