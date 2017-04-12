package com.qianlong.qltt.us.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public  class QlttRuntimeException extends QlttUSException {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(QlttRuntimeException.class);
	
	private Object source;
	
	public QlttRuntimeException(Exception exception) {
		super(exception.getMessage());
		logger.error(exception.getMessage());
	}

	public QlttRuntimeException(Object source ,Exception exception) {
		this(exception);
		this.source = source;
	}

	public Object getSource() {
		return source;
	}
	
	public String getExceptionCode() {
		return ErrorCodeMaster.RUNTIME_EXCEPTION;
	}
}
