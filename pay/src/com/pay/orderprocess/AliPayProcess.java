/**
 * 
 */
package com.pay.orderprocess;

import java.util.Random;

import org.apache.log4j.Logger;

import com.alipay.alipayinfo.AliPayInfo;
import com.alipay.alipayinfo.AliXmlTelegram;
import com.alipay.utiles.AliPaySignByRSA;
import com.interactiontimes.payinfo.InitMerchantInfotoAliPay;
import com.interactiontimes.products.InitProductstoAliPay;

/**
 * @author Podevor
 *
 */
public class AliPayProcess {
	public AliPayProcess(){
		InitMerchantInfotoAliPay.merchantAliInfoSetting();
		InitProductstoAliPay.initProducts();
	}
	public String orderProcess(int itemId, int itemCnt,String user) {
		Logger.getLogger(this.getClass()).info("支付宝订单生成处理");
		StringBuffer result = new StringBuffer();
		Logger.getLogger(this.getClass()).info("生成支付宝订单");
		String orderId = getOrderId();
		Logger.getLogger(this.getClass()).info("生成支付宝待签串");
		String sourceSign = AliXmlTelegram.getSourceSign(InitProductstoAliPay.getProductMapInfoById(itemId),itemCnt,orderId);
		Logger.getLogger(this.getClass()).info("生成支付宝签串");
		String sign = AliPaySignByRSA.sign(sourceSign, AliPayInfo.getRsa_private());
		if( !"85858".equals(sign) ){
			result.append(sourceSign).append("&sign_type=RSA&sign=").append(sign);
		}else{
			result.append("85858");
		}
		return result.toString();
	}	
	
	private static String getOrderId() {
		return random()+System.currentTimeMillis();
	}
	private static String random() {
		int cnt = 0;
		char orderSrc[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer orderId = new StringBuffer("");
		Random random = new Random();
		while (cnt < AliPayInfo.getOrderIdLength()) {
			int index = Math.abs(random.nextInt(AliPayInfo.getOrderIdLength()));
			if (index >= 0 && index < orderSrc.length) {
				orderId.append(orderSrc[index]);
				cnt++;
			}
		}
		return orderId.toString();
	}
}
