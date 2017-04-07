package com.qianlong.qltt.us.util.token;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class KeyGenerater {

	private byte[] priKey;
	private byte[] pubKey;

	public void generater(String seed) {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed(seed.getBytes());
			keygen.initialize(1024, secrand);
			KeyPair keys = keygen.genKeyPair();
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();
			pubKey = Base64.encodeBase64(pubkey.getEncoded());
			priKey = Base64.encodeBase64(prikey.getEncoded());

		} catch (java.lang.Exception e) {
			System.out.println("生成密钥对失败");
			e.printStackTrace();
		}
	}

	public byte[] getPriKey() {
		return priKey;
	}

	public byte[] getPubKey() {
		return pubKey;
	}
	
	public static void main(String[] args){
		byte privatekey[] = null;
		byte publickey[] = null;
		String plainText = "AhQ";
		KeyGenerater kg = new KeyGenerater();
		kg.generater("wx_000001"+new Date().getTime());
		privatekey = kg.getPriKey();
		publickey = kg.getPubKey();
		plainText +="wx_000001";
		
		byte signbytes[] = Signaturer.sign(privatekey, plainText);
		boolean verifyFlag = SignProvider.verify(publickey, plainText, signbytes);
		
		System.out.println(new String(publickey));
		System.out.println(new String(privatekey));
		System.out.println(new String(signbytes));
		System.out.println(verifyFlag);
	}
}
