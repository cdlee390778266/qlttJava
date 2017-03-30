package weixin.guanjia.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeewx.api.core.exception.WexinReqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.user.api.JwUserAPI;
import weixin.guanjia.user.entity.WeixinUser;
import weixin.guanjia.user.entity.WeixinUserTag;
import weixin.guanjia.user.service.IUserService;

@Service
@Transactional
public class UserServiceImpl extends CommonServiceImpl implements
		IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserTagServiceImpl.class);
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Override
	public WeixinUser save(WeixinUser weixinUser) {
		super.save(weixinUser);
		batchSaveWeixinUserTag(weixinUser);
		return weixinUser;
	}
	
	private void fillCreateUserInfo(WeixinUserTag user_tag){
		TSUser user = ResourceUtil.getSessionUserName();
		Date createDate = new Date();
		user_tag.setCreate_name(user.getUserName());
		user_tag.setCreate_by(user.getId());
		user_tag.setCreate_date(createDate);
		user_tag.setStatus(1);
		fillUpdateUserInfo(user_tag);
	}
	
	private void fillUpdateUserInfo(WeixinUserTag user_tag){
		TSUser user = ResourceUtil.getSessionUserName();
		Date createDate = new Date();
		user_tag.setUpdate_name(user.getUserName());
		user_tag.setUpdate_by(user.getId());
		user_tag.setUpdate_date(createDate);
	}

	@Override
	public WeixinUser subscribe(String accountid, String openid) {	
		WeixinAccountEntity entity = weixinAccountService.findByToUsername(accountid);
		String accessToken = weixinAccountService.getAccessToken(accountid);//acessTaoken
		WeixinUser weixinUser = new WeixinUser();
		weixinUser.setAccountid(entity.getId());
		weixinUser.setOpenid(openid);
		
		WeixinUser userFromWx = null;
		WeixinUser userFromDB = null;
		try {
			//从微信服务器获取关注用户的信息
			userFromWx = JwUserAPI.getWeixinuser(accessToken, openid);
			userFromWx.setAccountid(entity.getId());
			userFromDB = get(WeixinUser.class, weixinUser);
			if (userFromDB == null) {//当前关注的用户本地数据库不存在
				save(userFromWx);
			}else{
				saveOrUpdate(userFromWx);//更新用户信息
				List<WeixinUserTag> user_tag_list = getUserTagList(weixinUser);//获取数据库中用户_标签
				if(user_tag_list!=null && !user_tag_list.isEmpty()){
					deleteAllEntitie(user_tag_list);//删除用户所有的标签
				}
				batchSaveWeixinUserTag(userFromWx);
			}
			
		} catch (WexinReqException e) {
			throw new RuntimeException(e);
		}
		return userFromWx;
	}

	@Override
	public List<WeixinUserTag> getUserTagList(WeixinUser weixinUser) {
		CriteriaQuery query = new CriteriaQuery(WeixinUserTag.class);
		query.eq("accountid", weixinUser.getAccountid());
		query.eq("openid", weixinUser.getOpenid());
		query.add();
		return getListByCriteriaQuery(query, false);
	}
	
	public void batchSaveWeixinUserTag(WeixinUser weixinUser){
		List<Integer> taglist = weixinUser.getTagid_list();
		List<WeixinUserTag> user_tag_list = null;
		if(taglist!=null && !taglist.isEmpty()){
			user_tag_list = new ArrayList<WeixinUserTag>();
			WeixinUserTag user_tag =null;
			for (Integer integer:taglist) {
				if(integer!=null){
					user_tag = new WeixinUserTag();
					user_tag.setAccountid(weixinUser.getAccountid());
					user_tag.setId(integer);
					user_tag.setOpenid(weixinUser.getOpenid());
					user_tag.setStatus(1);
					fillCreateUserInfo(user_tag);
					user_tag_list.add(user_tag);
				}
			}
			if(!user_tag_list.isEmpty()){
				batchSave(user_tag_list);
			}
		}
	}

	@Override
	public void unSubscribe(String sys_accountid, String openid) {
		WeixinUser weixinUser = new WeixinUser();
		weixinUser.setAccountid(sys_accountid);
		weixinUser.setOpenid(openid);
		WeixinUser userFromDB = get(WeixinUser.class, weixinUser);
		if(userFromDB!=null){
			userFromDB.setSubscribe(0);//用户已取消关注
			saveOrUpdate(userFromDB);
		}	
	}
}