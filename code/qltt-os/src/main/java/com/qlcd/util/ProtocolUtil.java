package com.qlcd.util;

import java.util.zip.CRC32;

import jodd.util.StringUtil;

import org.jeecgframework.core.util.PropertiesUtil;

public class ProtocolUtil {

	public static int TXSN = 100000000;

	/**
	 * 生成请求流水号
	 * 
	 * @author kereny
	 * @date 
	 * @return integer
	 *
	 */
	public static synchronized int BuildTxSN() {
		TXSN++;
		if (TXSN >= 1000000000)
			TXSN = 100000000;
		return TXSN;
	}
	
	
	/**
	 * 生成CRC校验码
	 * 
	 * @author kereny
	 * @date 
	 * @return integer
	 *
	 */
	public static int getCRC32(byte[] bytes)
	{
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		return (int)(crc32.getValue()&0x7FFFFFFF);
	}
	
	
	public static boolean checkCRC32(int crc, byte[] bytes){
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		return ((int)(crc32.getValue()&0x7FFFFFFF) == crc);
	}
	
	
	public static int getSystemNo(){
		PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
		String systemno = util.readProperty("zmq.sysno");
		return StringUtil.isAllBlank(systemno)?101:Integer.parseInt(systemno);
	}
	
	public static int getNodeNo(){
		PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
		String sysnode = util.readProperty("zmq.sysnode");
		return StringUtil.isAllBlank(sysnode)?1010:Integer.parseInt(sysnode);
	}
	

}
