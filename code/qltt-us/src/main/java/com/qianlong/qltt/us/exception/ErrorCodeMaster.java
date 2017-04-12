package com.qianlong.qltt.us.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 错误码数据字典类
 */
public class ErrorCodeMaster {
	private static Logger logger = LoggerFactory.getLogger(ErrorCodeMaster.class);

	private static final String PATH = "errorcode.properties";
	private static Properties properties = new Properties();

	static {
		loadConfigFile();
	}
	
	private static void loadConfigFile() {
		InputStream is = ErrorCodeMaster.class.getResourceAsStream(PATH);
		try {
			properties.load(is);
		} catch (IOException ex) {
			logger.error("加载错误码字典失败");
			ex.printStackTrace();
		}
	}

	public static String getMessage(String exceptionCode) {
		return properties.getProperty(exceptionCode);
	}

	//sys_config
	/** 协议字典文件不存在或格式错误*/
	public static final String CONFIG_PRPTOCOL_FILE_NOT_CORRECT ="00000001";
	
	//AccessToken
	/**请求未带ACESS_TOKEN参数*/
	public static final String TOKEN_NOT_EXIST = "00010001";
	
	/**请求ACESS_TOKEN参数错误*/
	public static final String TOKEN_NOT_CORRECT = "00010002";
	
	/**ACESS_TOKEN参数已经过期*/
	public static final String TOKEN_EXPIRE = "00010003";
	
	/**客户签名错误*/
	public static final String SIGN_IS_NOT_CORRECT = "00010004";
	
	/**生成access_token失败*/
	public static final String GENERATE_ACCESS_TOKEN_FAILED = "00010005";
	
	/**不能分辨当前的请求者*/
	public static final String CAN_NOT_TELL_REQUETER = "00010006";
	
	//Interface
	/**该接口的调用已经超出最大限制*/
	public static final String INTERFACE_CALL_OUT_OF_LIMIT = "00020001";
	
	//reqparamter
	/**请求参数的格式不正确*/
	public static final String REQ_PARAMETER_FORMAT_NOT_CORRCT = "00030001";

	//business
	/**该手机号已存在*/
	public static final String CN_IS_EXIST = "00040001";

	/**该账号不存在或状态异常*/
	public static final String ACCT_NOT_CORRECT = "00040002";

	//runtime exception
	public static final String RUNTIME_EXCEPTION = "00050001";

	
}
