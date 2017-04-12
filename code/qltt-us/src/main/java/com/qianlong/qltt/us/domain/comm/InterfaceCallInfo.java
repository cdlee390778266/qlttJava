package com.qianlong.qltt.us.domain.comm;

import java.util.Date;

/**
 * 接口信息类
 */
public class InterfaceCallInfo{
	//渠道id
	private String appID;
	
	//协议号
	private String protocolNo;
	
	//对应的相关URL
	private String url;
	
	//最后调用时间
	private Date  lastCallTime;
	
	//调用的限制次数
	private Integer maxCallNo;
	
	//今日已调用的次数
	private Integer callNo;

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getProtocolNo() {
		return protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getLastCallTime() {
		return lastCallTime;
	}

	public void setLastCallTime(Date lastCallTime) {
		this.lastCallTime = lastCallTime;
	}

	public Integer getMaxCallNo() {
		return maxCallNo;
	}

	public void setMaxCallNo(Integer maxCallNo) {
		this.maxCallNo = maxCallNo;
	}

	public Integer getCallNo() {
		return callNo;
	}

	public void setCallNo(Integer callNo) {
		this.callNo = callNo;
	}

	@Override
	public String toString() {
		return "InterfaceCallInfo [appID=" + appID + ", protocolNo=" + protocolNo + ", url=" + url + ", lastCallTime="
				+ lastCallTime + ", maxCallNo=" + maxCallNo + ", callNo=" + callNo + "]";
	}
}
