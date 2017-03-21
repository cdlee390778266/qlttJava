package weixin.guanjia.user.json;

import net.sf.json.JSONObject;

public class JSONUtil {
	@SuppressWarnings("unchecked")
	public static <T> T toBean(String json,Class<T> clazz){
		return (T) JSONObject.toBean(JSONObject.fromObject(json), clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject object,Class<T> clazz){
		return (T) JSONObject.toBean(object, clazz);
	}
}
