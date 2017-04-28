package weixin.guanjia.core.util;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

public class WeixinUtilTest {

	@SuppressWarnings("restriction")
	@Test
	public void testEncode() {
		byte bytes[] = "qianlong".getBytes();
		String b64str = WeixinUtil.encode(bytes);
		assertTrue("两次编码结果不一致", b64str.equals(new sun.misc.BASE64Encoder().encode(bytes)));
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void testDecode() {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		byte[] bytes = null;
		try {
			bytes = decoder.decodeBuffer("qianlong");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue("两次解码结果不一致", Arrays.equals(bytes, WeixinUtil.decode("qianlong")));
	}
}
