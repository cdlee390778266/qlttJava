package com.qianlong.qltt.us.domain.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AccessToken {
	//渠道id
	private String appID;
	
	//accessToken
	private String accessToken;
	
	//接口信息
	private Map<String,InterfaceCallInfo> interfaceCallInfoMap;
	
	//accessToken更新的时间戳
	private Date timeStamp;


	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public Map<String, InterfaceCallInfo> getInterfaceCallInfoMap() {
		return interfaceCallInfoMap;
	}

	public void setInterfaceCallInfoMap(Map<String, InterfaceCallInfo> interfaceCallInfoMap) {
		this.interfaceCallInfoMap = interfaceCallInfoMap;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public static void main(String[] args) throws ParseException {
		AccessToken token = new AccessToken();
		String dateStr = "2017-04-05 15:50:10";
		token.setTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr));
	}
}
