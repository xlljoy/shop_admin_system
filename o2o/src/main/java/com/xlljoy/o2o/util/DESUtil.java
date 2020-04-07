package com.xlljoy.o2o.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// DES is a kind of symmetrical encryption algorithm 
public class DESUtil {
	private static Key key;
	private static String KEY_STR = "JoyKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES";
	
	// static block to generate algorithm object
	static {
		try {
			// generate DES algorithm object
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			// apply SHA1 security strategy
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			// set key seed
			secureRandom.setSeed(KEY_STR.getBytes());
			//initialize algorithm based on SHA1
			generator.init(secureRandom);
			// generate key object
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// encryption
	
	public static String getEncryptString(String str) {
		BASE64Encoder base64encoder = new BASE64Encoder();
		try {
			// encode according to UTF-8
			byte[] bytes = str.getBytes(CHARSETNAME);
			// get encoding object
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// init pass word
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//encrypt
			byte[] doFinal = cipher.doFinal(bytes);
			// return encoded string 
			return base64encoder.encode(doFinal);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// decryption
	public static String getDecryptString(String str) {
		// based on BASE64 encryption, get byte[] and transfer to String
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] bytes = base64decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptString("root"));
		System.out.println(getEncryptString("Pass21!!"));
		System.out.println(getDecryptString("7IZm6JRr9Mg="));
		System.out.println(getDecryptString("yaMuNW6A4PpTMl+bWi4LwA=="));
	}
}
