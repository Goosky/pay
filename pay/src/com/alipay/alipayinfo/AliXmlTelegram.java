/**
 * 
 */
package com.alipay.alipayinfo;

import java.text.DecimalFormat;
import java.util.Map;

/**
 * @author Podevor
 *
 */
public class AliXmlTelegram {
	public static String getSourceSign(Map<String,String> productInfoMap,int itemCnt,String orderId){
		int peerValue = Integer.parseInt(productInfoMap.get("product_peervalue"));
		String productName = productInfoMap.get("product_name");
		DecimalFormat dataformat = new DecimalFormat("0.00");		
		String total_fee = dataformat.format(Double.parseDouble(productInfoMap.get("product_perprice"))*(itemCnt/peerValue));
		String desc = "购买"+productName+itemCnt+"个；需支付"+total_fee+"元；"+productInfoMap.get("product_desc");
		AliPayInfo.setOrderDesc(desc);
		AliPayInfo.setOrderId(orderId);
		StringBuffer sourceSign = new StringBuffer();
		sourceSign.append("partner=").append(AliPayInfo.getPartner()).append("&")
		.append("seller=").append(AliPayInfo.getSeller()).append("&")
		.append("out_trade_no=").append(orderId).append("&")
		.append("subject=").append(productName).append("&")
		.append("body=").append(desc).append("&")
		.append("total_fee=").append(total_fee).append("&")
		.append("notify_url=").append(AliPayInfo.getNotify_url());
		return sourceSign.toString();
	}
}
