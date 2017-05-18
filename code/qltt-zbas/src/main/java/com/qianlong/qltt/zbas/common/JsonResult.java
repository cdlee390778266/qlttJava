package com.qianlong.qltt.zbas.common;

import java.util.HashMap;
import java.util.Map;

public class JsonResult {
	public static Map<String, Object> jsonError(String error)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "error");
		result.put("message", error);
		return result;
	}
	
	public static Map<String, Object>jsonError()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "error");
		return result;
	}

	public static Map<String, Object> jsonOk()
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		return result;
	}

	public static Map<String, Object> jsonData(Object data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> jsonMap(HashMap<String, Object> data)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "ok");
		result.putAll(data);
		return result;
	}
}
