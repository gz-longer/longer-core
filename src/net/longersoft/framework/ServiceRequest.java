package net.longersoft.framework;

import java.util.Map;
import net.longersoft.helpers.StringHelper;

public abstract class ServiceRequest {
	protected ServiceSession session = null;
	
	private String requestId;
	protected Map<String, Object> params;
	
	public ServiceRequest() {
	}
	
	public void fromClient(Map<String, Object> map) throws Exception{
		this.params = map;
	}

	public void setRequestId(String requestId){ this.requestId = requestId; }
	public String getRequestId() { return this.requestId; }
	public Map<String, Object> getParameters() { return this.params; }
	
	public String getMethodName() {
		return StringHelper.lowerFirst(this.getClass().getSimpleName().replace("Request", ""));
	}
	
	public void setServiceSession(ServiceSession session){
		this.session = session;
	}
	
	public ServiceSession getServiceSession(){
		return this.session;
	}
}
