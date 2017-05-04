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

	//RuntimeException
	public static final String RE_RUNTIMEEXCEPTION = "00000001";
	
	//DAO
	public static final String DAO_CleanupFailureDataAccessException = "00010001";

	public static final String DAO_DataIntegrityViolationException = "00010002";

	public static final String DAO_DataRetrievalFailureException = "00010003";

	public static final String DAO_DataSourceLookupFailureException = "00010004";

	public static final String DAO_InvalidDataAccessApiUsageException = "00010005";

	public static final String DAO_InvalidDataAccessResourceUsageException = "00010006";

	public static final String DAO_NonTransientDataAccessResourceException = "00010007";

	public static final String DAO_PermissionDeniedDataAccessException = "00010008";

	public static final String DAO_UncategorizedDataAccessException = "00010009";

	public static final String DAO_RecoverableDataAccessException = "00010010";

	public static final String DAO_CannotReadScriptException = "00010011";

	public static final String DAO_OptimisticLockingFailureException = "00010012";

	public static final String DAO_PessimisticLockingFailureException = "00010013";

	public static final String DAO_QueryTimeoutException ="00010014";

	public static final String DAO_TransientDataAccessResourceException = "00010015";
	
	//TransactionException
	public static final String TransactionException = "00020001";

	//sys_config
	/** 协议字典文件不存在或格式错误*/
	public static final String CONFIG_PRPTOCOL_FILE_NOT_CORRECT ="00030001";
	
	//AccessToken
	/**请求未带ACESS_TOKEN参数*/
	public static final String TOKEN_NOT_EXIST = "10000001";
	
	/**请求ACESS_TOKEN参数错误*/
	public static final String TOKEN_NOT_CORRECT = "10000002";
	
	/**ACESS_TOKEN参数已经过期*/
	public static final String TOKEN_EXPIRE = "10000003";
	
	/**客户签名错误*/
	public static final String SIGN_IS_NOT_CORRECT = "10000004";
	
	/**生成access_token失败*/
	public static final String GENERATE_ACCESS_TOKEN_FAILED = "10000005";
	
	/**不能分辨当前的请求者*/
	public static final String CAN_NOT_TELL_REQUETER = "10000006";
	
	//Interface
	/**该接口的调用已经超出最大限制*/
	public static final String INTERFACE_CALL_OUT_OF_LIMIT = "10010001";
	
	//reqparamter
	/**请求参数的格式不正确*/
	public static final String REQ_PARAMETER_FORMAT_NOT_CORRCT = "10020001";
	
	//app
	/**app不存在*/
	public static final String APP_IS_NOT_EXIST = "10030001";

	/**appid状态异常*/
	public static final String APP_STATUS_IS_NOT_NORMAL = "10030002";

	//business
	/**该手机号已存在*/
	public static final String CN_IS_EXIST = "20000001";

	/**该账号不存在或状态异常*/
	public static final String ACCT_NOT_CORRECT = "20000002";	
}
