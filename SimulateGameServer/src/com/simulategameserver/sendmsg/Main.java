/**
 * 
 */
package com.simulategameserver.sendmsg;

import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.SliderUI;

import com.pay.utiles.LanchPayXmlTelegram;
import com.pay.utiles.OrderInfoHttpTransfer;
import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		int runIndex = 0;
		if (runIndex == 0){

			OrderInfoHttpTransfer connectPayServer = 
				new OrderInfoHttpTransfer("http://localhost:8985/pay/orderIn.action","");
		String sumbitTelegram = "<?xml version='1.0' encoding='UTF-8'?>" +
				"<interactiontimes version='1.0'>" +
				"<gameName>gameName</gameName>"+
				"<gameVersion>1.0</gameVersion>"+
				"<userId>2219</userId>"+
				"<itemId>1001</itemId>" +
				"<itemCnt>1</itemCnt>" +
				"<payType>1</payType>" +
				"</interactiontimes>";
			connectPayServer.sendMsg(sumbitTelegram);
			 String recvMsg = connectPayServer.getReMeg();
			// System.out.println(recvMsg);
			 recvMsg = LanchPayXmlTelegram.lanchPayXml(recvMsg);
			 System.out.println(recvMsg);		
		}
		else{

			 String backendUrl = "http://localhost:8082/pay/unionBack.action";
				OrderInfoHttpTransfer connectMerchantServer = new OrderInfoHttpTransfer(backendUrl,"");
				String notifyXml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"
						   +"<upomp application='TransNotify.Req' version='1.0.0'>" 
							+"<transType>01</transType>"
							+"<merchantId>13241234</merchantId>"
							+"<merchantOrderId>89487298570693654356</merchantOrderId>"
							+"<merchantOrderAmt>132421432</merchantOrderAmt>"
							+"<settleDate>13241432</settleDate>"
							+"<setlAmt>13241324</setlAmt>"
							+"<setlCurrency>156</setlCurrency>"
							+"<converRate>0.001</converRate>"
							+"<cupsQid>898565623656</cupsQid>"
							+"<cupsTraceNum>12235333265689</cupsTraceNum>"
							+"<cupsTraceTime>56598989865</cupsTraceTime>"
							+"<cupsRespCode>00</cupsRespCode>"
							+"<cupsRespDesc>成功</cupsRespDesc>"
							+"<sign>asdada123113casdq</sign>"
						    +"<respCode>0000</respCode>"
						+"</upomp>";
			//	connectMerchantServer.sendMsg(notifyXml);

				System.out.println("收到的报文"+connectMerchantServer.getReMeg()+"code"+connectMerchantServer.getErrorCode()+connectMerchantServer.getErrorMsg());
		}
	//	OrderInfoHttpTransfer connectALiPayServer = 
		//new OrderInfoHttpTransfer("http://localhost:8082/unionPay/aliBack.action",6000);
		//String telegram = "notify_data=<notify><trade_status>TRADE_FINISHED</trade_status><total_fee>0.90</total_fee><subject>123456</subject><out_trade_no>322221350627029271</out_trade_no><notify_reg_time>2010 11 -1814:02:43.000</notify_reg_time></notify>&sign=ZPZULntRpJwFmGNI ZPZULntRpJwFmGNI VKwjLEF2Tze7bqs60rxQ22CqT5J1UlvGo575QK9z/+p+7E9cOoRoWzqR6xHZ6WVv3dloyGK VKwjLEF2Tze7bqs60rxQ22CqT5J1UlvGo575QK9z/+p+7E9cOoRoWzqR6xHZ6WVv3dloyGK=";
		//String telegram = "sign=ZPZULntRpJwFmGNI ZPZULntRpJwFmGNI VKwjLEF2Tze7bqs60rxQ22CqT5J1UlvGo575QK9z/+p+7E9cOoRoWzqR6xHZ6WVv3dloyGK VKwjLEF2Tze7bqs60rxQ22CqT5J1UlvGo575QK9z/+p+7E9cOoRoWzqR6xHZ6WVv3dloyGK=&notify_data=<notify><trade_status>TRADE_FINISHED</trade_status><total_fee>0.90</total_fee><subject>123456</subject><out_trade_no>1118060201</out_trade_no><notify_reg_time>2010 11 -1814:02:43.000</notify_reg_time></notify>";
		//connectALiPayServer.sendMsg(telegram);
		//System.out.println(connectALiPayServer.getReMeg());
	}
	
}
