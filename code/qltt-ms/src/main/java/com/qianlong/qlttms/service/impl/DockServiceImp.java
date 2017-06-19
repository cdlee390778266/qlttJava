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
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServAccessTokenReqBody;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.domain.db.MsDockHttp;
import com.qianlong.qlttms.domain.db.MsDockZmq;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;
import com.qianlong.qlttms.zmq.ZMQCommException;
import com.qianlong.qlttms.zmq.ZMQProxyClient;

@Service("dockService")
@Transactional
public class DockServiceImp implements IDockService {

	private Logger logger = Logger.getLogger(DockServiceImp.class);

	private final static Map<String, ZMQProxyClient> zmqSvcMap = new Hashtable<String, ZMQProxyClient>();

	private static final Map<String, MsDockHttp> httpSvcMap = new Hashtable<String, MsDockHttp>();

	@Autowired
	public ICommonDao commonDao;

	@Autowired
	private IHttpClient httpClient;
	

	@Override
	public <T> T request(String weixinAccountid, String trdCode,
			Object requestBody) {
		//从映射表中取代理客户端，如果没有则从数据库中查参数生成代理客户端并执行交易，如果出现通讯异常则从映射表中去掉，下次登录重新创建代理客户端
		//完成参数的活更新,无需要重启应用
		//根据交易码前1位区分逻辑服务器
		String buskey = trdCode.substring(0, 1);
		String dockkey = buskey+"_"+weixinAccountid;
		ZMQProxyClient zmqProxyClient = zmqSvcMap.get(dockkey);
		
		try{			
			if(zmqProxyClient == null){
					
				MsDockZmq zmqDock =  commonDao.singleResult("from MsDockZmq where weixinAccountid = '"+weixinAccountid+"' AND zmqsvcBusKey = '"+buskey+"'");
				if(zmqDock == null)
					throw new UserServBusinessException("无法路由对映的指标服务器");
				
				zmqProxyClient = new ZMQProxyClient();
				zmqProxyClient.setProxyIp(zmqDock.getZmqsvcHost());
				zmqProxyClient.setProxyPort(zmqDock.getZmqsvcPort());
				zmqProxyClient.setSndTimeOut(zmqDock.getZmqsvcSndTimeout());
				zmqProxyClient.setRcvTimeOut(zmqDock.getZmqsvcRcvTimeout());
				zmqProxyClient.setValidCRC(false);
				zmqSvcMap.put(dockkey, zmqProxyClient);	
			}
			return zmqProxyClient.outBound(trdCode, requestBody);
			
		}catch(ZMQCommException e){
			zmqSvcMap.remove(dockkey);
			throw new UserServBusinessException("连接指标服务器通讯错误");
		}
	}
	
	
	@Override
	public void loadDockConfig() {

		logger.debug("对ZMQ服务器相关信息进行缓存");
		String hql = "from MsDockZmq";
		List<MsDockZmq> zmqlist = commonDao.findHql(hql);
		ZMQProxyClient zmqProxyClient = null;
		if (!CollectionUtils.isEmpty(zmqlist)) {
			for (MsDockZmq zmqDock : zmqlist) {

				zmqProxyClient = new ZMQProxyClient();
				zmqProxyClient.setProxyIp(zmqDock.getZmqsvcHost());
				zmqProxyClient.setProxyPort(zmqDock.getZmqsvcPort());
				zmqProxyClient.setSndTimeOut(zmqDock.getZmqsvcSndTimeout());
				zmqProxyClient.setRcvTimeOut(zmqDock.getZmqsvcRcvTimeout());
				zmqProxyClient.setValidCRC(false);	
				String dockkey = zmqDock.getZmqsvcBusKey().trim()+"_"+zmqDock.getWeixinAccountid().trim();		
				logger.debug("加入ZMQ服务的BUSKEY"+dockkey);
				zmqSvcMap.put(dockkey, zmqProxyClient);
			}
		}
		
		logger.debug("对HTTP服务器相关信息进行缓存");
		hql = "from MsDockHttp";
		List<MsDockHttp> httplist = commonDao.findHql(hql);
		
		if (!CollectionUtils.isEmpty(httplist)) {
			for (MsDockHttp httpDock : httplist) {
				try {
					MsDockHttp msAccessToken = obtainAccessToken(httpDock);
					msAccessToken.setTokenEffect(true);					
					logger.debug("add AccessToken:"+msAccessToken.getWeixinAccountid());
					String	dockkey = msAccessToken.getHttpsvcBusKey().trim()+"_"+msAccessToken.getWeixinAccountid();	
					logger.debug("加入HTTP服务的BUSKEY"+dockkey);
					httpSvcMap.put(dockkey, msAccessToken);
					
				} catch (HttpRequestException e) {
					logger.error("更新用户服务器的AccessToken失败," + httpDock);
					logger.error(e.getMessage(), e);
				}catch (UserServBusinessException be){
					logger.error("用户服务业务异常：" + be.getMessage());
				}
			}
		}		
		
	}
	
	
	private  MsDockHttp obtainAccessToken(MsDockHttp httpDock) throws HttpRequestException {
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("appid", httpDock.getHttpsvcAppid()));

