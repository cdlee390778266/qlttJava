package com.qianlong.qltt.us.exception;

public  class QlttReqValidException extends QlttUSException {

private static final long serialVersionUID = 1L;
	
	private Object source;

	public QlttReqValidException(String msg){
		super(msg);
	}
	
	public QlttReqValidException(String msg,Object source) {
		this(msg);
		this.source = source;
	}

	public String getExceptionCode() {
		return ErrorCodeMaster.REQ_PARAMETER_FORMAT_NOT_CORRCT;
	}

	public Object getSource() {
		return source;
	}
}
