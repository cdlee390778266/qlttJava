package com.qianlong.qlttms.zmq;

public class ZMQMsgException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public ZMQMsgException(String message){
		super(message);
	}
	
	public ZMQMsgException(Throwable cause)
	{
		super(cause);
	}
	
	public ZMQMsgException(String message,Throwable cause)
	{
		super(message,cause);
	}

}
