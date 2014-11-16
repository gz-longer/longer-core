package net.longersoft.helpers;

import java.util.Map;
import java.util.Map.Entry;

import net.longersoft.exceptions.ClientException;
import net.longersoft.exceptions.ErrorCodes;

public class MapHelper {
	public static String getAndRemove(Map<String, Object> map,String key){
		String val = (String)map.remove(key);
		if(val == null || val.length() == 0) 
			throw new ClientException(ErrorCodes.Param_Missing, String.format("parameter %s missing.", key));
		return val; 
	}
	
	public static String getAndRemove(Map<String, Object> map,String key, String defaultValue){
		String val = (String)map.remove(key);
		if(val == null || val.length() == 0) return defaultValue;
		return val;
	}
	
	public static Object getWithoutRemove(Map<String, Object> map,String key, Object defaultValue){
		if( map.containsKey(key) ){
			return map.get(key);
		}else{
			return defaultValue;
		}
	}
	
	
	public static void printMap(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder("dump map{\n");
		
		for(Entry<String, Object> item : map.entrySet()){
			sb.append(String.format("\t%s=%s:%s, \n", item.getKey(), item.getValue(), 
					item.getValue() == null ? "<null>" : item.getValue().getClass().getName()));
		}
		
		sb.append("}");
		
		System.out.println(sb);
	}	
}
