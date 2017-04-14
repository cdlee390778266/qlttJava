package com.qlcd.util;

public class CommZMQException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public CommZMQException(String message){
		super(message);
	}
	
	public CommZMQException(Throwable cause)
	{
		super(cause);
	}
	
	public CommZMQException(String message,Throwable cause)
	{
		super(message,cause);
	}

}
