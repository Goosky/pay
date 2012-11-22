/**
 * 
 */
package com.pay.orderprocesschoose;

import org.apache.log4j.Logger;

import com.alipay.alipayinfo.AliPayInfo;
import com.pay.orderprocess.AliPayProcess;
import com.pay.orderprocess.UnionPayProcess;
import com.unionpay.unionpayinfo.UnionPayInfo;
/**
 * @author Podevor
 *
 */
public class OrderProcess {
	private static String orderDesc = null;
	private static String orderId = null;
	private static String orderTime = null;
	public static String OrderProcessChoose(String user,String payType,
			String itemId,String itemCnt){
		String result = null;
		//pay type
		int type = Integer.parseInt(payType);		 
		//item id
		int localItemId = Integer.parseInt(itemId);
		//item cnt
		int localItemCnt = Integer.parseInt(itemCnt);
		switch (type) {
		case 0: {// alipay
			Logger.getLogger(OrderProcess.class.getClass()).info("支付宝订单生成处理");
			Logger.getLogger(OrderProcess.class.getClass()).info(
					"支付宝订单itemId" + itemId + "itenCnt" + itemCnt);
			AliPayProcess aliPay = new AliPayProcess();
			result = aliPay.orderProcess(localItemId, localItemCnt, user);
			orderDesc = AliPayInfo.getOrderDesc();
			orderId = AliPayInfo.getOrderId();
			orderTime = AliPayInfo.getOrderTime();
		}

			break;

		case 1: {// union
			Logger.getLogger(OrderProcess.class.getClass()).info("银联支付处理");
			Logger.getLogger(OrderProcess.class.getClass()).info(
					"银联支付itemId" + itemId + "itenCnt" + itemCnt);
			UnionPayProcess unionPay = new UnionPayProcess();
			// union pay process
			result = unionPay.orderProcess(localItemId, localItemCnt, user);
			orderDesc = UnionPayInfo.getMerchantOrderDesc();
			orderId = UnionPayInfo.getMerchantOrderId();
			orderTime = UnionPayInfo.getMerchantOrderTime();
		}
			break;
		default: {
			result = "85858";
		}
			break;
		}
		Logger.getLogger(OrderProcess.class.getClass()).info("result=============="+result);
		return result;
	}
	public static String OrderDesc(){
		return orderDesc;
	}
	public static String OrderId(){
		return orderId;
	}
	public static String OrderTime(){
		return orderTime;
	}
}
