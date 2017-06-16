package com.qianlong.qlttms.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.dao.ICommonDao;
import com.qianlong.qlttms.http.IHttpClient;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.MsDockConfig;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServAccessTokenReqBody;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.ICommonDBService;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;
import com.qianlong.qlttms.zmq.ZMQCommException;
import com.qianlong.qlttms.zmq.ZMQProxyClient;

@Service("dockService")
@Transactional
public class DockServiceImp implements IDockService {

	private Logger logger = Logger.getLogger(DockServiceImp.class);

	private final static Map<String, ZMQProxyClient> indexSvcMap = new Hashtable<String, ZMQProxyClient>();

	private static final Map<String, MsDockConfig> dockConfigMap = new Hashtable<String, MsDockConfig>();

	@Autowired
	public ICommonDao commonDao;

	@Autowired
	private IHttpClient httpClient;
	

	@Override
	public <T> T request(String weixinAccountid, String trdCode,
			Object requestBody) {
		//从映射表中取代理客户端，如果没有则从数据库中查参数生成代理客户端并执行交易，如果出现通讯异常则从映射表中去掉，下次登录重新创建代理客户端
		//完成参数的活更新,无需要重启应用
		ZMQProxyClient zmqProxyClient = indexSvcMap.get(weixinAccountid);
		
		try{			
			if(zmqProxyClient == null){
				MsDockConfig dockConfig =  commonDao.findUniqueByProperty(MsDockConfig.class,  "weixinAccountid", weixinAccountid);
				if(dockConfig == null)
					throw new UserServBusinessException("无法路由对映的指标服务器");
				
				zmqProxyClient = new ZMQProxyClient();
				zmqProxyClient.setProxyIp(dockConfig.getIndexsvcIp());
				zmqProxyClient.setProxyPort(dockConfig.getIndexsvcPort());
				zmqProxyClient.setSndTimeOut(dockConfig.getIndexsvcSndTimeout());
				zmqProxyClient.setRcvTimeOut(dockConfig.getIndexsvcRcvTimeout());
				zmqProxyClient.setValidCRC(false);
				indexSvcMap.put(dockConfig.getWeixinAccountid(), zmqProxyClient);	
			}
			return zmqProxyClient.outBound(trdCode, requestBody);
			
		}catch(ZMQCommException e){
			indexSvcMap.remove(weixinAccountid);
			throw new UserServBusinessException("连接指标服务器通讯错误");
		}
	}
	
	
	@Override
	public void loadDockConfig() {

		logger.debug("对用户服务器相关信息进行缓存");
		String hql = "from  MsDockConfig";
		List<MsDockConfig> list = commonDao.findHql(hql);
		ZMQProxyClient zmqProxyClient = null;
		if (!CollectionUtils.isEmpty(list)) {
			for (MsDockConfig msDock : list) {

				zmqProxyClient = new ZMQProxyClient();
				zmqProxyClient.setProxyIp(msDock.getIndexsvcIp());
				zmqProxyClient.setProxyPort(msDock.getIndexsvcPort());
				zmqProxyClient.setSndTimeOut(msDock.getIndexsvcSndTimeout());
				zmqProxyClient.setRcvTimeOut(msDock.getIndexsvcRcvTimeout());
				zmqProxyClient.setValidCRC(false);
				indexSvcMap.put(msDock.getWeixinAccountid(), zmqProxyClient);

				try {
					MsDockConfig msAccessToken = obtainAccessToken(msDock);
					msAccessToken.setTokenEffect(true);
					
					logger.debug("add AccessToken:"+msAccessToken.getWeixinAccountid());
					dockConfigMap.put(msAccessToken.getWeixinAccountid(), msAccessToken);
					
				} catch (HttpRequestException e) {
					logger.error("更新用户服务器的AccessToken失败," + msDock);
					logger.error(e.getMessage(), e);
				}catch (UserServBusinessException be){
					logger.error("用户服务业务异常：" + be.getMessage());
				}
			}
		}
	}
	
	
	private  MsDockConfig obtainAccessToken(MsDockConfig usersvc) throws HttpRequestException {
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("appid", usersvc.getUsersvcAppid()));

        logger.debug("公众号ID:"+usersvc.getWeixinAccountid()+" Host:"+usersvc.getUsersvcIp()+":"+usersvc.getUsersvcPort());
        
        UserServAccessTokenReqBody reqBody = new UserServAccessTokenReqBody();
        reqBody.setAppid(usersvc.getUsersvcAppid());
        reqBody.setPlaintext(usersvc.getUsersvcPlaintext());
        reqBody.setSecret(usersvc.getUsersvcSecret());
        
