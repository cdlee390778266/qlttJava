package com.qianlong.qlttms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "user_serv_accesstoken")
public class UserServAccessToken {
   
    private String id;
    
    private String userServHost;
    
    private String userServPort;
    
    private String userServContext;
    
    private String userServReqAppid;
    
    private String userServReqPlaintext;
    
    private String userServReqSecret;
    
	@JSONField(name = "access_token")
	private String accessToken;
	
	@JSONField(name = "product_time")
	private Long productTime;
	
	@JSONField(name = "expires_in")
	private Integer expiresIn;
	
    @Id
    @Column(name ="id",nullable=false,length=36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
	
    @Column(name ="user_serv_host")
	public String getUserServHost() {
        return userServHost;
    }

    public void setUserServHost(String userServHost) {
        this.userServHost = userServHost;
    }

    @Column(name ="user_serv_port")
    public String getUserServPort() {
        return userServPort;
    }

    public void setUserServPort(String userServPort) {
        this.userServPort = userServPort;
    }

    @Column(name ="user_serv_context")
    public String getUserServContext() {
        return userServContext;
    }

    public void setUserServContext(String userServContext) {
        this.userServContext = userServContext;
    }

    @Column(name ="user_serv_req_appid")
    public String getUserServReqAppid() {
        return userServReqAppid;
    }

    public void setUserServReqAppid(String userServReqAppid) {
        this.userServReqAppid = userServReqAppid;
    }

    @Column(name ="user_serv_req_plaintext")
    public String getUserServReqPlaintext() {
        return userServReqPlaintext;
    }

    public void setUserServReqPlaintext(String userServReqPlaintext) {
        this.userServReqPlaintext = userServReqPlaintext;
    }

    @Column(name ="user_serv_req_secret")
    public String getUserServReqSecret() {
        return userServReqSecret;
    }

    public void setUserServReqSecret(String userServReqSecret) {
        this.userServReqSecret = userServReqSecret;
    }

	@Column(name = "access_token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "product_time")
	public Long getProductTime() {
		return productTime;
	}

	public void setProductTime(Long productTime) {
		this.productTime = productTime;
	}

	@Column(name = "expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

    @Override
    public String toString() {
        return "UserServAccessToken [id=" + id + ", userServHost=" + userServHost + ", userServPort=" + userServPort
                + ", userServContext=" + userServContext + ", userServReqAppid=" + userServReqAppid
                + ", userServReqPlaintext=" + userServReqPlaintext + ", userServReqSecret=" + userServReqSecret
                + ", accessToken=" + accessToken + ", productTime=" + productTime + ", expiresIn=" + expiresIn + "]";
    }
}
