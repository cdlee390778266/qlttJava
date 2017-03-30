package weixin.guanjia.user.service;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import weixin.guanjia.user.entity.WeixinUser;
import weixin.guanjia.user.entity.WeixinUserTag;

public interface IUserService extends CommonService{
	/**
	 * 保存用户
	 */
	WeixinUser  save(WeixinUser weixinUser);
	
	/**
	 * 获取用户的标签
	 */
	List<WeixinUserTag> getUserTagList(WeixinUser weixinUser);

	/**
	 * 当用户取消关注该公众号
	 */
	void unSubscribe(String sys_accountid, String openid);
	
	
	/**
	 * 当用户关注该公账号，调用该函数
	 */
	WeixinUser subscribe(String accountid, String openid);
}
