package net.longersoft.helpers;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.longersoft.framework.LongerModule;

import org.apache.log4j.Logger;

public class ReflectionHelper {
	private static Logger log = Logger.getLogger(ReflectionHelper.class);
	
	public static Object getFieldValue(Class<?> clazz, Object obj, String fieldName){
		if(!fieldName.contains(".")){
			return getFieldValue0(clazz, obj, fieldName);
		}
		
		int index = fieldName.indexOf(".");
		String current = fieldName.substring(0, index);
		String left = fieldName.substring(index + 1);
		Object currentObj = getFieldValue(clazz, obj, current);
		if( currentObj == null) return null;
		
		return getFieldValue(currentObj.getClass(), currentObj, left);
	}
	
	private static Object getFieldValue0(Class<?> clazz, Object obj, String fieldName){
		Field field = null;
		
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			
		}
		
		if(field != null){
			try {
				field.setAccessible(true);
				return field.get(obj);
			} catch(Exception e){
				log.error(e);
			}
		}
		
		log.warn(String.format("field %s not found", fieldName));
		return null;
	}
	
	public static List<Class<?>> getClasses(LongerModule module) throws ClassNotFoundException {
		List<Class<?>> list = new ArrayList<Class<?>>();
		
		for(Class<?> c : module.getClasses())
			list.add(c);
		
		return list;
	}
	
	public static List<Class<?>> getClasses(Package _package) throws ClassNotFoundException {
		String pckgname = _package.getName();
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		// Get a File object for the package
		File directory = null;
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			String path = pckgname.replace('.', '/');
			URL resource = cld.getResource(path);
			if (resource == null) {
				throw new ClassNotFoundException("No resource for " + path);
			}
			directory = new File(resource.getFile());
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname + " (" + directory
					+ ") does not appear to be a valid package");
		}
		if (directory.exists()) {
			// Get the list of the files contained in the package
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				// we are only interested in .class files
				if (files[i].endsWith(".class")) {
					// removes the .class extension
					classes.add(Class.forName(pckgname + '.'
							+ files[i].substring(0, files[i].length() - 6)));
				}
			}
		} else {
			throw new ClassNotFoundException(pckgname
					+ " does not appear to be a valid package");
		}
		return classes;
	}

}
