package com.qianlong.qlttms.zmq;

public class ZMQCommException extends RuntimeException  {
	
	private static final long serialVersionUID = 2L;

	public ZMQCommException(String message){
		super(message);
	}
	
	public ZMQCommException(Throwable cause)
	{
		super(cause);
	}
	
	public ZMQCommException(String message,Throwable cause)
	{
		super(message,cause);
	}

}
