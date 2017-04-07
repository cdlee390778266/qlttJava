package com.qianlong.qltt.us.util;

public class StringUtil {
	/**
	 * 验证该字符串是否为空或者为""
	 */
	public static boolean isNullOrBlank(String str){
		return (str == null || str.trim().length()==0);
	}
	
	/**
	 * 验证一个字符串数组的成员中是否被包含指定的字符串
	 */
	public static boolean contains(String[] strArray,String str){
		boolean contains = false;
		if(strArray != null){
			for(String row : strArray){
				if (str.indexOf(row) != -1) {
					contains = true;
					break;
				}
			}
		}
		return contains;
	}
	
	public static void main(String[] args) {
		String string = "    ";
		System.out.println(isNullOrBlank(string));
	}
}
