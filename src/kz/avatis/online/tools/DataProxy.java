package kz.avatis.online.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import kz.avatis.online.models.ExpertNameType;
import kz.avatis.online.models.ObjectFactory;
import kz.avatis.online.models.RecordType;
import kz.avatis.online.models.Records;
import kz.avatis.online.models.Response;
import kz.avatis.online.models.ServiceNameType;
import kz.avatis.online.models.SpecializationType;
import kz.avatis.online.models.Specializations;

import oracle.jrockit.jfr.openmbean.RecordingType;

import org.w3c.dom.Document;

import com.sun.org.apache.xerces.internal.util.URI.MalformedURIException;

public class DataProxy {
	
	private String _url, _query;
	private String _connectionToken;

	/*
	 * Constants
	 */
	public static final String QUERY_GETRECORDS = "?type=records";

	public DataProxy(String url) {
		_url = url;
		_connectionToken = null;
	}
	
	public String getToken() {
		return _connectionToken;
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
				_query += parameter.getKey() + "=" + parameter.getValue() + "&";
			}
		}
	}
	
	/*
	 * 
	 * Tries to login, if success than sets the token
	 * 
	 */
	public boolean login(Map<String, String> pars) {
		
		boolean result = false;
		connect(pars);
		
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Unmarshaller um = jc.createUnmarshaller();
			URL url = new URL(_url + _query);
			Response loginResponse = (Response)um.unmarshal(url);
			if (loginResponse.getStatus() == 1) {
				this._connectionToken = loginResponse.getToken();
				result = true;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Records getRecords() {
		Records result = null;
		
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Unmarshaller um = jc.createUnmarshaller();
			URL url = new URL(_url + QUERY_GETRECORDS+"&token="+_connectionToken);
			result = (Records)um.unmarshal(url);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
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
	
	public void justCheck() throws DatatypeConfigurationException {
		// shows the marshalling process
		try {
			JAXBContext jc = JAXBContext.newInstance("kz.avatis.online.models");
			Marshaller m = jc.createMarshaller();
			
			ObjectFactory of = new ObjectFactory();
			
			Records rs = of.createRecords();
			rs.setToken("34234");
			
			List data = rs.getRecord();
			
			
			for (int i=0;i<5;i++) {
				RecordType rt = new RecordType();
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(new Date(1234567890));
				XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
				
				rt.setStartTime(date2);
				rt.setEndTime(date2);
				ExpertNameType es = new ExpertNameType();
				es.setId(19*i);
				es.setValue("FOMA FOMA FOMA");
				rt.setExpert(es);
				ServiceNameType ss = new ServiceNameType();
				ss.setId(23*i);
				ss.setValue(String.valueOf(i)+" OTBELIVANIE");
				rt.setService(ss);
				rt.setApproved(false);
				rt.setPrice(i*541);
				rt.setClientName("EGOR EGORUSHKA");
				rt.setClientPhone("+5323423234");
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
