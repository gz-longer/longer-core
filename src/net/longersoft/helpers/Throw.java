package net.longersoft.helpers;

import java.util.List;

import net.longersoft.exceptions.ErrorCodes;
import net.longersoft.exceptions.LongerException;

public class Throw {
	public static void ifParameterEmpty(Object parameter, String name) throws Exception{
		ifEmpty(parameter, ErrorCodes.Param_Missing, String.format("parameter '%s' can not be empty.", name));
	}
	
	public static void ifEmpty(Object obj, String code, String msg) throws Exception{
		if(obj == null || obj.toString().length() == 0) throw new LongerException(code, msg);
	}
	
	public static void ifEmpty(List<?> list, String code, String msg) throws Exception{
		if(list == null || list.size() == 0) throw new LongerException(code, msg);
	}
	
	public static void ifNull(Object obj, String code, String msg) throws Exception{
		if( obj == null) throw new LongerException(code, msg);
	}
	
	public static void ifTrue(boolean condition, String code, String error) throws Exception{
		if(condition) throw new LongerException(code, error);
	}
}
