/**
 * 
 */
package com.interactiontimes.orderactionprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.interactiontimes.database.OrderStatus;
import com.interactiontimes.payinfo.InitMerchantInfotoUnionPay;
import com.pay.orderprocesschoose.OrderProcess;
import com.unionpay.orderprocess.UnionPayOrderCheckProcess;
import com.unionpay.orderprocess.UnionPayOrderQueryProcess;
import com.unionpay.unionpayinfo.UnionPayNotifyQueryTelegramModel;
import com.unionpay.unionpayinfo.UnionPayNotifyXmlTelegramResolution;
/**
 * @author Podevor
 * 
 */
public class UnionOrderBackActionProcess {
	// save result to database
	public static void orderBackProcess(HttpServletRequest request) {
		try {
			InputStream in = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			StringBuffer responseBuilder = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseBuilder.append(line);
			}
			String xmlDoc = URLDecoder.decode(responseBuilder.toString(),
					"UTF-8");
			Logger.getLogger(OrderProcess.class.getClass()).info(
					"银联前置通知报文" + xmlDoc);
			// init merchantunionpay info
			InitMerchantInfotoUnionPay.merchantUnionInfoSetting();

			UnionPayNotifyXmlTelegramResolution 
			unionPayXmlTelegram = new UnionPayNotifyXmlTelegramResolution(xmlDoc);
			UnionPayNotifyQueryTelegramModel 
			unionPayNotifyQueryTelegram = unionPayXmlTelegram.getUnionPayNotifyQueryTelegramModel();

			String checkOrderResult = UnionPayOrderCheckProcess
					.OrderCheckProcess(xmlDoc);
			if (OrderStatus.SUCCESS.equals(checkOrderResult)) {
				String orderId = unionPayNotifyQueryTelegram
						.getMerchantOrderId();
				String merchantId = unionPayNotifyQueryTelegram.getMerchantId();
				UnionPayOrderQueryProcess.OrderQueryProcess(merchantId,orderId);
			}
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(OrderProcess.class.getClass()).info("银联前置通知处理异常"+e.getLocalizedMessage());
		} catch (IOException e) {
			Logger.getLogger(OrderProcess.class.getClass()).info("银联前置通知处理异常"+e.getLocalizedMessage());
		}
	}
}
