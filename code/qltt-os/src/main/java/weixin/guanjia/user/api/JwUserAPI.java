package weixin.guanjia.user.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeewx.api.core.common.WxstoreUtils;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.WeiXinReqService;
import org.jeewx.api.core.req.model.user.UserBaseInfoGet;
import org.jeewx.api.core.req.model.user.UserInfoListGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.guanjia.user.entity.WeixinUser;
import weixin.guanjia.user.json.ErrorJson;
import weixin.guanjia.user.json.JSONUtil;

public class JwUserAPI {
	
	private static Logger logger = LoggerFactory.getLogger(JwUserAPI.class);
	
	private static String modifyRemark= "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	
	/**获取黑名单*/
	private static String getBlackList = "https://api.weixin.qq.com/cgi-bin/tags/members/getblacklist?access_token=ACCESS_TOKEN";
	
	/**拉黑用户*/
	private static String batchBlackList = "https://api.weixin.qq.com/cgi-bin/tags/members/batchblacklist?access_token=ACCESS_TOKEN";
	
	/**取消拉黑用户*/
	private static String batchUnBlackList = "https://api.weixin.qq.com/cgi-bin/tags/members/batchunblacklist?access_token=ACCESS_TOKEN";
	
	/**
	 * 更改用户备注名
	 */
	public static ErrorJson modifyRemark(String accessToken,String openid,String remark) throws WexinReqException{
		ErrorJson result = null;
		if (accessToken != null) {
			String requestUrl = modifyRemark.replace("ACCESS_TOKEN", accessToken);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("openid", openid);
			map.put("remark", remark);
			JSONObject obj = JSONObject.fromObject(map);
			logger.info("更改用户备注名json参数---obj: "+obj.toString());
			obj = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			logger.info("创建标签方法执行后json参数 : "+ obj.toString());
			result = JSONUtil.toBean(obj, ErrorJson.class);
		}
		return result;
	}

	/**
	 * 根据user_openid 获取关注用户的基本信息
	 */
	public static WeixinUser  getWeixinuser(String accesstoken,String user_openid) throws WexinReqException {
		if (accesstoken != null) {
			UserBaseInfoGet userBaseInfoGet = new UserBaseInfoGet();
			userBaseInfoGet.setAccess_token(accesstoken);
			userBaseInfoGet.setOpenid(user_openid);
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userBaseInfoGet);
			// 正常返回
			WeixinUser weixinUser = null;
			Object error = result.get("errcode");
			weixinUser = (WeixinUser)JSONObject.toBean(result, WeixinUser.class);
			return weixinUser;
		}
		return null;
	}

	/**
	 * 获取所有关注用户信息信息
	 */
	public static List<WeixinUser> getAllWxuser(String accesstoken,String next_openid) throws WexinReqException {
		
		if (accesstoken != null) {
			UserInfoListGet userInfoListGet = new UserInfoListGet();
			userInfoListGet.setAccess_token(accesstoken);
			userInfoListGet.setNext_openid(next_openid);
			JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(userInfoListGet);
			Object error = result.get("errcode");
			List<WeixinUser> lstUser = null;
			WeixinUser mxuser = null;
			int total = result.getInt("total");
			int count = result.getInt("count");
			String strNextOpenId = result.getString("next_openid");
			JSONObject data = result.getJSONObject("data");
			lstUser = new ArrayList<WeixinUser>(total);
			if (count > 0) {
				JSONArray lstOpenid = data.getJSONArray("openid");
				int iSize = lstOpenid.size();
				for (int i = 0; i < iSize; i++) {
					String openId = lstOpenid.getString(i);
					mxuser = getWeixinuser(accesstoken, openId);
					lstUser.add(mxuser);
				}
				if (strNextOpenId != null) {
					lstUser.addAll(getAllWxuser(accesstoken, strNextOpenId));
				}
			}
			return lstUser;
		}
		return null;
	}
	
	/**
	 * 获取黑名单列表
	 */
	public static List<String> getAllBlackList(String accesstoken,String next_openid) throws WexinReqException{
		if (accesstoken != null) {
			String requestUrl = getBlackList.replace("ACCESS_TOKEN", accesstoken);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("begin_openid", next_openid);
			JSONObject obj = JSONObject.fromObject(map);
			logger.info("获取黑名单 json参数---obj: "+obj.toString());
			JSONObject result = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			logger.info("获取黑名单方法执行后  json参数 : "+ result.toString());
			List<String> openids = null;
			int total = result.getInt("total");
			if(total==0){
				return null;
			}
			int count = result.getInt("count");
			String nextOpenid = result.getString("next_openid");
			JSONObject data = result.getJSONObject("data");
			openids = new ArrayList<String>(total);
			if (count > 0) {
				JSONArray partOpenids = data.getJSONArray("openid");
				int iSize = partOpenids.size();
				for (int i = 0; i < iSize; i++) {
					openids.add(partOpenids.getString(i));
				}
				if (nextOpenid != null) {
					openids.addAll(getAllBlackList(accesstoken, nextOpenid));
				}
			}
			return openids;
		}
		return null;
	}
	
	/**
	 * 拉黑用户
	 */
	public static ErrorJson batchBlackList(String accesstoken,List<String> openids,Integer black) throws WexinReqException{
		if(accesstoken != null) {
			String url = batchUnBlackList;
			if(black ==1){
				url = batchBlackList;
			}
			String requestUrl = url.replace("ACCESS_TOKEN", accesstoken);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("opened_list", openids);
			JSONObject obj = JSONObject.fromObject(map);
			logger.info("拉入黑名单 json参数---obj: "+obj.toString());
			JSONObject result = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			logger.info("拉入黑名单 方法执行后json参数 : "+ result.toString());
			return JSONUtil.toBean(result,ErrorJson.class);
		}
		return null;
	}
	
	public static void main(String[] args) throws WexinReqException {
		String accesstoken="aqb4a_2t654KyOzCDqGciEJ51a3pKE6v1AyxEkOWPqAa2eTWAs1c4cc84jSC6rxdTVUzqF-sSdJdmoSb5uQ3ZMebGCFa7R0M-tOJE4DRq9qJXLAA371RtPvR7jq_T1ZxGFVeAFAWVL";
		String openid = "oYk47wmXAaz3pEYaurlOHty66lKk";
	    WeixinUser user = JwUserAPI.getWeixinuser(accesstoken, openid);
		System.out.println(user);
	}
}
