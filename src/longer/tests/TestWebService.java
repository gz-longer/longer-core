package longer.tests;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import net.longersoft.framework.LongerWebService;
import net.longersoft.framework.ServiceHandler;
import net.longersoft.framework.ServiceRequest;
import net.longersoft.framework.ServiceResponse;
import junit.framework.TestCase;

public class TestWebService extends TestCase {
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	

	
	public void test1() throws Exception{
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		map.put("APP_KEY", "APP_KEY");
		map.put("SIGN_STRING", "SIGN_STRING");
		map.put("SIGN_VERSION", "SIGN_VERSION");
		map.put("TIMESTAMP", "TIMESTAMP");
		map.put("ACTION", MyWebService.class.getName() + ".hello");
		map.put("FORMAT", "json");
		
		String response = ServiceHandler.handle(map, map);
		System.out.println(response);
	}
	
	
	public static class MyWebService extends LongerWebService{
		public HelloResponse hello(HelloRequest request){
			return new HelloResponse();
		}
	}
	
	public static class HelloRequest extends ServiceRequest{
		
	}
	
	public static class HelloResponse extends ServiceResponse{
		@Override
		public Object toClient() throws Exception {
			JSONArray array = new JSONArray();
			array.put(true);
			array.put("2");
			array.put(3);
			return array;
		}
	}
}
