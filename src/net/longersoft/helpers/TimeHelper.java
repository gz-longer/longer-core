package net.longersoft.helpers;

import java.util.Date;

import org.apache.log4j.Logger;

import net.longersoft.callbacks.Callback0;

public class TimeHelper {
	private static Logger log = Logger.getLogger(TimeHelper.class);
	
	public static void run(Callback0 callback){
		Date start = new Date();
		try{
			callback.execute();
		}catch (Exception e) {
			log.error(e);
		}
		Date finish = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("\nstart  at: %s", start.toLocaleString()));
		sb.append(String.format("\nfinish at: %s", finish.toLocaleString()));
		sb.append(String.format("\ntake time: %s ms", finish.getTime() - start.getTime()));
		
		log.info(sb);
	}
}
