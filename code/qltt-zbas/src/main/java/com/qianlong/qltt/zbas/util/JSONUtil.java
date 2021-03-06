package com.qianlong.qltt.zbas.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	/**将一个对象转化成一个Json字符串*/
	public static String objToJson(Object object){
		return JSONObject.fromObject(object).toString();
	}
	
	public static String arrayToJson(List<?> list){
		return JSONArray.fromObject(list).toString();
	}
}
