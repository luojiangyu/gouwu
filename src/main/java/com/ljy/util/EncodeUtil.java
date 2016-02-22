package com.ljy.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodeUtil {
	public static final String[] hexArray = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
			"c", "d", "e", "f" };

	public static String generateMd5(String inputString) {
		String md5Str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] md5Byte = md.digest(inputString.getBytes("UTF8"));
			md5Str = exchange(md5Byte);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return md5Str;
	}

	public static String exchange(byte[] inputByte) {
		StringBuffer sb = new StringBuffer();
		for (byte temp : inputByte) {
           sb.append(solveByte(temp));
		}
		return sb.toString();
	}
	public static String solveByte(byte inputByte){
		int temp=inputByte;
		if(temp<0){
			temp+=256;
		}
		return hexArray[temp>>4]+hexArray[temp&0x10-1];
	}
}
