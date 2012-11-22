package com.interactiontimes.payinfo;

import com.alipay.alipayinfo.*;

public class InitMerchantInfotoAliPay {
	public static void merchantAliInfoSetting(){
		//合作商ID
		AliPayInfo.setPartner("合作商ID");
		//账户ID
		AliPayInfo.setSeller("账户ID");
		//商户RSA私钥
		AliPayInfo.setRsa_private("889ce7a52067a87f905c91f502c69644_d1cba47d-cbb1-4e29-9d77-8d1fe1b0dccd");
		//支付宝RSA公钥
		AliPayInfo.setRsa_alipay_public("支付宝RSA公钥");
		//合作商验签url
		AliPayInfo.setNotify_url("http://localhost:8082/unionPay/aliBack.action");
		//订单号长度
		AliPayInfo.setOrderIdLength(5);// 3~5
	}
}
