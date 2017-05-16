package com.qianlong.qltt.zbas.util;

public class StringUtil {
	/**判断是否为空串*/
	public static boolean nullOrBlank(String param) {
		return (param == null || param.length() == 0 || param.trim().equals("")) ? true
				: false;
	}
	
	public static String sqlLike(String param){
		return "%"+param+"%";
	}
}
