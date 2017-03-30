package weixin.guanjia.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
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
			fillUpdateUserInfo(weixinTag);
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
		
		
		List<WeixinTag> weixinTags = null;
		//数据库存在，微信服务器不存在，删除
		if(dbTags!=null){
			weixinTags = new ArrayList<WeixinTag>();
			for (WeixinTag dbTag: dbTags) {
				boolean isExist = false;//本地不存在
				if(wxTags!=null){//微信上存在，本地数据库不存在
					for (WxTag wxtag:wxTags) {
						if(dbTag.getId().equals(wxtag.getId())){//如果存在
							isExist = true;
							continue;
						}
					}
				}
				if(!isExist){
					weixinTags.add(dbTag);
				}
			}
			if(!weixinTags.isEmpty()){
				deleteAllEntitie(weixinTags);//批量删除
			}
		}
		
		//微信服务器上存在，本地数据库不存在
		if(wxTags!=null){
			weixinTags = new ArrayList<WeixinTag>();
			for (WxTag wxtag:wxTags) {
				boolean isExist = false;//本地不存在
				if(dbTags!=null){
					for (WeixinTag dbTag: dbTags) {
						if(dbTag.getId().equals(wxtag.getId())){//如果存在
							if(!dbTag.getName().equals(wxtag.getName())){//检验两个的名字是否一样
								dbTag.setName(wxtag.getName());//同步名字
								fillUpdateUserInfo(dbTag);
								saveOrUpdate(dbTag);
							}
							isExist = true;
							continue;	
						}
					}
				}
				
				if(!isExist){//插入数据库
					WeixinTag newTag = new WeixinTag();
					newTag.setAccountid(weixinTag.getAccountid());
					newTag.setId(wxtag.getId());//由微信返回的id
					newTag.setName(wxtag.getName());
					fillCreateUserInfo(newTag);
					weixinTags.add(newTag);
				}
			}
			if(!weixinTags.isEmpty()){
				batchSave(weixinTags);//批量插入
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
		fillUpdateUserInfo(tag);
	}
	
	private void fillUpdateUserInfo(WeixinTag tag){
		TSUser user = ResourceUtil.getSessionUserName();
		Date createDate = new Date();
		tag.setUpdate_name(user.getUserName());
		tag.setUpdate_by(user.getId());
		tag.setUpdate_date(createDate);
	}
}