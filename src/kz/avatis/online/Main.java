package kz.avatis.online;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String,String> pars = new HashMap<String,String>();
		pars.put("par1", "val1");
		pars.put("par2", "val2");
		
		DataProxy dp = new DataProxy("http://192.168.1.103/avest/data.xml");
		dp.connect(pars);
		Specializations s = dp.getSpecializations();
		List<SpecializationType> ss = s.getSpecialization();
		System.out.println(s.getToken());
		for (SpecializationType st : ss) {
			System.out.println(st.getName());
		}
		/*
		Specializations ss = dp.getSpecializations();
		List<SpecializationType> st = ss.getSpecialization();
		//System.out.println()
		for (SpecializationType s : st) {
			System.out.println(s.getName());
		}*/
	}
}
