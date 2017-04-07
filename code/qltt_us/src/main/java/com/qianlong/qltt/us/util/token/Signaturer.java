package com.qianlong.qltt.us.util.token;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Signaturer {

	public static byte[] sign(byte[] priKeyText, String plainText) {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(priKeyText));
		KeyFactory keyf;
		try {
			keyf = KeyFactory.getInstance("RSA");
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);
			Signature signet = java.security.Signature
					.getInstance("MD5withRSA");
			signet.initSign(prikey);
			signet.update(plainText.getBytes());
			byte[] signed = Base64.encodeBase64(signet.sign());
			return signed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
