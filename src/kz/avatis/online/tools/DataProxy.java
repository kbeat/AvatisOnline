package kz.avatis.online.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kz.avatis.online.models.ObjectFactory;
import kz.avatis.online.models.RecordType;
import kz.avatis.online.models.Records;
import kz.avatis.online.models.SpecializationType;
import kz.avatis.online.models.Specializations;

import oracle.jrockit.jfr.openmbean.RecordingType;

import org.w3c.dom.Document;

public class DataProxy {
	
	private String _url, _query;
	private String _connectionToken;
		

	public DataProxy(String url) {
		_url = url;
		_connectionToken = null;
	}
	
	/*
	 * 
	 * method for data connection
	 * generates the query string containing the parameters for the URL
	 * 
	 * */
	public void connect(Map<String, String> parMap) {
		
		_query = "?";
		
		if (parMap != null) {
			Set<Map.Entry<String, String>> parameters = parMap.entrySet();
			for (Map.Entry<String, String> parameter: parameters) {
				_query += parameter.getKey() + "=" + parameter.getValue();
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
		} 
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Specializations getSpecializations() {
		
		Specializations result = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Unmarshaller um = jc.createUnmarshaller();
			URL url = new URL(_url);
			System.out.println(_url+_query);
			result = (Specializations)um.unmarshal(url);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void justCheck() {
		// shows the marshalling process
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Marshaller m = jc.createMarshaller();
			
			ObjectFactory of = new ObjectFactory();
			
			Records rs = of.createRecords();
			rs.setToken("34234");
			
			List data = rs.getRecord();
			
			RecordType rt = new RecordType();
			for (int i=0;i<5;i++) {
				rt.setApproved(false);
				data.add(rt);
			}
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(rs, new FileOutputStream("records.xml"));
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
