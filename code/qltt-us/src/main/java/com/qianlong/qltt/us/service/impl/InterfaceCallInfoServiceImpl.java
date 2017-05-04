package com.qianlong.qltt.us.service.impl;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.common.Constants;
import com.qianlong.qltt.us.domain.TUSSysApp;
import com.qianlong.qltt.us.domain.TUSSysAppKey;
import com.qianlong.qltt.us.domain.TUSSysPtcDailyCall;
import com.qianlong.qltt.us.domain.comm.AccessToken;
import com.qianlong.qltt.us.domain.comm.InterfaceCallInfo;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.mapper.TUSSysAccessTokenTmpMapper;
import com.qianlong.qltt.us.mapper.TUSSysAppMapper;
import com.qianlong.qltt.us.mapper.TUSSysPtcDailyCallMapper;
import com.qianlong.qltt.us.mapper.TUsSysPtcCallTmpMapper;
import com.qianlong.qltt.us.service.IInterfaceCallInfoService;
import com.qianlong.qltt.us.util.DateUtil;


@Service("interfaceCallInfoService")
public class InterfaceCallInfoServiceImpl extends CommServiceImpl implements IInterfaceCallInfoService{
	
	private static final Logger logger = LoggerFactory.getLogger(InterfaceCallInfoServiceImpl.class);
	
	@Autowired
	private TUSSysPtcDailyCallMapper tUSSysPltDailyCallMapper;
	
	@Autowired
	private TUSSysAccessTokenTmpMapper tUSSysAccessTokenTmpMapper;
	
	@Autowired
	private  TUsSysPtcCallTmpMapper tUsSysPtcCallTmpMapper;
	
