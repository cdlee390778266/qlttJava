package com.qianlong.qlttms.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {
	
	
	public static Object jsonError(String error)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 1);
		result.put("message", error);
		return result;
	}
	

	public static Object jsonOk()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		return result;
	}

	public static Object jsonData(Object data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.put("data", data);
		return result;
	}
	
	public static Object jsonMap(HashMap<String, Object> data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 0);
		result.putAll(data);
		return result;
	}

}
