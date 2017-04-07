package com.qianlong.qltt.us.exception;

public  class QlttUSException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
	
	private Object source;
	
	private Exception exception;

	public QlttUSException(String exceptionCode){
		super(ErrorCodeMaster.getMessage(exceptionCode));
		this.exceptionCode = exceptionCode;
		
	}
	
	public QlttUSException(String exceptionCode,Object source) {
		this(exceptionCode);
		this.source = source;
	}

	public QlttUSException(String exceptionCode,Object source ,Exception e) {
		this(exceptionCode, source);
		this.exception = e;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public Object getSource() {
		return source;
	}

	public Exception getException() {
		return exception;
	}
}
