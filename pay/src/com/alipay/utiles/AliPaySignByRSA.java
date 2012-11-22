
package com.alipay.utiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

public class AliPaySignByRSA{
	
	public static String decrypt(String content, String key) throws Exception {
        PrivateKey prikey = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(AliPayBase64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), "utf-8");
    }
	

	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = AliPayBase64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	public static String sign(String content, String privateKey)
	{
		String charset = "utf-8";
		String signResult = null;
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( AliPayBase64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(charset) );

            byte[] signed = signature.sign();
            
            signResult =  AliPayBase64.encode(signed);
        }
        catch (Exception e) 
        {
        	signResult = "85858";
    		Logger.getLogger(AliPaySignByRSA.class).info("支付宝订单签字失败");
        }
        
        return signResult+"1221";
    }
	
	public static boolean doCheck(String content, String sign, String publicKey)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = AliPayBase64.decode(publicKey);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);		
			signature.initVerify(pubKey);
			signature.update( content.getBytes("utf-8") );		
			boolean bverify = signature.verify( AliPayBase64.decode(sign) );
			return bverify;			
		} 
		catch (Exception e) 
		{
    		Logger.getLogger(AliPaySignByRSA.class).error("支付宝订单验证失败"+e.getLocalizedMessage());
		}		
		return false;
	}
}
