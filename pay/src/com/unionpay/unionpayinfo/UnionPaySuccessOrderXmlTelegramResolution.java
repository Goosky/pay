/**
 * 
 */
package com.unionpay.unionpayinfo;

import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class UnionPaySuccessOrderXmlTelegramResolution {
	private UnionPayNotifyQueryTelegramModel unionPayNotifyQueryTelegram = null;
	public UnionPaySuccessOrderXmlTelegramResolution(String xml){
		unionPayNotifyQueryTelegram = new UnionPayNotifyQueryTelegramModel();
		//merchantOrderId
		String merchantOrderId = XmlDocResolution.getValueInXml(xml, "merchantOrderId");
		//merchant order time
		String merchantOrderTime = XmlDocResolution.getValueInXml(xml, "merchantOrderTime");
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
		//log the union server notify
		unionPayNotifyQueryTelegram.setMerchantOrderId(merchantOrderId);
		unionPayNotifyQueryTelegram.setMerchantOrderTime(merchantOrderTime);
		unionPayNotifyQueryTelegram.setSettleDate(settleDate);
		unionPayNotifyQueryTelegram.setSetlAmt(setlAmt);
		unionPayNotifyQueryTelegram.setSetlCurrency(setlCurrency);
		unionPayNotifyQueryTelegram.setConverRate(converRate);
		unionPayNotifyQueryTelegram.setCupsQid(cupsQid);
		unionPayNotifyQueryTelegram.setCupsTraceNum(cupsTraceNum);
		unionPayNotifyQueryTelegram.setCupsTraceTime(cupsTraceTime);
		unionPayNotifyQueryTelegram.setCupsRespCode(cupsRespCode);
		unionPayNotifyQueryTelegram.setCupsRespDesc(cupsRespDesc);
	}
	
	public UnionPayNotifyQueryTelegramModel getUnionPayNotifyQueryTelegramModel(){
		return unionPayNotifyQueryTelegram;
	}
}
