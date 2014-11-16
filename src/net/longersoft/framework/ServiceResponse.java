package net.longersoft.framework;

import java.util.*;

import net.longersoft.helpers.StringHelper;
import org.json.*;

public abstract class ServiceResponse{
	protected Object result = "?";
	protected Object value = null;
	
	public ServiceResponse() {
	}
	
	public ServiceResponse returnResult(Object result){
		this.result = result;
		return this;
	}
	
	public Object getResult(){
		return this.result;
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public ServiceResponse returnSuccess(){
		this.result = "success";
		return this;
	}
	
	public ServiceResponse returnRows(Collection<?> rows){
		return this.returnKeyValue("rows", rows);
	}
	
	public ServiceResponse returnId(String id){
		return this.returnKeyValue("id", id);
	}
	
	public ServiceResponse returnKeyValue(String key, Object value){
		this.value = value;
		try{
			this.result = new JSONObject().put(key, value);
			return this;
		}catch(Exception exp){
			throw new RuntimeException();
		}
	}
	
	public ServiceResponse returnPage(Collection<?> rows, int total){
		try{
			this.result = new JSONObject().put("rows", rows)
										.put("total", total);
			return this;
		}catch(Exception exp){
			throw new RuntimeException();
		}
	}
	
	public String getMethodName() {
		return StringHelper.lowerFirst(this.getClass().getSimpleName().replace("Response", ""));
	}
	
	public Object toClient() throws Exception{
		return this.result;
	}
}
