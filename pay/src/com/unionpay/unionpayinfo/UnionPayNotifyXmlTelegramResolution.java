/**
 * 
 */
package com.unionpay.unionpayinfo;

import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class UnionPayNotifyXmlTelegramResolution {
	private UnionPayNotifyQueryTelegramModel unionPayNotifyQueryTelegram = null;
	public UnionPayNotifyXmlTelegramResolution(String xml){
		unionPayNotifyQueryTelegram = new UnionPayNotifyQueryTelegramModel();
		//trans type
		String transType = XmlDocResolution.getValueInXml(xml, "transType");
		//merchantid
		String merchantId = XmlDocResolution.getValueInXml(xml, "merchantId");
		//merchantOrderId
		String merchantOrderId = XmlDocResolution.getValueInXml(xml, "merchantOrderId");
		//merchantAmt	
		String merchantOrderAmt = XmlDocResolution.getValueInXml(xml, "merchantOrderAmt");
		//settleDate
		String settleDate = XmlDocResolution.getValueInXml(xml, "settleDate");
		//setlAmt
		String setlAmt = XmlDocResolution.getValueInXml(xml, "setlAmt");
		//setlCurrency
		String setlCurrency = XmlDocResolution.getValueInXml(xml, "setlCurrency");
		//converRate
		String converRate = XmlDocResolution.getValueInXml(xml, "converRate");
		//cupsQid
		String cupsQid = XmlDocResolution.getValueInXml(xml, "cupsQid");
		//cupsTraceNum
		String cupsTraceNum = XmlDocResolution.getValueInXml(xml, "cupsTraceNum");
		//cupsTraceTime
		String cupsTraceTime = XmlDocResolution.getValueInXml(xml, "cupsTraceTime");
		//cupsRespCode
		String cupsRespCode = XmlDocResolution.getValueInXml(xml, "cupsRespCode");
		//cupsRespDesc
		String cupsRespDesc = XmlDocResolution.getValueInXml(xml, "cupsRespDesc");
		//respCode			
		String respCode = XmlDocResolution.getValueInXml(xml, "respCode");

		//log the union server notify
		unionPayNotifyQueryTelegram.setTransType(transType);
		unionPayNotifyQueryTelegram.setMerchantId(merchantId);
		unionPayNotifyQueryTelegram.setMerchantOrderId(merchantOrderId);
		unionPayNotifyQueryTelegram.setMerchantOrderAmt(merchantOrderAmt);
		unionPayNotifyQueryTelegram.setSettleDate(settleDate);
		unionPayNotifyQueryTelegram.setSetlAmt(setlAmt);
		unionPayNotifyQueryTelegram.setSetlCurrency(setlCurrency);
		unionPayNotifyQueryTelegram.setConverRate(converRate);
		unionPayNotifyQueryTelegram.setCupsQid(cupsQid);
		unionPayNotifyQueryTelegram.setCupsTraceNum(cupsTraceNum);
		unionPayNotifyQueryTelegram.setCupsTraceTime(cupsTraceTime);
		unionPayNotifyQueryTelegram.setCupsRespCode(cupsRespCode);
		unionPayNotifyQueryTelegram.setCupsRespDesc(cupsRespDesc);
		unionPayNotifyQueryTelegram.setRespCode(respCode);
	}
	
	public UnionPayNotifyQueryTelegramModel getUnionPayNotifyQueryTelegramModel(){
		return unionPayNotifyQueryTelegram;
	}
}
