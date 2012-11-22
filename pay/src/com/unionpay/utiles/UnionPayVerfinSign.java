/**
 * 
 */
package com.unionpay.utiles;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import com.unionpay.utiles.UnionPayMd5;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;

/**
 * @author Podevor
 *
 */
public class UnionPayVerfinSign {
	public static boolean verifySign(String certName, String base64Sign,String src) {
		boolean b = false;
		try {
			InputStream is = new FileInputStream(certName); 
			CertificateFactory cf = CertificateFactory.getInstance("x509");
			Certificate cerCert = cf.generateCertificate(is);
			PublicKey publicKey = cerCert.getPublicKey();
    
			BASE64Decoder de = new BASE64Decoder(); 
			String tmp = base64Sign.replaceAll(" ", "+");
			byte[] byteSign = de.decodeBuffer(tmp);
			byte[] oldMD5 = rsaDecrypt(publicKey,byteSign);
			byte[] newMD5 = UnionPayMd5.MD5(src);
			if(oldMD5.length == newMD5.length) {
				int i=0;
				for(i=0;i<oldMD5.length;i++) {
					if(oldMD5[i]==newMD5[i]) {
						System.out.println("123");
						continue;
					}else{
						break;
					}
				}
				if(i==oldMD5.length) {
					b=true;
				}
			}
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return b;
		}
	}
	
	public static byte[] rsaDecrypt(Key key, byte[] barr) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(barr);
	}
}
