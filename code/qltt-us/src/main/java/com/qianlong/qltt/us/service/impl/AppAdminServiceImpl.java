package com.qianlong.qltt.us.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUSSysApp;
import com.qianlong.qltt.us.domain.TUSSysAppKey;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttRuntimeException;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.mapper.TUSSysAppMapper;
import com.qianlong.qltt.us.protocol.app.AppCreate001Rsp;
import com.qianlong.qltt.us.protocol.app.TokenReset001;
import com.qianlong.qltt.us.service.IAppAdminService;
import com.qianlong.qltt.us.util.token.KeyGenerater;
import com.qianlong.qltt.us.util.token.SignProvider;
import com.qianlong.qltt.us.util.token.Signaturer;

@Service("appAdminService")
public class AppAdminServiceImpl extends CommServiceImpl implements IAppAdminService {
	
	@Autowired
	private TUSSysAppMapper tUSSysAppMapper;
	
	@Override
	public String generateAppid() {
		// TODO 渠道生成规则
		return "app" + String.valueOf(getSystemDate().getTime());
	}

	@Override
	@Transactional
	public AppCreate001Rsp createApp() {
		try {
			Timestamp now = getSystemDate();
			TUSSysApp app = new TUSSysApp();
			String appid = generateAppid();
			app.setFiStatus(1);
			app.setFsAppid(appid);
			KeyGenerater keyGenerater = new KeyGenerater();
			keyGenerater.generater(appid + now.getTime());

			String publickey = new String(keyGenerater.getPubKey(), "utf-8");
			app.setFsAppsecret(publickey);

			String token = appid;
			app.setFsApptoken(token);
			app.setFtRegtime(now);
			app.setFtUpdtime(now);
			
			tUSSysAppMapper.insert(app);

			byte signbytes[] = Signaturer.sign(keyGenerater.getPriKey(), appid + token);
			AppCreate001Rsp rsp = new AppCreate001Rsp();
			rsp.setAppid(appid);
			rsp.setToken(token);
			rsp.setSecret(new String(signbytes, "utf-8"));
			return rsp;
		} catch (UnsupportedEncodingException e) {
			throw new QlttRuntimeException(this, e);
		}
	}

	@Override
	@Transactional
	public TokenReset001 resetAppToken(TokenReset001 req) {
		//验证私钥的正确性
		String appid = req.getAppid();
		TUSSysApp app =  validAppSecret(appid, req.getOld_token(),req.getSecret());
		try {
			Timestamp now = getSystemDate();
			KeyGenerater keyGenerater = new KeyGenerater();
			keyGenerater.generater(appid + now.getTime());

			String publickey = new String(keyGenerater.getPubKey(), "utf-8");
			app.setFsAppsecret(publickey);
			app.setFsApptoken(req.getNew_token());
			app.setFtUpdtime(now);
			tUSSysAppMapper.updateByPrimaryKey(app);
			byte signbytes[] = Signaturer.sign(keyGenerater.getPriKey(), appid + req.getNew_token());
			
			req.setSecret(new String(signbytes, "utf-8"));
			return req;
		} catch (UnsupportedEncodingException e) {
			throw new QlttRuntimeException(this, e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public TUSSysApp validAppSecret(String appid,String plaintext, String secret) {
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
		String pubKey = app.getFsAppsecret();
		try {
			boolean verifyFlag = SignProvider.verify(pubKey.getBytes("utf-8"), appid + plaintext, secret.getBytes("utf-8"));
			if(!verifyFlag){
				throw new QlttUSBusinessException(ErrorCodeMaster.SIGN_IS_NOT_CORRECT);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return app;
	}
}
