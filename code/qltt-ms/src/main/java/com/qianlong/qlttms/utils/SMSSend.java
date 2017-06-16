package com.qianlong.qlttms.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSSend {
	
	private static Logger logger = LoggerFactory.getLogger(SMSSend.class);

	static String PHONENO_TYPE_FMT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			+ "<soap:Body><GetPhoneNoType xmlns=\"http://tempuri.org/\"><phoneNo>%s</phoneNo></GetPhoneNoType></soap:Body></soap:Envelope>";

	static String SENDMSG_FMT = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
			+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			+ "<soap:Body>" + "<SendMessage xmlns=\"http://tempuri.org/\">" + "<sender>%s</sender>"
			+ "<title>%s</title>" + "<message>%s</message>" + "<mobileNos>%s</mobileNos>" + "<privilege>%d</privilege>"
			+ "<sourceId>%d</sourceId>" + "<checkCode>%s</checkCode>" + "</SendMessage>" + "</soap:Body>"
			+ "</soap:Envelope>";

	static String MESSAGE_URL = "http://qlsms.ql18.com.cn/Service.asmx";

	static int SOURCEID = 102;

	static String SAIT = "Q3570Lqphantom";

	static char[] HEX = "0123456789abcdef".toCharArray();

	public static void getPhoneNoType(String mobile) throws Exception {

		URL wsUrl = new URL(MESSAGE_URL);

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		conn.setRequestProperty("SOAPAction", "http://tempuri.org/GetPhoneNoType");

		OutputStream os = conn.getOutputStream();

		String soapBody = String.format(PHONENO_TYPE_FMT, mobile);

		os.write(soapBody.getBytes());

		InputStream is = conn.getInputStream();

		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		System.out.println(s);

		is.close();
		os.close();
		conn.disconnect();
	}

	/**
	 * 向服务器发送数据
	 * 
	 * @param sender
	 * @param title
	 * @param message
	 * @param mobile
	 * @param privilege
	 * @throws Exception
	 */
	public static void sendMessage(String sender, String title, String message, String mobile, int privilege)
			throws Exception {

		URL wsUrl = new URL(MESSAGE_URL);

		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		conn.setRequestProperty("SOAPAction", "http://tempuri.org/SendMessage");

		OutputStream os = conn.getOutputStream();

		String md5src = String.format("%s%s%s%s%d%s", sender, title, message, mobile, privilege, SAIT);

		String md5 = Utf16LEMd5Base64(md5src);

		String soapBody = String.format(SENDMSG_FMT, sender, title, message, mobile, privilege, SOURCEID, md5);

		logger.debug(soapBody);

		os.write(soapBody.getBytes());

		InputStream is = conn.getInputStream();

		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		logger.debug(s);

		is.close();
		os.close();
		conn.disconnect();

	}

	public static String Utf16LEMd5Base64(String md5data) {
		String md5 = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			logger.info("MD5 Data:{}", md5data);

			byte[] md5UTF16LEbytes = md5data.getBytes("UTF-16LE");
			logger.debug("MD5 Data Byte:{}", Arrays.toString(md5UTF16LEbytes));
			
			// 计算md5函数
			md.update(md5UTF16LEbytes);
			byte[] md5bytes = md.digest();
			logger.debug("MD5 Byte:{}", Arrays.toString(md5bytes));

			//md5 = new sun.misc.BASE64Encoder().encode(md5bytes);
			md5 = Base64.encodeBase64String(md5bytes);
			logger.debug("MD5:{}", md5);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return md5;

	}
	
	
	public static void main(String[] args) {
		
		String xx = "cdqianlong乾隆推推短信验证码验证码是：228301189817780811Q3570Lqphantom";
		SMSSend.Utf16LEMd5Base64(xx);
		
	}
	

}
