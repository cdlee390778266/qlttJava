package com.qianlong.qltt.us.service.impl;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qianlong.qltt.us.common.Constants;
import com.qianlong.qltt.us.domain.token.AccessToken;
import com.qianlong.qltt.us.domain.token.InterfaceCallInfo;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSException;
import com.qianlong.qltt.us.service.IInterfaceCallInfoService;
import com.qianlong.qltt.us.util.DateUtil;


@Service("interfaceCallInfoService")
public class InterfaceCallInfoServiceImpl extends CommServiceImpl implements IInterfaceCallInfoService{
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceCallInfoServiceImpl.class);
	/**
	 * 检查接口调用是否超出限定次数
	 */
	private boolean validInterfaceCallLimit(InterfaceCallInfo interfaceCallInfo){
		Date lastCallTime = interfaceCallInfo.getLastCallTime();
		Integer maxCallNo = interfaceCallInfo.getMaxCallNo();
		Integer callNo = interfaceCallInfo.getCallNo();
		Date now = getSystemDate();//当前时间
		if(DateUtil.isSameDate(lastCallTime, now)){//若是同一天
			if(maxCallNo != null){
				if(maxCallNo < callNo){
					lastCallTime = now;
					return false;
				}else{
					callNo++;
				}
			}	
		}else{//如果不是同一天
			//TODO 将该对像持久化到数据库
			callNo = 1 ;
		}
		lastCallTime = now ;
		return true;
	}

	@SuppressWarnings("unchecked")
	public void callInterfaceValidByAccessToken(ServletContext context, String access_token, String uri){
		Map<String,String> accessToken_appid_map = (Map<String,String>)context.getAttribute(Constants.ACCESSTOKEN_APPID_MAP);
		String appid = accessToken_appid_map.get(access_token);//渠道id
		Map<String, AccessToken> tokenMap = (Map<String,AccessToken>)context.getAttribute(Constants.SYS_ACCESSTOKENS);	
		AccessToken token = tokenMap.get(appid);
		Map<String,InterfaceCallInfo> interfaceCallInfoMap = token.getInterfaceCallInfoMap();							
		handleInterfaceValid(context, interfaceCallInfoMap, uri, appid);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void callInterfaceValidByAppid(ServletContext context, String appid, String uri){
		Map<String, AccessToken> tokenMap = (Map<String,AccessToken>)context.getAttribute(Constants.SYS_ACCESSTOKENS);
		if(tokenMap == null){
			tokenMap = new HashMap<String, AccessToken>();
			context.setAttribute(Constants.SYS_ACCESSTOKENS, tokenMap);
		}
		AccessToken token = tokenMap.get(appid);
		Map<String,InterfaceCallInfo> interfaceCallInfoMap = null;
		if(token == null){//如果该渠道的Token不存在，就创建一个
			token = new AccessToken();
			tokenMap.put(appid, token);//往内存中放一个Token
			token.setAppID(appid);
			interfaceCallInfoMap = new HashMap<String, InterfaceCallInfo>();
			token.setInterfaceCallInfoMap(interfaceCallInfoMap);
		}else{
			interfaceCallInfoMap = token.getInterfaceCallInfoMap();
		}
		handleInterfaceValid(context, interfaceCallInfoMap, uri, appid);
	}
	
	@SuppressWarnings("unchecked")
	private void handleInterfaceValid(ServletContext context,Map<String,InterfaceCallInfo> interfaceCallInfoMap,String url,String appid){
		Map<String,String> url_pn_map = (Map<String,String>)context.getAttribute(Constants.SYS_URL_PN_MAP);
		String pn = url_pn_map.get(url);//获取协议号
		InterfaceCallInfo interfaceCallInfo = interfaceCallInfoMap.get(pn);//获取token中关于调用接口的信息
		if(interfaceCallInfo == null){//如果该渠道从来没有调用过该接口
			interfaceCallInfo = new InterfaceCallInfo();
			Map<String,InterfaceCallInfo> sys_pn_interfaceCallInfo_map = (Map<String,InterfaceCallInfo>)context.getAttribute(Constants.SYS_PN_INTERFACECALLINFO_MAP);
			InterfaceCallInfo sys_interfaceCallInfo = sys_pn_interfaceCallInfo_map.get(pn);
			interfaceCallInfo.setProtocolNo(pn);
			interfaceCallInfo.setMaxCallNo(sys_interfaceCallInfo.getMaxCallNo());
			interfaceCallInfo.setUrl(sys_interfaceCallInfo.getUrl());
			interfaceCallInfo.setAppID(appid);
			interfaceCallInfo.setCallNo(1);
			interfaceCallInfo.setLastCallTime(getSystemDate());//最后一次调用时间
			interfaceCallInfoMap.put(pn, interfaceCallInfo);
		}else{//如果该渠道已经调用过了该接口
			boolean validFlag = validInterfaceCallLimit(interfaceCallInfo);
			if(!validFlag){//验证不通过，报超出最大限制的异常
				throw new QlttUSException(ErrorCodeMaster.INTERFACE_CALL_OUT_OF_LIMIT);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loadAllInterfaceMap(ServletContext servletContext){
		String path = servletContext.getInitParameter("protocolMaster");
		URL url;
		try {
			url = servletContext.getResource(path);
			SAXReader reader = new SAXReader();
			Document document = reader.read(url.openStream());
			Element root = document.getRootElement();
			Element protocolMaster = root.element("protocols");//加载协议字典
			List<Element> protocols = (List<Element>) protocolMaster.elements("protocol");
			Map<String, String> url_pn_map = new HashMap<String, String>();
			Map<String, InterfaceCallInfo> protocolMap = new HashMap<String, InterfaceCallInfo>();
			
			servletContext.setAttribute(Constants.SYS_URL_PN_MAP, url_pn_map);
			servletContext.setAttribute(Constants.SYS_PN_INTERFACECALLINFO_MAP, protocolMap);
			
			if(protocols != null){
				InterfaceCallInfo info = null;
				for (Element item : protocols) {
					info = new InterfaceCallInfo();
					Element protocolNoElment = item.element("protocolNo");
					String protocolNo = protocolNoElment.getTextTrim();
					info.setProtocolNo(protocolNo);
					
					Element protocolURLElment = item.element("reqURL");
					String protocolUrl = protocolURLElment.getTextTrim();
					info.setUrl(protocolUrl);
					
					Element protocolMaxCallNo = item.element("maxCallNo");
					if(protocolMaxCallNo != null){
						info.setMaxCallNo(Integer.parseInt(protocolMaxCallNo.getTextTrim()));
					}
					url_pn_map.put(protocolUrl, protocolNo);
					protocolMap.put(protocolNo, info);
				}
			}
			logger.debug("url--protocol：");
			logger.debug(url_pn_map.toString());
			logger.debug("protocol--InterfaceInfo:");
			logger.debug(protocolMap.toString());
		} catch (Exception e){
			e.printStackTrace();
			throw new QlttUSException(ErrorCodeMaster.CONFIG_PRPTOCOL_FILE_NOT_CORRECT, this, e);
		}
	}
}
