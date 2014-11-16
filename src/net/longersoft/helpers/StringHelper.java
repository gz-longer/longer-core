package net.longersoft.helpers;

public class StringHelper {
	public static String lowerFirst(String s){
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
	
	public static String upperFirst(String s){
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	public static boolean empty(String s){
		return s == null || s.length() == 0;
	}
}
