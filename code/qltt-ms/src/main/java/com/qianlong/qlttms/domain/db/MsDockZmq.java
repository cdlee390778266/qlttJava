package com.qianlong.qlttms.domain.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_ext_msdockzmq")
public class MsDockZmq implements Serializable {
	
	private String weixinAccountid;
	
	private String zmqsvcBusKey;
	
	private String zmqsvcHost;
	
	private int	zmqsvcPort;
	
	private int zmqsvcSndTimeout;
	
	private int zmqsvcRcvTimeout;
	
	@Id
	@Column(name = "weixin_accountid")
	public String getWeixinAccountid() {
		return weixinAccountid;
	}

	public void setWeixinAccountid(String weixinAccountid) {
		this.weixinAccountid = weixinAccountid;
	}
	
	@Id
	@Column(name = "zmqsvc_buskey")
	public String getZmqsvcBusKey() {
		return zmqsvcBusKey;
	}


	public void setZmqsvcBusKey(String zmqsvcBusKey) {
		this.zmqsvcBusKey = zmqsvcBusKey;
	}

	@Column(name = "zmqsvc_host")
	public String getZmqsvcHost() {
		return zmqsvcHost;
	}

	
	public void setZmqsvcHost(String zmqsvcHost) {
		this.zmqsvcHost = zmqsvcHost;
	}


	@Column(name = "zmqsvc_port")
	public int getZmqsvcPort() {
		return zmqsvcPort;
	}


	public void setZmqsvcPort(int zmqsvcPort) {
		this.zmqsvcPort = zmqsvcPort;
	}

	@Column(name = "zmqsvc_sndtimeout")
	public int getZmqsvcSndTimeout() {
		return zmqsvcSndTimeout;
	}


	public void setZmqsvcSndTimeout(int zmqsvcSndTimeout) {
		this.zmqsvcSndTimeout = zmqsvcSndTimeout;
	}

	@Column(name = "zmqsvc_rcvtimeout")
	public int getZmqsvcRcvTimeout() {
		return zmqsvcRcvTimeout;
	}


	public void setZmqsvcRcvTimeout(int zmqsvcRcvTimeout) {
		this.zmqsvcRcvTimeout = zmqsvcRcvTimeout;
	}

	
}
