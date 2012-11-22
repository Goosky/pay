/**
 * 
 */
package com.unionpay.orderprocess;

import org.apache.log4j.Logger;

import com.interactiontimes.database.OrderStatus;
import com.pay.orderprocesschoose.OrderProcess;
import com.pay.utiles.OrderInfoHttpTransfer;
import com.pay.utiles.XmlDocResolution;
import com.unionpay.unionpayinfo.UnionPayInfo;
import com.unionpay.unionpayinfo.UnionPayNotifyQueryTelegramModel;
import com.unionpay.unionpayinfo.UnionPayNotifyXmlTelegramResolution;
import com.unionpay.unionpayinfo.UnionXmlTelegram;
import com.unionpay.utiles.UnionPayVerfinSign;

/**
 * @author Podevor
 *
 */
public class UnionPayOrderCheckProcess {
	public static String OrderCheckProcess(String xml){
		Logger.getLogger(OrderProcess.class.getClass()).info("银联前置通知报文处理——解析报文"+xml);
		String checkOrderResult = OrderStatus.FAILURE;
		if(!"".equals(xml) && xml != null){
			UnionPayNotifyXmlTelegramResolution unionPayXmlTelegram = new UnionPayNotifyXmlTelegramResolution(xml);
			UnionPayNotifyQueryTelegramModel unionPayNotifyQueryTelegram = unionPayXmlTelegram.getUnionPayNotifyQueryTelegramModel();
			String respCodetoUnionServer = "1111";
			if (/*0000".equals(unionPayNotifyQueryTelegram.getRespCode()) && */"00".equals(unionPayNotifyQueryTelegram.getCupsRespCode())) {
				//sign
				String src = UnionXmlTelegram.getNotifySourceSign(unionPayNotifyQueryTelegram);
				String sign = XmlDocResolution.getValueInXml(xml, "sign");
				//verify data
				boolean verfied = UnionPayVerfinSign.verifySign(UnionPayInfo.getMerchantPublicCerPath(), sign, src);
				if (verfied) {
					//verfy success
					respCodetoUnionServer = "0000";
					checkOrderResult = OrderStatus.SUCCESS;
				}
			}
			//connect to the union server
			OrderInfoHttpTransfer connectUnionServer = new OrderInfoHttpTransfer(UnionPayInfo.getUnionServerUrl(), UnionPayInfo.getUnionServerTimeOut());
			//update the merchantOrderId by from union notify telegram
			UnionPayInfo.setMerchantOrderId(unionPayNotifyQueryTelegram.getMerchantOrderId());
			String telegram = UnionXmlTelegram.getVerifyRspTelegram(respCodetoUnionServer);
			connectUnionServer.sendMsg(telegram);
		}
		return checkOrderResult;
	}
}
