/**
 * 
 */
package com.unionpay.unionpayinfo;

import com.unionpay.utiles.UnionPayPrivateKey;
import com.unionpay.utiles.UnionPaySignByRSA;

/**
 * @author Podevor
 *
 */
public class UnionXmlTelegram {
	public static String signXml(String merchantId,String orderId,String orderTime){
		return "<?xml version='1.0' encoding='UTF-8' ?>"
		+"<upomp application='LanchPay.Req' version='1.0.0'>"
		+ "<merchantId>"+merchantId+"</merchantId>"
		+"<merchantOrderId>"+orderId+"</merchantOrderId>"
		+"<merchantOrderTime>"+orderTime+"</merchantOrderTime>"
		+"<sign>"+sign(merchantId,orderId,orderTime)+"</sign>"
		+"</upomp>";
	}
	private static String sign(String merchantId,String orderId,String orderTime){
		String signSource = "merchantId="+merchantId+"&merchantOrderId="+orderId+"&merchantOrderTime="+orderTime;
		return UnionPaySignByRSA.createSign(signSource,UnionPayInfo.getMerchantPrivateKeyAlias(), UnionPayInfo.getMerchantPassword(), UnionPayPrivateKey.getPrivateSign());
	}
	
	public static String getSourceSign(){
		StringBuffer sourceSign = new StringBuffer();
		sourceSign.append("merchantName=").append(UnionPayInfo.getMerchantName())
		.append("&merchantId=").append(UnionPayInfo.getMerchantId())
		.append("&merchantOrderId=").append(UnionPayInfo.getMerchantOrderId())
		.append("&merchantOrderTime=").append(UnionPayInfo.getMerchantOrderTime())
		.append("&merchantOrderAmt=").append(UnionPayInfo.getMerchantOrderAmt())
		.append("&merchantOrderDesc=").append(UnionPayInfo.getMerchantOrderDesc())
		.append("&transTimeout=").append(UnionPayInfo.getTransTimeout());
		return sourceSign.toString();
	}
	public static String getSumbitTelegram(){
		return  "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>"
				+"<upomp  application='SubmitOrder.Req' version='1.0.0'>"
				+ "<merchantName>"+ UnionPayInfo.getMerchantName()+ "</merchantName>"
				+ "<merchantId>"+ UnionPayInfo.getMerchantId()+ "</merchantId>"		
				+ "<merchantOrderId>"+ UnionPayInfo.getMerchantOrderId()+"</merchantOrderId>"		
				+ "<merchantOrderTime>"+UnionPayInfo.getMerchantOrderTime()+"</merchantOrderTime>"		
				+ "<merchantOrderAmt>"+ UnionPayInfo.getMerchantOrderAmt()+"</merchantOrderAmt>"		
				+ "<merchantOrderDesc>"+ UnionPayInfo.getMerchantOrderDesc()+"</merchantOrderDesc>"		
				+ "<transTimeout>"+UnionPayInfo.getTransTimeout()+"</transTimeout>"		
				+ "<backEndUrl>"+ UnionPayInfo.getBackEndUrl()+"</backEndUrl>"	
				+ "<sign>"+ UnionPayInfo.getXmlSign()+ "</sign>"		
				+ "<merchantPublicCert>"+ UnionPayInfo.getMerchantPublicCer()+"</merchantPublicCert>"
				+ "</upomp>";
	}
	
	public static String getNotifySourceSign( UnionPayNotifyQueryTelegramModel unionPayNotifyQueryTelegram ){
		return "transType="+unionPayNotifyQueryTelegram.getTransType()
				+"&merchantId="+unionPayNotifyQueryTelegram.getMerchantId()
				+"&merchantOrderId="+unionPayNotifyQueryTelegram.getMerchantOrderId()
				+"&merchantOrderAmt="+unionPayNotifyQueryTelegram.getMerchantOrderAmt()
				+"&settleDate="+unionPayNotifyQueryTelegram.getSettleDate()
				+"&setlAmt="+unionPayNotifyQueryTelegram.getSetlAmt()
				+"&setlCurrency="+unionPayNotifyQueryTelegram.getSetlCurrency()
				+"&converRate="+unionPayNotifyQueryTelegram.getConverRate()
				+"&cupsQid="+unionPayNotifyQueryTelegram.getCupsQid()
				+"&cupsTraceNum="+unionPayNotifyQueryTelegram.getCupsTraceNum()
				+"&cupsTraceTime="+unionPayNotifyQueryTelegram.getCupsTraceTime()
				+"&cupsRespCode="+unionPayNotifyQueryTelegram.getCupsRespCode()
				+"&cupsRespDesc="+unionPayNotifyQueryTelegram.getCupsRespDesc()
				+"&respCode="+unionPayNotifyQueryTelegram.getRespCode();
	}
	
	public static String getVerifyRspTelegram(String verifyCode) {
		return "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>"
				+ "<upomp  application='TransNotify.Rsp' version='1.0.0'>"
				+ "<transType>01</transType>" 
				+ "<merchantId>"+ UnionPayInfo.getMerchantId() + "</merchantId>"
				+ "<merchantOrderId>" + UnionPayInfo.getMerchantOrderId() + "</merchantOrderId>" 
				+ "<respCode>"+ verifyCode+"</respCode>"
				+"</upomp>";
	}
	
	public static String getQueryTelegram(String merchantId,String orderId,String orderTime,String sign){
		return  "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>"
				+"<upomp  application='QueryOrder.Req' version='1.0.0'>"
				+ "<transType>01</transType>"
				+ "<merchantId>"+ merchantId+ "</merchantId>"		
				+ "<merchantOrderId>"+orderId+"</merchantOrderId>"		
				+ "<merchantOrderTime>"+orderTime+"</merchantOrderTime>"	
				+ "<sign>"+ sign + "</sign>"		
				+ "<merchantPublicCert>"+ UnionPayInfo.getMerchantPublicCer()+"</merchantPublicCert>"
				+ "</upomp>";
	}
	
	public static String getQuerySourceSign(String merchantId,String orderId,String orderTime){
			return "transType=01&merchantId="+merchantId
					+"&merchantOrderId="+orderId
					+"&merchantOrderTime="+orderTime;
		
	}
}
