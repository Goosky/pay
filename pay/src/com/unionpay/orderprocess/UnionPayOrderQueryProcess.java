/**
 * 
 */
package com.unionpay.orderprocess;

import org.apache.log4j.Logger;

import com.interactiontimes.database.OrderInfoField;
import com.interactiontimes.database.OrderInfoInDatabase;
import com.interactiontimes.database.OrderStatus;
import com.interactiontimes.database.OrderStatusChange;
import com.interactiontimes.database.UnionPaySuccessOrderLog;
import com.pay.orderprocesschoose.OrderProcess;
import com.pay.utiles.OrderInfoHttpTransfer;
import com.pay.utiles.XmlDocResolution;
import com.unionpay.unionpayinfo.UnionPayInfo;
import com.unionpay.unionpayinfo.UnionPayNotifyQueryTelegramModel;
import com.unionpay.unionpayinfo.UnionPaySuccessOrderXmlTelegramResolution;
import com.unionpay.unionpayinfo.UnionXmlTelegram;
import com.unionpay.utiles.UnionPayPrivateKey;
import com.unionpay.utiles.UnionPaySignByRSA;

/**
 * @author Podevor
 *
 */
public class UnionPayOrderQueryProcess {
	public static void OrderQueryProcess(String merchantId,String orderId){
		// get order time from database
		Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置前查询数据库中订单时间");
		String orderTime = OrderInfoInDatabase.getOrderInfo(orderId,OrderInfoField.ORDERTIME);
		if (!OrderStatus.NOTFOUND.equals(orderTime)) {
			// query to union server
			Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理-定义商户和订单号信息");
			
			Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理-根据订单号和订单时间生成查询原串");
			// get souce sign
			String querySourceSign = UnionXmlTelegram.getQuerySourceSign(merchantId,orderId,orderTime);

			Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理-查询原串签字");
			// xml telegram sign
			String xmlSign = UnionPaySignByRSA.createSign(querySourceSign,
					UnionPayInfo.getMerchantPrivateKeyAlias(),
					UnionPayInfo.getMerchantPassword(),
					UnionPayPrivateKey.getPrivateSign());
			Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理-获取查询报文");
			String queryTelegram = UnionXmlTelegram.getQueryTelegram(merchantId,orderId,orderTime,xmlSign);
			Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理-开始请求查询");
			// connect to the union server
			OrderInfoHttpTransfer connectUnionServer = new OrderInfoHttpTransfer(
					UnionPayInfo.getUnionServerUrl(),
					UnionPayInfo.getUnionServerTimeOut());
			connectUnionServer.sendMsg(queryTelegram);
			// recv message:log in union success orderlog
			OrderStatusUpdate(connectUnionServer.getReMeg());
		}
	}
	private static void OrderStatusUpdate(String recvXml) {
		Logger.getLogger(OrderProcess.class.getClass()).info("查询银联前置回馈通知处理");
		String queryResult = XmlDocResolution.getValueInXml(recvXml, "queryResult");
		String orderCurrentStatus = null;
		if ( "0".equals(queryResult) ){
			UnionPaySuccessOrderXmlTelegramResolution 
			unionPaySuccessOrderXmlTelegram = new UnionPaySuccessOrderXmlTelegramResolution(recvXml);
			UnionPayNotifyQueryTelegramModel unionQueryTelegramModel = unionPaySuccessOrderXmlTelegram.getUnionPayNotifyQueryTelegramModel();
			UnionPaySuccessOrderLog.UnionPaySuccessOrderLogInDatabase(unionQueryTelegramModel);
			orderCurrentStatus = OrderStatus.SUCCESS;
		}else{
			orderCurrentStatus = OrderStatus.FAILURE;
		}
		//change order status
		OrderStatusChange.updateProcess(XmlDocResolution.getValueInXml(recvXml, "merchantOrderId"),orderCurrentStatus );
	}
}
