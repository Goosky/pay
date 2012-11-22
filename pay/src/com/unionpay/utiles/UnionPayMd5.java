package com.unionpay.utiles;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UnionPayMd5 {
	/**
	 * 
	 * @param md5
	 * @return
	 */
		static byte[] MD5(String src) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(src.getBytes("utf-8"));
			byte[] digest = messageDigest.digest();
			return digest;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

	}
}
