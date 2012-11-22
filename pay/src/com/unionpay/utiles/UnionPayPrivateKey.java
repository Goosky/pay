/**
 * 
 */
package com.unionpay.utiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import com.unionpay.unionpayinfo.*;

/**
 * @author Podevor
 *
 */
public class UnionPayPrivateKey {

	public static InputStream getPrivateSign(){
		InputStream privateSign = null;
		try {
			privateSign = new FileInputStream(UnionPayInfo.getPrivateKeyPath());
		} catch (FileNotFoundException e) {
			Logger.getLogger(UnionPayPrivateKey.class).error("获取私钥失败"+e.getMessage());
		}
		return privateSign;
	}
}
