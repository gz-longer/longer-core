package net.longersoft.framework;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class LongerWebService {
	public static Logger log = Logger.getLogger(LongerWebService.class);
	
	Method[] methods;
	protected ServiceSession session = null;
	
	public LongerWebService() {
		methods = this.getClass().getDeclaredMethods();
		this.session = new ServiceSession().initByUserId("system");
	}
	
	public ServiceResponse handle(ServiceRequest request) throws Throwable {
		String methodName = request.getMethodName();
		for(Method m : methods){
			if(m.getName().equalsIgnoreCase(methodName)){
				try{
					Object result = m.invoke(this, request);
					return (ServiceResponse)result;
				}catch(InvocationTargetException exp){
					throw exp.getTargetException();
				}
			}
		}		
		throw new NoSuchMethodException();
	}
}
