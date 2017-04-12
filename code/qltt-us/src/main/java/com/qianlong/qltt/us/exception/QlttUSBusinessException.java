package com.qianlong.qltt.us.exception;

public  class QlttUSBusinessException extends QlttUSException{

	private static final long serialVersionUID = 1L;
	
	private String exceptionCode;
	
	private Object source;

	public QlttUSBusinessException(String exceptionCode){
		super(ErrorCodeMaster.getMessage(exceptionCode));
		this.exceptionCode = exceptionCode;
		
	}
	
	public QlttUSBusinessException(String exceptionCode,Object source) {
		this(exceptionCode);
		this.source = source;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public Object getSource() {
		return source;
	}
}
