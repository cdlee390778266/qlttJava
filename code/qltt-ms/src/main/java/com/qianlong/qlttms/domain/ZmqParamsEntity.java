package com.qianlong.qlttms.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "zmq_params")
public class ZmqParamsEntity implements Serializable{
	 
	private static final long serialVersionUID = 6966388793784729187L;
	
	public static final  String ZMQ_PUSH_SAVE_DEFAULT = "0";

	private String weixinAccountId;
	
	private Integer zmqDirection;
	
	private String zmqProxyHost;
	
	private  Integer zmqProxyPort;
	
	private Integer zmqPushSave;
	
	private String wechatId;

	@Id
	@Column(name ="accountid",nullable=false,length=36)
	public String getWeixinAccountId() {
		return weixinAccountId;
	}

	public void setWeixinAccountId(String weixinAccountId) {
		this.weixinAccountId = weixinAccountId;
	}

	@Id
	@Column(name ="zmq_direction")
	public Integer getZmqDirection() {
		return zmqDirection;
	}

	public void setZmqDirection(Integer zmqDirection) {
		this.zmqDirection = zmqDirection;
	}

	@Column(name ="zmq_proxy_host")
	public String getZmqProxyHost() {
		return zmqProxyHost;
	}

	public void setZmqProxyHost(String zmqProxyHost) {
		this.zmqProxyHost = zmqProxyHost;
	}

	@Column(name ="zmq_proxy_port")
	public Integer getZmqProxyPort() {
		return zmqProxyPort;
	}

	public void setZmqProxyPort(Integer zmqProxyPort) {
		this.zmqProxyPort = zmqProxyPort;
	}

	@Column(name ="zmq_push_save")
	public Integer getZmqPushSave() {
		return zmqPushSave;
	}

	public void setZmqPushSave(Integer zmqPushSave) {
		this.zmqPushSave = zmqPushSave;
	}

	@Transient
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@Override
	public String toString() {
		return "ZmqParamsEntity [weixinAccountId=" + weixinAccountId
				+ ", zmqDirection=" + zmqDirection + ", zmqProxyHost="
				+ zmqProxyHost + ", zmqProxyPort=" + zmqProxyPort
				+ ", zmqPushSave=" + zmqPushSave + ", wechatId=" + wechatId
				+ "]";
	}
}
