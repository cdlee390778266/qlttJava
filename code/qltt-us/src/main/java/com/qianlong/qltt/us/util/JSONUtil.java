package com.qianlong.qltt.us.util;

import net.sf.json.JSONObject;

public class JSONUtil {
	/**将一个对象转化成一个Json字符串*/
	public static String objToJson(Object object){
		return JSONObject.fromObject(object).toString();
	}
}
