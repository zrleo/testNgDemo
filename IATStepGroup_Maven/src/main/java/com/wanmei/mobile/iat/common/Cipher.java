package com.wanmei.mobile.iat.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;




import org.apache.http.util.TextUtils;
import org.bouncycastle.util.encoders.Base64;




public class Cipher {
	
	public static String aesContacts(String content,String key) throws Exception {
		return Cipher.base64Encode(Cipher.encrypt(content, key));
	}

	public static byte[] encrypt(String content, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			javax.crypto.Cipher cipher;
			cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(content.getBytes("UTF-8"));
			return encrypted;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String decrypt(byte[] content, String key) {
		try {
			byte[] raw = key.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			javax.crypto.Cipher cipher;
			cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(content);
			String originalString = new String(original, "UTF-8");
			return originalString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String base64Encode(byte[] content) {
		String base = new String(Base64.encode(content));
		return base;
		
	}

	public static byte[] base64Decode(String content) throws Exception  {
		return Base64.decode(content);
	}

	
}
