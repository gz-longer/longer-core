package net.longersoft.framework;

import java.util.HashMap;
import java.util.Map;

public class ServiceSession {
	public static final String SESSION_ID = "SESSION-ID";
	
	public static final String USER_ID = "USER-ID";
	public static final String USER_NAME = "USER-NAME";
	
	public static final String DEPT_ID = "DEPT-ID";
	public static final String DEPT_NAME = "DEPT-NAME";
	
	public static final String USER_ROLES = "USER-ROLES";
	
	public ServiceSession initByUserId(String userId){
		this.setUserId(userId);
		return this;
	}
	
	private Map<String, Object> params = new HashMap<String, Object>();
	
	public String getSessionId(){
		return this.get(SESSION_ID, "").toString();
	}
	
	public void setSessionId(String sessionId){
		this.put(SESSION_ID, sessionId);
	}
	
	public String getUserId(){
		return this.get(USER_ID, "").toString();
	}
	
	public void setUserId(String userId){
		this.put(USER_ID, userId);
	}
	
	public String getUserName(){
		return this.get(USER_NAME, "").toString();
	}
	
	public void setUserName(String userName){
		this.put(USER_NAME, userName);
	}
	
	public String getDeptId(){
		return this.get(DEPT_ID, "").toString();
	}
	
	public void setDeptId(String deptId){
		this.put(DEPT_ID, deptId);
	}
	
	public String getDeptName(){
		return this.get(DEPT_NAME, "").toString();
	}
	
	public void setDeptName(String deptName){
		this.put(DEPT_NAME, deptName);
	}
	
	public String getUserRoles(){
		return this.get(USER_ROLES, "").toString();
	}
	
	public void setUserRoles(String userRoles){
		this.put(USER_ROLES, userRoles);
	}
	
	public ServiceSession() {
	}
	
	public Map<String, Object> getParameters(){
		return this.params;
	}
	
	public void setParameters(Map<String, Object> params){
		this.params = params;
	}
	
	private Object get(String key, Object defaultValue){
		Object obj = this.params.get(key);
		if(obj == null ) return defaultValue;
		return obj;
	}
	
	private void put(String key, Object value){
		this.params.put(key, value);
	}
}
