package kz.avatis.online.tools;

import java.net.URL;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DataProvider {
	
	private String _url;
	private String _connectionToken;
	Document xmlDoc;	
	public DataProvider(String url) {
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
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			xmlDoc = db.parse(new URL(_url + query).openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String login(Map<String, String> pars) {
		connect(pars);
		return null;
	}

}
