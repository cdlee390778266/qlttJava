package com.qianlong.webapp.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.UserServConstant;

@Service("userServCoreService")
public class UserServCoreServiceImpl extends CommonServiceImpl implements IUserServCoreService, CommonService {

	private Logger logger = LoggerFactory.getLogger(UserServCoreServiceImpl.class);

	@Autowired
	IHttpService httpService;

	@Override
	public UserServAccessToken obtainAccessToken(UserServAccessTokenReqBody body) {
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("appid", body.getAppid()));

		UserServAccessToken token = null;
		try {
			token = httpService.httpPost(UserServConstant.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")),
					UserServConstant.USER_SERV_OBTAINACCESSTOKEN_PATH, nvps, (JSONObject) JSONObject.toJSON(body),
					UserServAccessToken.class);
			
			executeSql("DELETE FROM user_serv_accesstoken");
			save(token);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
		}
		return token;
	}

	@Override
	public UserServAccessToken getCurrentAccessToken() {
		List<UserServAccessToken> list = getList(UserServAccessToken.class);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

}
