package weixin.util;

import java.io.Reader;
import java.sql.Clob;
import java.util.zip.CRC32;

public class StringTypeUtil {

	public static Clob stringToClob(String str) {
		if (null == str)
			return null;
		else {
			try {
				java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
				return c;
			} catch (Exception e) {
				return null;
			}
		}
	}

	public static String clobToString(Clob clob) {
		if (clob == null)
			return null;
		StringBuffer sb = new StringBuffer();
		Reader clobStream = null;
		try {
			clobStream = clob.getCharacterStream();
			char[] b = new char[60000];// 每次获取60K
			int i = 0;
			while ((i = clobStream.read(b)) != -1) {
				sb.append(b, 0, i);
			}
		} catch (Exception ex) {
			sb = null;
		} finally {
			try {
				if (clobStream != null) {
					clobStream.close();
				}
			} catch (Exception e) {
			}
		}
		if (sb == null)
			return null;
		else
			return sb.toString();
	}

	/**
	 * 生成串的CRC32校验码
	 * 
	 * @author kereny
	 * @date 2015-6-10 上午10:11:20
	 * @param str
	 * @return int
	 *
	 */
	public static int getCRC32(String str) {
		CRC32 crc32 = new CRC32();
		crc32.update(str.getBytes());
		return (int) (crc32.getValue() & 0x7FFFFFFF);
	}

}