	@Autowired
	private TUSSysAppMapper tUSSysAppMapper;
	/**
	 * 检查接口调用是否超出限定次数
	 */
	private boolean validInterfaceCallLimit(InterfaceCallInfo interfaceCallInfo){
		Date lastCallTime = interfaceCallInfo.getLastCallTime();
		Integer maxCallNo = interfaceCallInfo.getMaxCallNo();
		Integer callNo = interfaceCallInfo.getCallNo();
		Timestamp now = getSystemDate();//当前时间
		if(DateUtil.isSameDate(lastCallTime, now)){//若是同一天
			if(maxCallNo != null){
				if(maxCallNo < callNo){
					return false;
				}else{
					callNo++;	
				}
			}	
		}else{//如果不是同一天
			//将该对像持久化到数据库
			TUSSysPtcDailyCall pltDailyCall = new TUSSysPtcDailyCall();
			pltDailyCall.setFsAppid(interfaceCallInfo.getAppID());
			pltDailyCall.setFsPtlno(interfaceCallInfo.getProtocolNo());
			pltDailyCall.setFtPtllastcalltime(new Timestamp(interfaceCallInfo.getLastCallTime().getTime()));
			pltDailyCall.setFiPtlcallnum(callNo);
			tUSSysPltDailyCallMapper.insert(pltDailyCall);
			callNo = 1 ;
		}
		interfaceCallInfo.setCallNo(callNo);
		interfaceCallInfo.setLastCallTime(now);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Transactional
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
	@Transactional
	public void callInterfaceValidByAppid(ServletContext context, String appid, String uri){
		//验证该appid存不存在
		TUSSysAppKey key = new TUSSysAppKey();
		key.setFsAppid(appid);
		TUSSysApp app = tUSSysAppMapper.selectByPrimaryKey(key);
		if(app ==null){//appid不存在
			throw new QlttUSBusinessException(ErrorCodeMaster.APP_IS_NOT_EXIST);
		}else{
			if(app.getFiStatus() != 1 ){//appid状态不对
				throw new QlttUSBusinessException(ErrorCodeMaster.APP_STATUS_IS_NOT_NORMAL);
			}
		}
		
		Map<String, AccessToken> tokenMap = (Map<String,AccessToken>)context.getAttribute(Constants.SYS_ACCESSTOKENS);
		
		AccessToken token = tokenMap.get(appid);
		Map<String,InterfaceCallInfo> interfaceCallInfoMap = null;
		if(token == null){//如果该渠道的Token不存在，就创建一个
			token = new AccessToken();
			tokenMap.put(appid, token);//往内存中放一个Token
			token.setAppID(appid);
			interfaceCallInfoMap = new Hashtable<String, InterfaceCallInfo>();
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
				throw new QlttUSBusinessException(ErrorCodeMaster.INTERFACE_CALL_OUT_OF_LIMIT);
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
			Map<String, String> url_pn_map = new Hashtable<String, String>();
			Map<String, InterfaceCallInfo> protocolMap = new Hashtable<String, InterfaceCallInfo>();
			
			servletContext.setAttribute(Constants.SYS_URL_PN_MAP, url_pn_map);
			servletContext.setAttribute(Constants.SYS_PN_INTERFACECALLINFO_MAP, protocolMap);
			
			if(protocols != null){
				InterfaceCallInfo info = null;
				for (Element item : protocols) {
					info = new InterfaceCallInfo();
					Element protocolNoElment = item.element("protocol-num");
					String protocolNo = protocolNoElment.getTextTrim();
					info.setProtocolNo(protocolNo);
					
					Element protocolURLElment = item.element("req-url");
					String protocolUrl = protocolURLElment.getTextTrim();
					info.setUrl(protocolUrl);
					
					Element protocolMaxCallNo = item.element("req-call-max");
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
		}
	}

	@Override
	@Transactional
	public void loadAcessTokenData(ServletContext sc) {
		//加载数据
		List<AccessToken> accessTokens = tUSSysAccessTokenTmpMapper.selectAllAccessTokens();
		Map<String,String> at_appid_map = new Hashtable<String,String>();
		Map<String, AccessToken> appid_atobj_map = new Hashtable<String,AccessToken>();
		sc.setAttribute(Constants.ACCESSTOKEN_APPID_MAP, at_appid_map);
		sc.setAttribute(Constants.SYS_ACCESSTOKENS, appid_atobj_map);
		if(accessTokens != null && ! accessTokens.isEmpty()){
			List<InterfaceCallInfo> infos;
			Map<String,InterfaceCallInfo> infoMap;
			for(AccessToken at:accessTokens){
				String atString = at.getAccessToken();
				String appid = at.getAppID();
				at_appid_map.put(atString, appid);
				appid_atobj_map.put(appid, at);
				
				//加载接口信息
				infos = tUsSysPtcCallTmpMapper.selectByAppid(appid);
				if(infos!=null && !infos.isEmpty()){
					infoMap = new Hashtable<String,InterfaceCallInfo>();
					for(InterfaceCallInfo info:infos){
						infoMap.put(info.getProtocolNo(), info);
					}
					at.setInterfaceCallInfoMap(infoMap);
				}
			}
		}
		//清空临时表
		tUSSysAccessTokenTmpMapper.deleteAll();
		tUsSysPtcCallTmpMapper.deleteAll();
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public void saveAcessTokenData(ServletContext servletContext) {
		Map<String, AccessToken> appid_atobj_map = (Map<String, AccessToken>)servletContext.getAttribute(Constants.SYS_ACCESSTOKENS);
		if(appid_atobj_map != null && !appid_atobj_map.isEmpty()){
			List<AccessToken> accessTokens = new ArrayList<AccessToken>();
			List<InterfaceCallInfo> infos = new ArrayList<InterfaceCallInfo>();
			Collection<AccessToken> atc = appid_atobj_map.values();
			Iterator<AccessToken> atit= atc.iterator();
			AccessToken at;
			Map<String, InterfaceCallInfo> infomap;
			while (atit.hasNext()) {
				at = atit.next();
				accessTokens.add(at);
				infomap = at.getInterfaceCallInfoMap();
				if(infomap !=null && !infomap.isEmpty()){
					infos.addAll(infomap.values());
				}
			}
			if(!accessTokens.isEmpty())
				tUSSysAccessTokenTmpMapper.batchInsert(accessTokens);
			if(!infos.isEmpty())
				tUsSysPtcCallTmpMapper.batchInsert(infos);
		}
	}
}
