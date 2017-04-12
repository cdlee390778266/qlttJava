package com.qianlong.qltt.us.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import com.qianlong.qltt.us.common.Constants;
import com.qianlong.qltt.us.domain.comm.AccessToken;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.protocol.commom.GetAccessTokenReq;
import com.qianlong.qltt.us.protocol.commom.GetAccessTokenRsp;
import com.qianlong.qltt.us.service.IAccessTokenService;
import com.qianlong.qltt.us.util.StringUtil;
import com.qianlong.qltt.us.util.token.AccessTokenGenerater;
import com.qianlong.qltt.us.util.token.SignProvider;


@Service("accessTokenService")
public class AccessTokenServiceImpl extends CommServiceImpl implements IAccessTokenService{

	@SuppressWarnings("unchecked")
	public void isAccessTokenLegal(ServletContext context, String accees_token, String uri) {
		//验证access_token是否存在
		Map<String,String> accessToken_appid_map = (Map<String,String>)context.getAttribute(Constants.ACCESSTOKEN_APPID_MAP);
		String appid = accessToken_appid_map.get(accees_token);
		if(StringUtil.isNullOrBlank(appid)){
			throw new QlttUSBusinessException(ErrorCodeMaster.TOKEN_NOT_CORRECT);
		}else{
			Map<String, AccessToken> tokenMap = (Map<String,AccessToken>)context.getAttribute(Constants.SYS_ACCESSTOKENS);
			if(tokenMap == null ||tokenMap.get(appid) == null){//当应用文上下文中找不到
				throw new QlttUSBusinessException(ErrorCodeMaster.TOKEN_NOT_CORRECT);
			}else{
				AccessToken token = tokenMap.get(appid);
				//检查Token是否过期
				long time = Long.parseLong((String)context.getAttribute(Constants.SYS_ACESSTOKEN_EXPIRE));
				if(isTokenExpire(token,time)){
					throw new QlttUSBusinessException(ErrorCodeMaster.TOKEN_EXPIRE);
				}
			}
		}
	}

	public boolean isTokenExpire(AccessToken token, Long time) {
		if(time != null){
			//TODO 该处是否考虑延迟accesstoken的失效延迟几分钟，以便客户端刷新access_token
			return (time*60+5) <= (getSystemDate().getTime()-token.getTimeStamp().getTime())/1000/60;
		}
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public GetAccessTokenRsp getAccessToken(ServletContext servletContext, GetAccessTokenReq req) {
		//验证私钥的正确性
		String appid = req.getAppid();
		String plaintText = req.getPlaintext();
		//TODO 以后可以从数据库中取
		String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClboG5rbQ3k2p0Hy1pUy6O3oidGMIR5YTx1C4j2Y2t1RI52zRt0TXzORF0qWR0gWBjRkSevpzWMBPdUdIIfKEDlKTljKofqnizgQwFvf8vm0aMGb4/i/PezA0bY4YvTzBTeMxz2HLMGbSPOPDhE1d1Qca4buryZYlv2m2lncuNNQIDAQAB";
		//String seceret = "CLlTNP394PISXMTPIcYNRvgkIPb9j/DRZ+M7sUp9gCL92t5sIDpprHHeOWoaZnL3C3qXeF1FOYybseLvxohcWqnw2QrIN8VzXFe9TBuF3sdY1wMqxtXKb/i61EJHwsjERYGTY/iI7y1Zl3VwavbNC02IXL+0vPIGosnyQ12tw0M=";
		String sign = req.getSecret();
		plaintText+=appid;
		try {
			boolean verifyFlag = SignProvider.verify(pubKey.getBytes("utf-8"), plaintText, sign.getBytes("utf-8"));
			if(!verifyFlag){
				throw new QlttUSBusinessException(ErrorCodeMaster.SIGN_IS_NOT_CORRECT);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//从上下文中获取accesstoken_appid_map;
		Map<String,String> at_appid_map = (Map<String,String>)servletContext.getAttribute(Constants.ACCESSTOKEN_APPID_MAP);
		Map<String,AccessToken> appid_atobj_map = (Map<String,AccessToken>) servletContext.getAttribute(Constants.SYS_ACCESSTOKENS);
		if(at_appid_map == null){
			at_appid_map = new Hashtable<String,String>();
			servletContext.setAttribute(Constants.ACCESSTOKEN_APPID_MAP, at_appid_map);
		}else{
			Iterator<Entry<String, String>> it = at_appid_map.entrySet().iterator();
			while (it.hasNext()) {//将原先的删除
				Entry<String, String> entry = it.next();
				if(entry.getValue().equals(appid)){
					it.remove();
				}
			}
		}
		//生成Token
		String access_token;
		try {
			access_token = AccessTokenGenerater.generater(pubKey+plaintText);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new QlttUSBusinessException(ErrorCodeMaster.GENERATE_ACCESS_TOKEN_FAILED);
		}
		at_appid_map.put(access_token, appid);
		
		//更新系统中保存的AccessToken对象
	
		AccessToken accessToken = appid_atobj_map.get(appid);
		accessToken.setTimeStamp(getSystemDate());
		accessToken.setAccessToken(access_token);
		
		GetAccessTokenRsp rsp = new GetAccessTokenRsp();
		rsp.setAccess_token(access_token);
		rsp.setExpires_in((String)servletContext.getAttribute(Constants.SYS_ACESSTOKEN_EXPIRE));
		rsp.setProduct_time(getSystemDate().getTime());
		return rsp;
	}
}
