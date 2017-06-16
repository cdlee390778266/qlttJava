package com.qianlong.qlttms.zmq;
import java.util.zip.CRC32;



public class ProtocolUtil {

	public static int TXSN = 100000000;
	
	public static String ZMQ_SYS_NO = "101";
	
	public static String ZMQ_NODE_NO = "1010";

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
		return Integer.parseInt(ZMQ_SYS_NO);
	}
	
	public static int getNodeNo(){
		return Integer.parseInt(ZMQ_NODE_NO);
	}
	

}
