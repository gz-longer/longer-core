package net.longersoft.helpers;

public class Convert {
	public static int toInt(Object o){
		return Integer.parseInt(toString(o));
	}
	
	public static String toString(Object o){
		if(o == null) return null;
		return "" + o;
	}
	
	public static Boolean toBoolean(Object o, Boolean defaultVal){
		if(o == null) return defaultVal;
		
		String s = o.toString().toLowerCase();
		return "true".equals(s) || "1".equals(s);
	}
	
	public static Boolean toBoolean(Object o) throws Exception{
		if(o == null) throw new Exception();
		
		String s = o.toString().toLowerCase();
		return "true".equals(s) || "1".equals(s);
	}
	
	public static Float toFloat (Object o) throws Exception{
		if(o == null) throw new Exception();
		
		return Float.parseFloat(o.toString());
	}
}
