package weixin.guanjia.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeewx.api.core.common.WxstoreUtils;
import org.jeewx.api.core.exception.WexinReqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JwTagAPI{
	
	private static Logger logger = LoggerFactory.getLogger(JwTagAPI.class);
	
	private static final String GET_TAG_USERS= "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取标签下的粉丝列表
	 */
	public static List<String> getTagUsers(String accesstoken,Integer tagid,String next_openid) throws WexinReqException{
		if (accesstoken != null) {
			String requestUrl = GET_TAG_USERS.replace("ACCESS_TOKEN", accesstoken);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("tagid", tagid);
			map.put("next_openid", next_openid);
			JSONObject obj = JSONObject.fromObject(map);
			
			logger.info("获取标签下的粉丝 json参数,开始 "+obj.toString());
			JSONObject result = WxstoreUtils.httpRequest(requestUrl, "POST", obj.toString());
			logger.info("获取标签下的粉丝 json参数，结束"+ result.toString());
			int count = result.getInt("count");
			if(count == 0){
				return null;
			}
			JSONObject data = result.getJSONObject("data");
			List<String> openids = new ArrayList<String>(count);
			JSONArray openidArray = data.getJSONArray("openid");
			int iSize = openidArray.size();
			for (int i = 0; i < iSize; i++) {
				openids.add(openidArray.getString(i));
			}
			return openids;
		}
		return null;
	}
}
