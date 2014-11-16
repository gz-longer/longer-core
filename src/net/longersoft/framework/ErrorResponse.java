package net.longersoft.framework;

import net.longersoft.exceptions.ILongerException;
import org.json.JSONObject;

public class ErrorResponse extends ServiceResponse{
	private Throwable exp = null;
	
	public ErrorResponse(Throwable exp){
		this.exp = exp;
	}
	
	@Override
	public Object toClient() throws Exception {
		return this.toClient(exp);
	}
	
	private Object toClient(Throwable exp) throws Exception{
		String errorCode;
		
		if(exp instanceof ILongerException){
			errorCode = ((ILongerException)exp).getErrorCode();
		}else{
			errorCode = exp.getClass().getSimpleName();
		}
		
		return new JSONObject()
		.put("code", errorCode)
		.put("message", exp.getMessage() + "");		
	}
}