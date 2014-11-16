package net.longersoft.exceptions;

public class LongerException extends Exception implements ILongerException {
	private static final long serialVersionUID = 1L;
	
	private String code;
	public LongerException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getErrorCode(){
		return this.code;
	}
}
