package kz.avatis.online.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kz.avatis.online.models.Records;
import kz.avatis.online.models.SpecializationType;
import kz.avatis.online.models.Specializations;

import org.w3c.dom.Document;

public class DataProxy {
	
	private String _url;
	private String _connectionToken;
		

	public DataProxy(String url) {
		_url = url;
		_connectionToken = null;
	}
	
	private void connect(Map<String, String> parMap) {
		
		String query = "?";
		
		if (parMap != null) {
			Set<Map.Entry<String, String>> parameters = parMap.entrySet();
			for (Map.Entry<String, String> parameter: parameters) {
				query += parameter.getKey() + "=" + parameter.getValue();
			}
		}
	}
	
	public String login(Map<String, String> pars) {
		connect(pars);
		return null;
	}
	
	public Records getRecords() {
		Records result = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Unmarshaller um = jc.createUnmarshaller();
			
			//result = (Specializations)um.unmarshal(new FileInputStream("result.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Specializations getSpecializations() {
		
		Specializations result = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Unmarshaller um = jc.createUnmarshaller();
			
			result = (Specializations)um.unmarshal(new FileInputStream("result.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
