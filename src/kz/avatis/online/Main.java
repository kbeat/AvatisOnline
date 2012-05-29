package kz.avatis.online;

import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import kz.avatis.online.models.ObjectFactory;
import kz.avatis.online.models.SpecializationType;
import kz.avatis.online.models.Specializations;
import kz.avatis.online.tools.DataProxy;

public class Main {
	public static void main(String [] args) {
		/*try {
			ObjectFactory of = new ObjectFactory();
			Specializations s = (Specializations)of.createSpecializations();
			List l = s.getSpecialization();
			SpecializationType st = new SpecializationType();st.setName("gg");
			l.add(st);
			st.setName("sdfsdf");
			l.add(st);
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			
			m.marshal(s, new FileOutputStream("result.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		DataProxy dp = new DataProxy("url");
		Specializations ss = dp.getSpecializations();
		List<SpecializationType> st = ss.getSpecialization();
		//System.out.println()
		for (SpecializationType s : st) {
			System.out.println(s.getName());
		}
	}
}
