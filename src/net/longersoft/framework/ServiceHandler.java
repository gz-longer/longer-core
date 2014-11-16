package net.longersoft.framework;

import java.util.Map;
import java.util.UUID;

import net.longersoft.helpers.MapHelper;
import net.longersoft.helpers.StringHelper;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.omg.CORBA.ServerRequest;

public class ServiceHandler {
	private static Logger log = Logger.getLogger(ServiceHandler.class);
	
	public static String handle(Map<String, Object> requestParams, 
			Map<String, Object> sessionParams) throws Exception
	{
		String format = MapHelper.getAndRemove(requestParams, "FORMAT", "json");
		ServiceResponse result = null;
		String requestId = "req-" + UUID.randomUUID();
		
		try{
			// verify signature
			String appKey = MapHelper.getAndRemove(requestParams, "APP_KEY");
			String signString = MapHelper.getAndRemove(requestParams, "SIGN_STRING");
			String signVersion = MapHelper.getAndRemove(requestParams, "SIGN_VERSION");
			String timestamp = MapHelper.getAndRemove(requestParams, "TIMESTAMP");
			///TODO: sign & auth
			
			// call method
			String action = MapHelper.getAndRemove(requestParams, "ACTION");
			String className = dirname(action);
			String className0 = basename(className);
			String methodName = basename(action);
			
			String simpleRequestName = StringHelper.upperFirst(methodName + "Request");
			String simpleResponseName = StringHelper.upperFirst(methodName + "Response");
			String requestClassName = className.replace(className0, simpleRequestName);
			log.info(String.format("SERVICE> %s %s(%s request)", simpleResponseName, methodName, requestClassName));
			
			
			Class<?> serviceClass = Class.forName(className);
			Class<?> requestClass = Class.forName(requestClassName);
			
			LongerWebService service = (LongerWebService)serviceClass.newInstance();
			ServiceRequest request = (ServiceRequest)requestClass.newInstance();
			ServiceSession session = new ServiceSession();
			session.setParameters(sessionParams); 
			
			request.setServiceSession(session);
			request.setRequestId(requestId);
			request.fromClient(requestParams);
			result = service.handle(request);
		}catch(Throwable exp){
			exp.printStackTrace();
			result = new ErrorResponse(exp);
		}
		
		JSONObject jsonResponse = makeJsonResponse(result, requestId);
		
		if(format.equals("json")){
			return jsonResponse.toString();
		}else{
			return XML.toString(jsonResponse);
		} 
	}
	
	private static JSONObject makeJsonResponse(ServiceResponse result, String requestId) throws Exception{
		String methodName = result.getMethodName();
		return new JSONObject().put(methodName + "Response", new JSONObject()
		.put(methodName + "Result", result.toClient())
		.put("requestId", requestId));
	}	
	
	private static String dirname(String s){
		int dot = s.replace("$", ".").lastIndexOf(".");
		return s.substring(0, dot);
	}
	
	private static String basename(String s){
		int dot = s.replace("$", ".").lastIndexOf(".");
		return s.substring(dot+1);
	}
}

