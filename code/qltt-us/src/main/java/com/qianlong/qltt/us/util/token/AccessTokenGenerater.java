package com.qianlong.qltt.us.util.token;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class AccessTokenGenerater {
	public static String generater(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		str += new Date().getTime();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md5Bytes = md5.digest(str.getBytes("utf-8"));
		return new String(Base64.encodeBase64(md5Bytes),"utf-8");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String string = generater("1111111111111111111111111111111111111111111111111111111111111111111111111");
		System.out.println(string);
		System.out.println(string.length());
	}
}
