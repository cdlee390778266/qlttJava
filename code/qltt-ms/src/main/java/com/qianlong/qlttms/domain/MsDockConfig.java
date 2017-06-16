package com.qianlong.qlttms.domain;

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
@Table(name = "t_ext_msdockconfig")
public class MsDockConfig implements Serializable {
	
	private static final long serialVersionUID = 4403129714799706408L;
	
	private int id;

	private String weixinAccountid;
	
	private String weixinAppid;
	
	private String weixinAppsecret;
	
	private String usersvcIp;
	
	private int	usersvcPort;
	
	private String usersvcContextApp;
	
	private String usersvcAppid;
	
	private String usersvcPlaintext;
	
	private String usersvcSecret;
	
	@JSONField(name = "access_token")
	private String usersvcAccessToken;
	
	@JSONField(name = "product_time")
	private String usersvcProducttime;
	
	@JSONField(name = "expires_in")
	private String usersvcTokenExpires;
	
	private String indexsvcIp;
	
	private int	indexsvcPort;
	
	private int indexsvcSndTimeout;
	
	private int indexsvcRcvTimeout;
	
	private boolean tokenEffect = false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "weixin_accountid")
	public String getWeixinAccountid() {
		return weixinAccountid;
	}



	public void setWeixinAccountid(String weixinAccountid) {
		this.weixinAccountid = weixinAccountid;
	}
	

	@Column(name = "weixin_appid")
	public String getWeixinAppid() {
		return weixinAppid;
	}

	public void setWeixinAppid(String weixinAppid) {
		this.weixinAppid = weixinAppid;
	}
	
	@Column(name = "weixin_appsecret")
	public String getWeixinAppsecret() {
		return weixinAppsecret;
	}

	public void setWeixinAppsecret(String weixinAppsecret) {
		this.weixinAppsecret = weixinAppsecret;
	}

	@Column(name = "usersvc_ip")
	public String getUsersvcIp() {
		return usersvcIp;
	}

	public void setUsersvcIp(String usersvcIp) {
		this.usersvcIp = usersvcIp;
	}

	@Column(name = "usersvc_port")
	public int getUsersvcPort() {
		return usersvcPort;
	}

	public void setUsersvcPort(int usersvcPort) {
		this.usersvcPort = usersvcPort;
	}

	@Column(name = "usersvc_contextapp")
	public String getUsersvcContextApp() {
		return usersvcContextApp;
	}

	public void setUsersvcContextApp(String usersvcContextApp) {
		this.usersvcContextApp = usersvcContextApp;
	}

	@Column(name = "usersvc_appid")
	public String getUsersvcAppid() {
		return usersvcAppid;
	}

	public void setUsersvcAppid(String usersvcAppid) {
		this.usersvcAppid = usersvcAppid;
	}

	@Column(name = "usersvc_plaintext")
	public String getUsersvcPlaintext() {
		return usersvcPlaintext;
	}

	public void setUsersvcPlaintext(String usersvcPlaintext) {
		this.usersvcPlaintext = usersvcPlaintext;
	}

	@Column(name = "usersvc_secret")
	public String getUsersvcSecret() {
		return usersvcSecret;
	}

	public void setUsersvcSecret(String usersvcSecret) {
		this.usersvcSecret = usersvcSecret;
	}

	@Column(name = "usersvc_accesstoken")
	public String getUsersvcAccessToken() {
		return usersvcAccessToken;
	}

	public void setUsersvcAccessToken(String usersvcAccessToken) {
		this.usersvcAccessToken = usersvcAccessToken;
	}
	
	
	@Column(name = "usersvc_producttime")
	public String getUsersvcProducttime() {
		return usersvcProducttime;
	}

	public void setUsersvcProducttime(String usersvcProducttime) {
		this.usersvcProducttime = usersvcProducttime;
	}

	@Column(name = "usersvc_tokenexpires")
	public String getUsersvcTokenExpires() {
		return usersvcTokenExpires;
	}

	public void setUsersvcTokenExpires(String usersvcTokenExpires) {
		this.usersvcTokenExpires = usersvcTokenExpires;
	}

	@Column(name = "indexsvc_ip")
	public String getIndexsvcIp() {
		return indexsvcIp;
	}

	public void setIndexsvcIp(String indexsvcIp) {
		this.indexsvcIp = indexsvcIp;
	}

	@Column(name = "indexsvc_port")
	public int getIndexsvcPort() {
		return indexsvcPort;
	}

	public void setIndexsvcPort(int indexsvcPort) {
		this.indexsvcPort = indexsvcPort;
	}

	@Column(name = "indexsvc_sndtimeout")
	public int getIndexsvcSndTimeout() {
		return indexsvcSndTimeout;
	}

	public void setIndexsvcSndTimeout(int indexsvcSndTimeout) {
		this.indexsvcSndTimeout = indexsvcSndTimeout;
	}

	@Column(name = "indexsvc_rcvtimeout")
	public int getIndexsvcRcvTimeout() {
		return indexsvcRcvTimeout;
	}

	public void setIndexsvcRcvTimeout(int indexsvcRcvTimeout) {
		this.indexsvcRcvTimeout = indexsvcRcvTimeout;
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
		return "MsDockConfig [id=" + id + ", weixinAccountid="
				+ weixinAccountid + ", weixinAppid=" + weixinAppid
				+ ", weixinAppsecret=" + weixinAppsecret + ", usersvcIp="
				+ usersvcIp + ", usersvcPort=" + usersvcPort
				+ ", usersvcContextApp=" + usersvcContextApp
				+ ", usersvcAppid=" + usersvcAppid + ", usersvcPlaintext="
				+ usersvcPlaintext + ", usersvcSecret=" + usersvcSecret
				+ ", usersvcAccessToken=" + usersvcAccessToken
				+ ", usersvcProducttime=" + usersvcProducttime
				+ ", usersvcTokenExpires=" + usersvcTokenExpires
				+ ", indexsvcIp=" + indexsvcIp + ", indexsvcPort="
				+ indexsvcPort + ", indexsvcSndTimeout=" + indexsvcSndTimeout
				+ ", indexsvcRcvTimeout=" + indexsvcRcvTimeout
				+ ", tokenEffect=" + tokenEffect + "]";
	}
	
	
	
}
