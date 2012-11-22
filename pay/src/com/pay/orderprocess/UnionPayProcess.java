/**
 * 
 */
package com.pay.orderprocess;

import java.text.DecimalFormat;
import java.util.Random;

import org.apache.log4j.Logger;

import com.interactiontimes.payinfo.InitMerchantInfotoUnionPay;
import com.interactiontimes.products.InitProductstoUnionPay;
import com.pay.orderprocesschoose.OrderProcess;
import com.pay.utiles.OrderInfoHttpTransfer;
import com.pay.utiles.XmlDocResolution;
import com.unionpay.unionpayinfo.*;
import com.unionpay.utiles.UnionPayPrivateKey;
import com.unionpay.utiles.UnionPaySignByRSA;
/**
 * @author Podevor
 *
 */
public class UnionPayProcess {
	public UnionPayProcess(){
		//init the union pay info
		InitMerchantInfotoUnionPay.merchantUnionInfoSetting();
		InitProductstoUnionPay.initProducts();
	}

	public String orderProcess(int itemId, int itemCnt,String user) {
		Logger.getLogger(this.getClass()).info("银联订单处理");
		StringBuffer merchantOrderAmt = new StringBuffer();
		//orderId
		UnionPayInfo.setMerchantOrderId(getMerchantOrderId());
		//orderAmt
		Logger.getLogger(this.getClass()).info("银联订单处理：获取商品单价");
		int price = InitProductstoUnionPay.getItemPriceById(itemId);
		int total_fee = price*itemCnt;
		merchantOrderAmt.append(total_fee+"");
		UnionPayInfo.setMerchantOrderAmt(merchantOrderAmt.toString());
		//orderDesc
		DecimalFormat dataformat = new DecimalFormat("0.00");	
		UnionPayInfo.setMerchantOrderDesc("购买"+InitProductstoUnionPay.getItemNameById(itemId)
				+itemCnt+"个；需支付"+dataformat.format(Double.parseDouble(UnionPayInfo.getMerchantOrderAmt())/100)+"元；"+InitProductstoUnionPay.getItemDescById(itemId));

		Logger.getLogger(this.getClass()).info("银联支付原串 \n"+UnionXmlTelegram.getSourceSign());
		UnionPayInfo.setSourceSign(UnionXmlTelegram.getSourceSign());
		//xml sign
		Logger.getLogger(this.getClass()).info("银联订单处理：xml加密");
		String xmlSign = UnionPaySignByRSA.createSign(UnionPayInfo.getSourceSign(),
				UnionPayInfo.getMerchantPrivateKeyAlias(), UnionPayInfo.getMerchantPassword(), UnionPayPrivateKey.getPrivateSign());

		Logger.getLogger(this.getClass()).info("银联支付加密签字 \n"+xmlSign);
		UnionPayInfo.setXmlSign(xmlSign);
		//submit telegram
		Logger.getLogger(this.getClass()).info("银联订单处理：获取发送给前置的签字源xml");
		String sumbitTelegram = UnionXmlTelegram.getSumbitTelegram();
		Logger.getLogger(this.getClass()).info("银联支付提交的报文 \n"+sumbitTelegram);
		//connect to the union server
		OrderInfoHttpTransfer connectUnionServer = new OrderInfoHttpTransfer(UnionPayInfo.getUnionServerUrl(), UnionPayInfo.getUnionServerTimeOut());
		connectUnionServer.sendMsg(sumbitTelegram);
		Logger.getLogger(this.getClass()).info("银联前置回馈信息\n"+connectUnionServer.getReMeg());
		//recv message
		return getRecvResultFromUnionServer(connectUnionServer.getReMeg(),user);
	}
	
	/**
	 * @return the merchantOrderId
	 */
	private static String getMerchantOrderId() {
		int cnt = 0;
		char orderSrc[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer orderId = new StringBuffer("");
		Random random = new Random();
		while (cnt < UnionPayInfo.getOrderIdLength()) {
			int index = Math.abs(random.nextInt(UnionPayInfo.getOrderIdLength()));
			if (index >= 0 && index < orderSrc.length) {
				orderId.append(orderSrc[index]);
				cnt++;
			}
		}
		return orderId.toString();
	}
	
	private static String getRecvResultFromUnionServer(String recvMessage,String user){
		String result = null;
		String respCode = null;
		if(!"".equals(recvMessage) && recvMessage != null){
			respCode = XmlDocResolution.getValueInXml(recvMessage, "respCode");
		}else{
			respCode = "85858";
		}
		//send the result to game server
		String merchantOrderId = null;
		String merchantOrderTime = null;
		if ("0000".equals(respCode)) {
			Logger.getLogger(OrderProcess.class.getClass()).info("银联支付验签处理成功");
			String merchantId = XmlDocResolution.getValueInXml(recvMessage, "merchantId");
			merchantOrderId = XmlDocResolution.getValueInXml(recvMessage, "merchantOrderId");
			merchantOrderTime = XmlDocResolution.getValueInXml(recvMessage, "merchantOrderTime");
			result = UnionXmlTelegram.signXml(merchantId,merchantOrderId,merchantOrderTime);
		}else{
			result = "85858";
			Logger.getLogger(OrderProcess.class.getClass()).info("银联支付验签处理失败");
		}
		return result;
	}
}
