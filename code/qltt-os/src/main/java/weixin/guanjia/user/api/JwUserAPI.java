package weixin.guanjia.user.api;

import java.util.ArrayList;
import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.req.WeiXinReqService;
import org.jeewx.api.core.req.model.user.UserBaseInfoGet;
import org.jeewx.api.core.req.model.user.UserInfoListGet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weixin.guanjia.user.entity.WeixinUser;

public class JwUserAPI {

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
	
	public static void main(String[] args) throws WexinReqException {
		String accesstoken="aqb4a_2t654KyOzCDqGciEJ51a3pKE6v1AyxEkOWPqAa2eTWAs1c4cc84jSC6rxdTVUzqF-sSdJdmoSb5uQ3ZMebGCFa7R0M-tOJE4DRq9qJXLAA371RtPvR7jq_T1ZxGFVeAFAWVL";
		String openid = "oYk47wmXAaz3pEYaurlOHty66lKk";
	    WeixinUser user = JwUserAPI.getWeixinuser(accesstoken, openid);
		System.out.println(user);
	}
}