        HttpContent<MsDockConfig, UserServMessage> content = null;
        try {
            content = httpClient.httpPost(Constants.SCHEME_HTTP, usersvc.getUsersvcIp(), usersvc.getUsersvcPort(), 
            		UserServPath.USER_SERV_OBTAINACCESSTOKEN_PATH.replace(UserServPath.USER_SERV_CONTEXT, usersvc.getUsersvcContextApp()), 
                        nvps, (JSONObject) JSONObject.toJSON(reqBody),
                        MsDockConfig.class, UserServMessage.class);

            if (content.getContent() == null || StringUtils.isEmpty(content.getContent().getUsersvcAccessToken())) {
                throw new UserServBusinessException(String.format("获取用户服务器ACCESS_TOKEN错误 - 错误信息: %s",
                        content.getMessage() == null ? "用户服务器未知错误" : content.getMessage().getErrorMsg()));
            }
        } catch (URISyntaxException | IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpRequestException("HTTP请求发生错误，请检查URI是否正确，网络是否畅通", e);
        }

        if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
            throw new UserServBusinessException(
                    String.format("获取用户服务器ACCESS_TOKEN错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
                    content.getMessage().getErrorCode());
        }

        MsDockConfig resAccessToken =  content.getContent();
        usersvc.setUsersvcAccessToken(resAccessToken.getUsersvcAccessToken());
        usersvc.setUsersvcProducttime(resAccessToken.getUsersvcProducttime());
        usersvc.setUsersvcTokenExpires(resAccessToken.getUsersvcTokenExpires());
        return usersvc;
    }


	private final static String TOKEN_SYNC_FLAG = "TOKEN";
	
	private final static String TOKEN_RESET_FLAG = "TOKEN_RESET";

	@Override
	public UserServAccessToken getCurrentAccessToken(String weixinAccountid) {
		
		logger.debug("get AccessToken:"+weixinAccountid);
		UserServAccessToken token = null;
		MsDockConfig dockConfig = dockConfigMap.get(weixinAccountid);
		if(dockConfig == null){
			logger.debug("Access Token is not exist!!");
			MsDockConfig usersvcEntity =  commonDao.findUniqueByProperty(MsDockConfig.class,  "weixinAccountid", weixinAccountid);			
			if(usersvcEntity == null){
				throw new UserServBusinessException("无法取得用户服务器参数");
			}
			synchronized(TOKEN_SYNC_FLAG){				
				dockConfig = dockConfigMap.get(weixinAccountid);
				if(dockConfig == null){
					try {
						MsDockConfig msAccessToken = obtainAccessToken(usersvcEntity);
						msAccessToken.setTokenEffect(true);
						dockConfigMap.put(msAccessToken.getWeixinAccountid(), msAccessToken);
						
					} catch (HttpRequestException e) {
						logger.error("更新用户服务器的AccessToken失败"+e.getMessage());
						logger.error(e.getMessage(), e);
					}catch (UserServBusinessException be){
						logger.error("用户服务业务异常：" + be.getMessage());
					}
				}
            }
		}else{	
			logger.debug("Access Token is exist:"+dockConfig);
			
			if(isReset(Long.parseLong(dockConfig.getUsersvcProducttime()), Long.parseLong(dockConfig.getUsersvcTokenExpires())) || dockConfig.isTokenEffect() == false){				
				synchronized(TOKEN_RESET_FLAG){
					dockConfig = dockConfigMap.get(weixinAccountid);
					if(isReset(Long.parseLong(dockConfig.getUsersvcProducttime()), Long.parseLong(dockConfig.getUsersvcTokenExpires())) || dockConfig.isTokenEffect() == false){
						try {
							MsDockConfig msAccessToken = obtainAccessToken(dockConfig);
							msAccessToken.setTokenEffect(true);
							dockConfigMap.remove(msAccessToken.getWeixinAccountid());
							dockConfigMap.put(msAccessToken.getWeixinAccountid(), msAccessToken);
							
						} catch (HttpRequestException e) {
							logger.error("更新用户服务器的AccessToken失败"+e.getMessage());
							logger.error(e.getMessage(), e);
						}catch (UserServBusinessException be){
							logger.error("用户服务业务异常：" + be.getMessage());
						}
					}
				}
			}
		}
		token = new UserServAccessToken();
		token.setAccessToken(dockConfig.getUsersvcAccessToken());
		token.setUserServContext(dockConfig.getUsersvcContextApp());
		token.setUserServHost(dockConfig.getUsersvcIp());
		token.setUserServPort(String.format("%s", dockConfig.getUsersvcPort() != 0?dockConfig.getUsersvcPort():80));
		return token;
	}
	
	private boolean isReset(long tokenProductTime, long tokenExpires){					
		long usedMin = ( System.currentTimeMillis() - tokenProductTime)/1000/60;		
		if(usedMin > (tokenExpires*60-30)){
			return true;
		}else{
			return false;
		}
	}
	
	public  synchronized void resetAccessTokenByAccountid(String weixinAccountid, long errorCode){		
		if(errorCode == 1){
			logger.debug("Executer reset accesstoken by"+weixinAccountid);
			MsDockConfig dockConfig = dockConfigMap.get(weixinAccountid);
			dockConfig.setTokenEffect(false);
			dockConfigMap.remove(weixinAccountid);
			dockConfigMap.put(weixinAccountid, dockConfig);
		}
	}

}