        logger.debug("公众号ID:"+httpDock.getWeixinAccountid()+"Bus Key="+httpDock.getHttpsvcBusKey()+" Host:"+httpDock.getHttpsvcHost()+":"+httpDock.getHttpsvcPort());
        
        UserServAccessTokenReqBody reqBody = new UserServAccessTokenReqBody();
        reqBody.setAppid(httpDock.getHttpsvcAppid());
        reqBody.setPlaintext(httpDock.getHttpsvcPlaintext());
        reqBody.setSecret(httpDock.getHttpsvcSecret());
        
        HttpContent<MsDockHttp, UserServMessage> content = null;
        try {
            content = httpClient.httpPost(Constants.SCHEME_HTTP, httpDock.getHttpsvcHost(), httpDock.getHttpsvcPort(), 
            		UserServPath.USER_SERV_OBTAINACCESSTOKEN_PATH.replace(UserServPath.USER_SERV_CONTEXT, httpDock.getHttpsvcContextApp()), 
                        nvps, (JSONObject) JSONObject.toJSON(reqBody),
                        MsDockHttp.class, UserServMessage.class);

            if (content.getContent() == null || StringUtils.isEmpty(content.getContent().getHttpsvcAccessToken())) {
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

        MsDockHttp resAccessToken =  content.getContent();
        httpDock.setHttpsvcAccessToken(resAccessToken.getHttpsvcAccessToken());
        httpDock.setHttpsvcProducttime(resAccessToken.getHttpsvcProducttime());
        httpDock.setHttpsvcTokenExpires(resAccessToken.getHttpsvcTokenExpires());
        return httpDock;
    }


	private final static String TOKEN_SYNC_FLAG = "TOKEN";
	
	private final static String TOKEN_RESET_FLAG = "TOKEN_RESET";

	@Override
	public UserServAccessToken getCurrentAccessToken(String dockKey) {
		
		logger.debug("get AccessToken:["+dockKey+"]");
		
		
		String weixinAccountid = dockKey.substring(1);
		String buskey = dockKey.substring(0,1);
		if(!StringUtils.isNumeric(buskey)){
			buskey = "0";
			weixinAccountid = dockKey;
			dockKey = buskey+"_"+weixinAccountid;
		}
		
		UserServAccessToken token = null;
		MsDockHttp dockHttp = httpSvcMap.get(dockKey);
		if(dockHttp == null){
			logger.debug("Access Token is not exist!!");
			MsDockHttp httpsvcEntity =  commonDao.singleResult("from MsDockHttp where weixinAccountid = '"+weixinAccountid+"' AND httpsvcBusKey = '"+buskey+"'");			
			if(httpsvcEntity == null){
				throw new UserServBusinessException("无法取得用户服务器参数");
			}
			synchronized(TOKEN_SYNC_FLAG){				
				dockHttp = httpSvcMap.get(dockKey);
				if(dockHttp == null){
					try {
						dockHttp = obtainAccessToken(httpsvcEntity);
						dockHttp.setTokenEffect(true);
						httpSvcMap.put(dockKey, dockHttp);
						
					} catch (HttpRequestException e) {
						logger.error("更新用户服务器的AccessToken失败"+e.getMessage());
						logger.error(e.getMessage(), e);
					}catch (UserServBusinessException be){
						logger.error("用户服务业务异常：" + be.getMessage());
					}
				}
            }
		}else{	
			logger.debug("Access Token is exist:"+dockHttp);
			
			if(isReset(Long.parseLong(dockHttp.getHttpsvcProducttime()), Long.parseLong(dockHttp.getHttpsvcTokenExpires())) || dockHttp.isTokenEffect() == false){				
				synchronized(TOKEN_RESET_FLAG){
					dockHttp = httpSvcMap.get(dockKey);
					if(isReset(Long.parseLong(dockHttp.getHttpsvcProducttime()), Long.parseLong(dockHttp.getHttpsvcTokenExpires())) || dockHttp.isTokenEffect() == false){
						try {
							dockHttp = obtainAccessToken(dockHttp);
							dockHttp.setTokenEffect(true);
							httpSvcMap.remove(dockKey);
							httpSvcMap.put(dockKey, dockHttp);
							
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
		token.setAccessToken(dockHttp.getHttpsvcAccessToken());
		token.setUserServContext(dockHttp.getHttpsvcContextApp());
		token.setUserServHost(dockHttp.getHttpsvcHost());
		token.setUserServPort(String.format("%s", dockHttp.getHttpsvcPort() != 0?dockHttp.getHttpsvcPort():80));
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
	
	public  synchronized void resetAccessTokenByAccountid(String buskey, long errorCode){		
		if(errorCode == 1){
			logger.debug("Executer reset accesstoken by"+buskey);
			MsDockHttp dockHttp = httpSvcMap.get(buskey);
			dockHttp.setTokenEffect(false);
			httpSvcMap.remove(buskey);
			httpSvcMap.put(buskey, dockHttp);
		}
	}

}
