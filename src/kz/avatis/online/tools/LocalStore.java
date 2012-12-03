package kz.avatis.online.tools;

import java.io.File;
import java.util.Properties;

public class LocalStore {
	public LocalStore() {
				
	}
	
	public void createStore() {
		Properties props = System.getProperties();
		String homedir = props.getProperty("user.home");
		
		if (homedir != null) {
			Log.m("INFO","Found home directory at "+homedir);
			
			if (!(new File(homedir+"/_store")).exists()) {
				Boolean success = (new File(homedir+"/_store")).mkdirs();
				if (!success) {
				    Log.m("ERROR","Storage folder is not created");
				}
			} else 
				Log.m("INFO","The storage already exisits here, nothing is created");
		} else {
			Log.m("ERROR", "Can't get the username from the env vars");
		}
	}
}
