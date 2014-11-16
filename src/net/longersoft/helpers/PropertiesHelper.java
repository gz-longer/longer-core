package net.longersoft.helpers;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesHelper {
	private static Logger log = Logger.getLogger(PropertiesHelper.class);
	private static Properties properties = new Properties();
	
	public static String getProperty(String key){
		makesureProperties();
		if(properties.containsKey(key)){
			return properties.getProperty(key);
		}
		return null;
	}
	
	public static Object getObject(String key){
		return getObject(key, new Class[]{}, new Object[]{});
	}
	
	@SuppressWarnings("unchecked")
	public static Object getObject(String key, Object... objects){
		Class[] classes = new Class[objects.length];
		for(int i = 0; i<objects.length; i++){
			classes[i] = objects[i].getClass();
		}
		return getObject(key, classes, objects);
	}
	
	@SuppressWarnings("unchecked")
	public static Object getObject(String key, Class[] classes, Object[] objects){
		String className = getProperty(key);
		if(className == null || className.length() == 0) throw new RuntimeException(String.format("can not find the config of key: %s", key));
		try{
			return Class.forName(className).getConstructor(classes).newInstance(objects);
		}catch(Exception exp){
			log.error(exp);
			return null;
		}
	}
	
	

	private static void makesureProperties() {
		FileInputStream file = null;
		try{
			String fileName = PropertiesHelper.class.getResource("/longer.properties").getFile();
			file = new FileInputStream(fileName);
			InputStream input = new BufferedInputStream(file);
			properties.load(input);
		}catch(Exception exp){
			log.error(exp);
		}finally{
			try{
				if( file != null) file.close();
			}catch(Exception exp){
				log.error(exp);
			}
		}
	}
}
