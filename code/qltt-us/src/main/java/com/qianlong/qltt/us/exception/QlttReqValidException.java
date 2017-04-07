package com.qianlong.qltt.us.exception;

public  class QlttReqValidException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	private static final String exceptionCode = ErrorCodeMaster.REQ_PARAMETER_FORMAT_NOT_CORRCT;
	
	private Object source;
	
	private Exception exception;
	
	public QlttReqValidException(){
		super(ErrorCodeMaster.getMessage(exceptionCode));
	}

	public QlttReqValidException(String msg){
		super(msg);
	}
	
	public QlttReqValidException(String msg,Object source) {
		this(msg);
		this.source = source;
	}

	public QlttReqValidException(String msg,Object source ,Exception e) {
		this(msg,source);
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
