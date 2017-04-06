package weixin.guanjia.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.tag.JwTagAPI;
import org.jeewx.api.wxuser.tag.model.WxTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.user.entity.WeixinTag;
import weixin.guanjia.user.entity.WeixinUserTag;
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
				fillCreateUserInfo(weixinTag);
				save(weixinTag);
			} catch (Exception e){//返回是错误的json字符串
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
	public ErrorJson delWeixinTag(WeixinTag weixinTag) {
		String accessToken = weixinAccountService.getAccessToken();
		JSONObject jsonObject = JwTagAPI.delete(accessToken,String.valueOf(weixinTag.getId()));
		ErrorJson errorRspJson = JSONUtil.toBean(jsonObject, ErrorJson.class);
		if(0==errorRspJson.getErrcode()){
			weixinTag.setAccountid(ResourceUtil.getWeiXinAccountId());//设置微信账号
			delete(weixinTag);//物理删除
		}
		return errorRspJson;
	}

	@Override
	public String updateWeixinTag(WeixinTag weixinTag) {
		String rspMessage = "更新标签信息成功！";
		String accessToken = weixinAccountService.getAccessToken();
		String newName = weixinTag.getName();
		JSONObject jsonObject = JwTagAPI.updateTag(accessToken, weixinTag.getId(),newName);
		ErrorJson errorRspJson = JSONUtil.toBean(jsonObject, ErrorJson.class);
		if(0==errorRspJson.getErrcode()){
			this.getEntity(WeixinTag.class,weixinTag);
			weixinTag.setName(newName);
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

	@Override
	public String doSameWeixinTag(WeixinTag weixinTag) {
		String rspMessage = "标签同步成功！";
		
		//从微信上获取所有标签
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		List<WxTag> wxTags = JwTagAPI.getTags(accessToken);
		
		//从数据库中查询所有标签
		CriteriaQuery query = new CriteriaQuery(WeixinTag.class);
		query.eq("accountid", weixinTag.getAccountid());
		query.add();
		List<WeixinTag> dbTags = getListByCriteriaQuery(query, false);
		if(dbTags!=null && !dbTags.isEmpty()){//将原来的全部删除
			deleteAllEntitie(dbTags);
		}
		
		if(wxTags!=null){
			List<WeixinTag> tags = new ArrayList<WeixinTag>();
			for (WxTag wxTag: wxTags) {
				WeixinTag newTag = new WeixinTag();
				newTag.setAccountid(weixinTag.getAccountid());
				newTag.setId(wxTag.getId());//由微信返回的id
				newTag.setName(wxTag.getName());
				fillCreateUserInfo(newTag);
				tags.add(newTag);
				doTagUserSame(weixinTag.getAccountid(),wxTag.getId());//同步标签下的用户
			}
			if(!tags.isEmpty()){//往本地数据库存
				batchSave(tags);
			}
		}
		return rspMessage;
	}
	
	
	private void fillCreateUserInfo(WeixinTag tag){
		TSUser user = ResourceUtil.getSessionUserName();
		Date createDate = new Date();
		tag.setCreate_name(user.getUserName());
		tag.setCreate_by(user.getId());
		tag.setCreate_date(createDate);
		tag.setStatus(1);
		tag.setUpdate_name(user.getUserName());
		tag.setUpdate_by(user.getId());
		tag.setUpdate_date(createDate);
	}

	private String doTagUserSame(String accountid, Integer tagid) {
		String rspMessage = "操作成功";
		//从微信上获取所有的标签用户数据
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		try {
			List<String> openids = weixin.guanjia.api.JwTagAPI.getTagUsers(accessToken, tagid, null);
			updateTagUsers(accountid,tagid,openids);
		} catch (WexinReqException e) {
			rspMessage = "获取微信服务器数据失败";
		}
		return rspMessage;
	}

	private void updateTagUsers(String accountid, Integer tagid,List<String> openids){
		CriteriaQuery query = new CriteriaQuery(WeixinUserTag.class);
		query.eq("accountid", accountid);
		query.eq("id", tagid);
		query.add();
		List<WeixinUserTag> dbUserTags = getListByCriteriaQuery(query, false);//将数据库中的删除
		if(dbUserTags!=null && !dbUserTags.isEmpty()){
			deleteAllEntitie(dbUserTags);
		}
			
		List<WeixinUserTag> doAddUserTags = new ArrayList<WeixinUserTag>();//保存新增的
		WeixinUserTag doAddUserTag = null;
		if(openids != null && !openids.isEmpty()){
			for(String openid: openids){//这一个for循环处理本地数据库不存在的
				doAddUserTag = getWeixinUserTagObject(accountid,openid,tagid);	
				doAddUserTags.add(doAddUserTag);
			}
		}
		if(!doAddUserTags.isEmpty()){
			batchSave(doAddUserTags);//批量新增
		}
	}
	
	private WeixinUserTag getWeixinUserTagObject(String accountid, String openid, Integer tagid) {
		WeixinUserTag userTag = new WeixinUserTag();
		userTag.setAccountid(accountid);
		userTag.setOpenid(openid);
		userTag.setId(tagid);
		userTag.setStatus(1);
		Date create_date = new Date();
		TSUser user = ResourceUtil.getSessionUserName();
		userTag.setUpdate_name(user.getUserName());
		userTag.setUpdate_by(user.getId());
		userTag.setUpdate_date(create_date);
		userTag.setCreate_by(user.getId());
		userTag.setCreate_name(user.getUserName());
		userTag.setCreate_date(create_date);
		return userTag;
	}

	@Override
	public String doTagUsersSet(String accountid, Integer tagid, String[] openidStrs) {
		String message = "操作成功";
		// 将微信上原来的标签删除
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		List<String> openids = (openidStrs==null||openidStrs.length==0)?null:Arrays.asList(openidStrs);
		try {
			//现将微信上所有用户移除
			List<String> openidsFromWx = weixin.guanjia.api.JwTagAPI.getTagUsers(accessToken, tagid, null);
			if(openidsFromWx!=null){
				JSONObject rspJsonObject = JwTagAPI.batchuntagging(accessToken, openidsFromWx, tagid);//将原来的移除标签
				ErrorJson json = JSONUtil.toBean(rspJsonObject, ErrorJson.class);
				if(json.getErrcode()!=0){
					message = json.getErrmsg();
					return message;
				}
			}
			
			//将现在设置的用户加上
			if(openids!=null){
				JSONObject rspJsonObject = JwTagAPI.batchtagging(accessToken, openids, tagid);//将原来的移除标签
				ErrorJson json = JSONUtil.toBean(rspJsonObject, ErrorJson.class);
				if(json.getErrcode()!=0){
					message = json.getErrmsg();
					return message;
				}
			}
			updateTagUsers(accountid,tagid,openids);//更新本地数据库
		} catch (WexinReqException e) {
			message = "连接微信服务器失败";
		}
		return message;
	}
}