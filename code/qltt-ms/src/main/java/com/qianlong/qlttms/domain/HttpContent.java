package com.qianlong.qlttms.domain;

public class HttpContent<T, E> {

	private T content;
	
	private E message;
	
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public E getMessage() {
		return message;
	}

	public void setMessage(E message) {
		this.message = message;
	}
}
