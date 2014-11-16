package net.longersoft.exceptions;

public class ClientException extends RuntimeException implements ILongerException {
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public ClientException() {
		this.errorCode = this.getClass().getSimpleName();
	}
	
	public ClientException(String code, String msg){
		super(msg);
		this.errorCode = code;
	}

	@Override
	public String getErrorCode() {
		return this.errorCode;
	}
}
