package com.unionpay.utiles;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;

public class UnionPayRSA {

	/**
	 * 
	 * @param signsRSA
	 * @param isTest
	 * @return
	 * @throws IOException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 */
	static byte[] rsaEncode(byte[] signsRSA, String alias, String pwd,
			InputStream dataSign) {
		try {
			KeyStore store = KeyStore.getInstance("PKCS12");
			InputStream inStream = dataSign;
			store.load(inStream, pwd.toCharArray());
			inStream.close();
			PrivateKey pKey = (PrivateKey) store.getKey(alias,
					pwd.toCharArray());
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pKey);
			return cipher.doFinal(signsRSA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
