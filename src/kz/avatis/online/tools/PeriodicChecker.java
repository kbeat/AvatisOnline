package kz.avatis.online.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class PeriodicChecker extends Thread {
	
	private boolean interrupted = false;
	private boolean isOnline = false;
	private static final int CHECK_INTERVAL = 30000; 
	
	public void interruptProcess() {
		interrupted = true;
	}
	
	public boolean isOnline() {
		return isOnline;
	}
	
	@Override
	public void run() {
		while(!interrupted) {
			try {
				InetAddress a = InetAddress.getByName("online.avatis.kz");
				isOnline = true;
				Log.m("Online: "+a.toString()+" at "+(new Date(System.currentTimeMillis())).toString());
				Thread.sleep(CHECK_INTERVAL);
			} catch (InterruptedException e) {
				// interruption during sleep
				// just skip it
			} catch (UnknownHostException e) {
				Log.m("Offline: at "+(new Date(System.currentTimeMillis())).toString());
				isOnline = false;
			}
		}
	}
}
