package kz.avatis.online.tools;

public class Log {
	public static void m(String message) {
		System.out.println(message);
	}
	
	public static void m(String type, String message) {
		System.out.println(type+": "+message);
	}
}
