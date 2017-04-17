package weixin.guanjia.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.api.JwUserAPI;
import weixin.guanjia.user.entity.WeixinUser;
import weixin.guanjia.user.entity.WeixinUserTag;
import weixin.guanjia.user.json.ErrorJson;
import weixin.guanjia.user.service.IUserService;

@Service
@Transactional
public class UserServiceImpl extends CommonServiceImpl implements
		IUserService {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
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
		try {
			//从微信服务器获取关注用户的信息
			userFromWx = JwUserAPI.getWeixinuser(accessToken, openid);
			userFromWx.setAccountid(entity.getId());
			userFromWx.setIsblack(0);
			delete(userFromWx);
			save(userFromWx);
			checkWeixinUserTag(userFromWx);//更新用户标签
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

	
	@Override
	public String doSameWeixinUser(String accountid) {
		String rspMessage = "微信用户同步成功";
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		try {
			List<WeixinUser> wxUsers = JwUserAPI.getAllWxuser(accessToken, null);
			List<WeixinUser> dbUsers = getUsersByAccountid(accountid);
			List<WeixinUser> saveUsers = null;
			List<WeixinUser> updateUsers = null;
			if(wxUsers!=null){//从微信上获取的用户数据不为空
				saveUsers = new ArrayList<WeixinUser>();
				updateUsers = new ArrayList<WeixinUser>();
				WeixinUser dbUser = null;
				for(WeixinUser wxUser:wxUsers){
					wxUser.setIsblack(0);
					wxUser.setAccountid(accountid);
					if(dbUsers != null){
						int index = dbUsers.indexOf(wxUser);
						if(index==-1){
							saveUsers.add(wxUser);//如果微信用户在本地数据库中不存在
						}else{
							dbUser = dbUsers.get(index);
							if(!dbUser.getSubscribe().equals(wxUser.getSubscribe())){
								updateUsers.add(wxUser);
							}
						}
					}else{
						saveUsers.add(wxUser);
					}
				}
			}
			
			
			//本地数据库没有的添加上
			if(saveUsers !=null && !saveUsers.isEmpty()){
				for(WeixinUser user: saveUsers){
//					logger.error(user.toString());
//					logger.error(user.getNickname());
//					logger.error(String.valueOf(user.getNickname().length()));
					save(user);
					checkWeixinUserTag(user);
				}
				rspMessage	+= ",添加了"+saveUsers.size()+"位用户";
			}
			
			//本地数据库已存在，但是关注标识不一样
			if(updateUsers!=null && !updateUsers.isEmpty()){
				for(WeixinUser user:updateUsers){
					saveOrUpdate(user);
					if(user.getSubscribe()==1){
						checkWeixinUserTag(user);
					}
				}
				rspMessage	+= ",更新了"+saveUsers.size()+"位用户";
			}
			
			doSameBlack(accountid);
		} catch (WexinReqException e) {
			throw new RuntimeException(e);
		}
		return rspMessage;
	}
	
	private void checkWeixinUserTag(WeixinUser user){
		List<WeixinUserTag> user_tag_list = getUserTagList(user);//获取数据库中用户_标签
		if(user_tag_list!=null && !user_tag_list.isEmpty()){
			deleteAllEntitie(user_tag_list);//删除用户所有的标签
		}
		batchSaveWeixinUserTag(user);
	}


	@Override
	public String doModifyRemark(WeixinUser user) {
		String rsp = "更改用户备注名成功!";
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		try {
			ErrorJson rspJson = JwUserAPI.modifyRemark(accessToken, user.getOpenid(), user.getRemark());
			if(rspJson.getErrcode()==0){//微信同步成功
				String remark = user.getRemark();
				user = getEntity(WeixinUser.class, user);
				user.setRemark(remark);
				saveOrUpdate(user);
			}else{
				rsp = rspJson.getErrmsg();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return rsp;
	}

	@Override
	public String doSameBlack(String accountid) {
		String rspMessage ="黑名单同步成功";
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		try {
			List<WeixinUser> dbUsers = getUsersByAccountid(accountid);
			if(dbUsers==null || dbUsers.isEmpty()){
				rspMessage = "用户为空，请先同步用户";
				return rspMessage;
			}
			List<String> blacklist = JwUserAPI.getAllBlackList(accessToken, null);
			if(blacklist!=null && !blacklist.isEmpty()){//如果微信上黑名单数据不为空
				for(WeixinUser user :dbUsers){
					if(blacklist.contains(user.getOpenid())){//当黑名单上有该用户
						if(user.getIsblack()!=1)//如果本地数据没有拉黑用户，将他拉黑
							user.setIsblack(1);	
					}else{//黑名单上没有该用户
						if(user.getIsblack()==1)//如果本地数据已经拉黑用户，取消拉黑
							user.setIsblack(1);
					}
					save(user);
				}
			}else{
				for(WeixinUser user :dbUsers){
					if(user.getIsblack()==1){
						user.setIsblack(0);
						save(user);
					}
				}
			}
		} catch (WexinReqException e) {
			throw new RuntimeException(e);
		}
		return rspMessage;
	}
	
	public List<WeixinUser> getUsersByAccountid(String accountid){
		CriteriaQuery query = new CriteriaQuery(WeixinUser.class);
		query.eq("accountid", accountid);
		query.add();
		return getListByCriteriaQuery(query, false);
	}

	@Override
	public String blackList(String accountid, String[] openids, Integer black) {
		String message = "操作成功";
		String accessToken = weixinAccountService.getAccessToken();//acessTaoken
		try {
			ErrorJson errorJson = JwUserAPI.batchBlackList(accessToken, Arrays.asList(openids),black);
			if(errorJson.getErrcode()==0){
				WeixinUser user = null;
				for(String openid:openids){
					user = new WeixinUser();
					user.setAccountid(accountid);
					user.setOpenid(openid);
					user = getEntity(WeixinUser.class, user);
					user.setIsblack(black);
					saveOrUpdate(user);
				}
			}else{
				message = errorJson.getErrmsg();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return message;
	}
}