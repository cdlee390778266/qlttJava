package com.qianlong.qltt.zbas.exception;
/**
 * 描述:基础异常类
 */
public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BaseException() {
		super();
	}

	public BaseException(final String msg) {
		super(msg);
	}
}
