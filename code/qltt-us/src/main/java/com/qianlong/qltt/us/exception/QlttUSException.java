package com.qianlong.qltt.us.exception;

public  class QlttUSException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
	
	private Object source;
	

	public QlttUSException() {
		super();
	}

	public QlttUSException(final String msg) {
		super(msg);
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public Object getSource() {
		return source;
	}
}
