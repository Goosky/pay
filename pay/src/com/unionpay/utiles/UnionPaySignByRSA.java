package com.unionpay.utiles;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.pay.orderprocesschoose.OrderProcess;




public class UnionPaySignByRSA {	
	public static String createSign(String  original_string,String alias,String password,InputStream PrivateSign){
		try {
			byte [] signsMD5 = UnionPayMd5.MD5(original_string);
			
			byte [] signsRSA = UnionPayRSA.rsaEncode(signsMD5,alias,password,PrivateSign);

			return new String(UnionPayBase64.encode(signsRSA));
			
		} catch (Exception e) {			

			Logger.getLogger(OrderProcess.class.getClass()).error("银联支付签字失败"+e.getMessage());
		}
		
		return null;
	}
}
