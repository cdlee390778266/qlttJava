package com.qianlong.qlttms.domain.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "t_ext_msdockhttp")
public class MsDockHttp implements Serializable {

	/** 主键 */
	private int id;

	private String weixinAccountid;

	private String httpsvcBusKey;

	private String httpsvcHost;

	private int httpsvcPort;

	private String httpsvcContextApp;

	private String httpsvcAppid;

	private String httpsvcPlaintext;

	private String httpsvcSecret;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@JSONField(name = "access_token")
	private String httpsvcAccessToken;

	@JSONField(name = "product_time")
	private String httpsvcProducttime;

	@JSONField(name = "expires_in")
	private String httpsvcTokenExpires;

	private boolean tokenEffect = false;

	@Column(name = "weixin_accountid")
	public String getWeixinAccountid() {
		return weixinAccountid;
	}

	public void setWeixinAccountid(String weixinAccountid) {
		this.weixinAccountid = weixinAccountid;
	}

	@Column(name = "httpsvc_buskey")
	public String getHttpsvcBusKey() {
		return httpsvcBusKey;
	}

	public void setHttpsvcBusKey(String httpsvcBusKey) {
		this.httpsvcBusKey = httpsvcBusKey;
	}

	@Column(name = "httpsvc_host")
	public String getHttpsvcHost() {
		return httpsvcHost;
	}

	public void setHttpsvcHost(String httpsvcHost) {
		this.httpsvcHost = httpsvcHost;
	}

	@Column(name = "httpsvc_port")
	public int getHttpsvcPort() {
		return httpsvcPort;
	}

	public void setHttpsvcPort(int httpsvcPort) {
		this.httpsvcPort = httpsvcPort;
	}

	@Column(name = "httpsvc_contextapp")
	public String getHttpsvcContextApp() {
		return httpsvcContextApp;
	}

	public void setHttpsvcContextApp(String httpsvcContextApp) {
		this.httpsvcContextApp = httpsvcContextApp;
	}

	@Column(name = "httpsvc_appid")
	public String getHttpsvcAppid() {
		return httpsvcAppid;
	}

	public void setHttpsvcAppid(String httpsvcAppid) {
		this.httpsvcAppid = httpsvcAppid;
	}

	@Column(name = "httpsvc_plaintext")
	public String getHttpsvcPlaintext() {
		return httpsvcPlaintext;
	}

	public void setHttpsvcPlaintext(String httpsvcPlaintext) {
		this.httpsvcPlaintext = httpsvcPlaintext;
	}

	@Column(name = "httpsvc_secret")
	public String getHttpsvcSecret() {
		return httpsvcSecret;
	}

	public void setHttpsvcSecret(String httpsvcSecret) {
		this.httpsvcSecret = httpsvcSecret;
	}

	@Transient
	public String getHttpsvcAccessToken() {
		return httpsvcAccessToken;
	}

	public void setHttpsvcAccessToken(String httpsvcAccessToken) {
		this.httpsvcAccessToken = httpsvcAccessToken;
	}

	@Transient
	public String getHttpsvcProducttime() {
		return httpsvcProducttime;
	}

	public void setHttpsvcProducttime(String httpsvcProducttime) {
		this.httpsvcProducttime = httpsvcProducttime;
	}

	@Transient
	public String getHttpsvcTokenExpires() {
		return httpsvcTokenExpires;
	}

	public void setHttpsvcTokenExpires(String httpsvcTokenExpires) {
		this.httpsvcTokenExpires = httpsvcTokenExpires;
	}

	@Transient
	public boolean isTokenEffect() {
		return tokenEffect;
	}

	public void setTokenEffect(boolean tokenEffect) {
		this.tokenEffect = tokenEffect;
	}

	@Override
	public String toString() {
		return "MsDockHttp [weixinAccountid=" + weixinAccountid
				+ ", httpsvcBusKey=" + httpsvcBusKey + ", httpsvcHost="
				+ httpsvcHost + ", httpsvcPort=" + httpsvcPort
				+ ", httpsvcContextApp=" + httpsvcContextApp
				+ ", httpsvcAppid=" + httpsvcAppid + ", httpsvcPlaintext="
				+ httpsvcPlaintext + ", httpsvcSecret=" + httpsvcSecret
				+ ", httpsvcAccessToken=" + httpsvcAccessToken
				+ ", httpsvcProducttime=" + httpsvcProducttime
				+ ", httpsvcTokenExpires=" + httpsvcTokenExpires
				+ ", tokenEffect=" + tokenEffect + "]";
	}

}
