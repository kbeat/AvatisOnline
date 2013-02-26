package kz.avatis.online.tools;

import kz.avatis.online.model.LoginType;
import kz.avatis.online.model.Response;
import kz.avatis.online.models.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DataProxy {
	
	private String _url, _query;
	private String _connectionToken;
	private JAXBContext jc = null;

	public DataProxy(String url) {
		_url = url;
		_connectionToken = null;
		try {
			jc = JAXBContext.newInstance("kz.avatis.online.model");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		System.out.println(_query);
	}
	
	/*
	 * 
	 * Tries to login, if success than sets the token
	 * 
	 */
	public boolean login(String login, String pass) {
		
		boolean result = false;
		Map<String, String> pars = new HashMap<String, String>();
		pars.put("login", login);
		pars.put("password", pass);
		pars.put("type", Constants.QUERY_LOGIN);
		connect(pars);
		
		try {
            Unmarshaller um = jc.createUnmarshaller();

            URL url = new URL(_url + _query);

			Response response = (Response)um.unmarshal(url);

            LoginType loginResponse = (LoginType) response.getAny();

            if (loginResponse.getStatus() == 1) {
				this._connectionToken = loginResponse.getToken();
				result = true;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
	/**
	 * Gets any simple data by request depending on the query that is taken from Constants.QUERY*.
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData(String query) {
		T result = null;
		
		try {
            Unmarshaller um = jc.createUnmarshaller();
            URL url = new URL(_url + "?type="+query+"&token="+_connectionToken);

            result = (T)um.unmarshal(url);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
	
	
	/*
	 * 
	 * Synchronizes the lists with the online database. The object ID is the key field on which the synchronization is taken.
	 * The following lists are synced:
	 * - Experts
	 * - 
	 * 
	 */
	public void synchronize(int status) {
		switch (status) {
		case Constants.SYNCHRONIZE_SENDDATA :
			sendData();
			break;
		case Constants.SYNCHRONIZE_GETDATA :
			break;
		}
	}
	
	private void sendData() {
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Specializations getSpecializations() {
		
		Specializations result = null;
		
		try {
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
				rt.setId(new Random().nextInt());
				rt.setStartTime(4345345);
				rt.setEndTime(345345322);
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
