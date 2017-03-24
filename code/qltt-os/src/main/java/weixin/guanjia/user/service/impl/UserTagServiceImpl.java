package weixin.guanjia.user.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.tag.JwTagAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONException;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.user.entity.WeixinTag;
import weixin.guanjia.user.json.ErrorJson;
import weixin.guanjia.user.json.JSONUtil;
import weixin.guanjia.user.service.IUserTagService;

@Service("userTagService")
@Transactional
public class UserTagServiceImpl extends CommonServiceImpl implements
		IUserTagService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserTagServiceImpl.class);
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@Override
	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((WeixinTag) entity);
	}

	@Override
	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((WeixinTag) entity);
		return t;
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((WeixinTag) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 */
	public boolean doAddSql(WeixinTag t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 */
	public boolean doUpdateSql(WeixinTag t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 */
	public boolean doDelSql(WeixinTag t) {
		return true;
	}

	@Override
	public String addWeixinTag(WeixinTag weixinTag){
		String rspMessage = "添加标签成功！";
		//调用微信接口
		String accessToken = weixinAccountService.getAccessToken();
		try {
			//调用微信接口，创建tag
			JSONObject jsonObject = JwTagAPI.createTag(accessToken, weixinTag.getName());
			try {
				String json = jsonObject.getString("tag");//获取tag
				WeixinTag tag = JSONUtil.toBean(json, WeixinTag.class);
				
				weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
				weixinTag.setId(tag.getId());//由微信返回的id
				Date createDate = new Date();
				TSUser user = ResourceUtil.getSessionUserName();
				weixinTag.setCreate_name(user.getUserName());
				weixinTag.setCreate_by(user.getId());
				weixinTag.setCreate_date(createDate);
				
				weixinTag.setUpdate_name(user.getUserName());
				weixinTag.setUpdate_by(user.getId());
				weixinTag.setUpdate_date(createDate);
				weixinTag.setStatus(1);
				save(weixinTag);
			} catch (JSONException je){//返回是错误的json字符串
				ErrorJson errorRspJson = JSONUtil.toBean(jsonObject, ErrorJson.class);
				rspMessage = errorRspJson.getErrmsg();
			}	
		} catch (WexinReqException e) {
			e.printStackTrace();
			rspMessage = "调用微信接口失败";
		}
		return rspMessage;
	}

	@Override
	public String delWeixinTag(WeixinTag weixinTag) {
		String rspMessage = "删除标签成功！";
		
		String accessToken = weixinAccountService.getAccessToken();
		JSONObject jsonObject = JwTagAPI.delete(accessToken,String.valueOf(weixinTag.getId()));
		ErrorJson errorRspJson = JSONUtil.toBean(jsonObject, ErrorJson.class);
		
		if(0==errorRspJson.getErrcode()){
			weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
			TSUser user = ResourceUtil.getSessionUserName();
			weixinTag.setUpdate_name(user.getUserName());
			weixinTag.setUpdate_by(user.getId());
			weixinTag.setUpdate_date(new Date());
			weixinTag.setStatus(0);
			saveOrUpdate(weixinTag);
		}else{
			rspMessage = errorRspJson.getErrmsg();
		}
		return rspMessage;
	}

	@Override
	public String updateWeixinTag(WeixinTag weixinTag) {
		String rspMessage = "更新标签信息成功！";
		String accessToken = weixinAccountService.getAccessToken();
		JSONObject jsonObject = JwTagAPI.updateTag(accessToken, weixinTag.getId(), weixinTag.getName());
		ErrorJson errorRspJson = JSONUtil.toBean(jsonObject, ErrorJson.class);
		if(0==errorRspJson.getErrcode()){
			weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
			TSUser user = ResourceUtil.getSessionUserName();
			weixinTag.setUpdate_name(user.getUserName());
			weixinTag.setUpdate_by(user.getId());
			weixinTag.setUpdate_date(new Date());
			saveOrUpdate(weixinTag);
		}else{
			rspMessage = errorRspJson.getErrmsg();
		}
		return rspMessage;
	}
}