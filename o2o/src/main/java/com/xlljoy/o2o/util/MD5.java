package com.xlljoy.o2o.util;

import java.security.MessageDigest;

public class MD5 {
	
	// encrypt password with MD5
	public static final String getMd5(String s) {
		// to the pwr of 16
		char hexDigits[] = { '5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y' };
		try {
			char str[];
			// transfer input to byte array
			byte strTemp[] = s.getBytes();
			// get MD5 instance
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			// pass into target byte array
			mdTemp.update(strTemp);
			// get encrypted array
			byte md[] = mdTemp.digest();
			int j = md.length;
			str = new char[j * 2];
			int k = 0;
			// shift array
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// convert to String and return
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5.getMd5("123456"));
	}
	
}
