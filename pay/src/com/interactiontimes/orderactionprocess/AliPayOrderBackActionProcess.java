/**
 * 
 */
package com.interactiontimes.orderactionprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alipay.alipayinfo.AliPayInfo;
import com.alipay.utiles.AliPaySignByRSA;
import com.interactiontimes.database.OrderStatus;
import com.interactiontimes.database.OrderStatusChange;
import com.pay.orderprocesschoose.OrderProcess;
import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class AliPayOrderBackActionProcess {
	public static void orderBackProcess(HttpServletRequest request,
			HttpServletResponse response){
		Logger.getLogger(OrderProcess.class.getClass()).info("支付宝安全支付的通知");
		try {
			InputStream in = request.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			StringBuffer responseBuilder = new StringBuffer();
			String line = null;
			while ( (line = reader.readLine()) != null ){
				responseBuilder.append(line);
			}
			PrintWriter out = response.getWriter();
			String sourceData = URLDecoder.decode(responseBuilder.toString(),"UTF-8");
			if(!"".equals(sourceData)){
				String []dataArray = null;
				String []temp = null;
				String sign = null;
				String notifydata = null;
				if (sourceData.split("&sign=").length == 1) {
					dataArray = sourceData.split("&notify_data=");
					temp = dataArray[0].split("sign=");
					sign = temp[1];
					notifydata = dataArray[1];
				}else{
					dataArray = sourceData.split("&sign=");
					sign = dataArray[1];
					temp = dataArray[0].split("notify_data=");
					notifydata = temp[1];
				}
				String xmlDoc = "<?xml version='1.0' encoding='UTF-8'?>" +notifydata;
				notifydata = "notify_data="+notifydata;
				boolean verfied  = AliPaySignByRSA.doCheck(notifydata, sign, AliPayInfo.getRsa_alipay_public());
				String tradeStatus = null;
				String out_trade_no = null;
				if (verfied){
					out.print("success");
					tradeStatus = XmlDocResolution.getValueInXml(xmlDoc, "trade_status");
					out_trade_no = XmlDocResolution.getValueInXml(xmlDoc, "out_trade_no");
					if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
						tradeStatus = OrderStatus.SUCCESS;
					}
					else{
						tradeStatus = OrderStatus.FAILURE;
					}
					//log the result to local database
					OrderStatusChange.updateProcess(out_trade_no, tradeStatus);
					Logger.getLogger(OrderProcess.class.getClass()).info("支付宝安全支付的成功通知");
				}else{
					out.print("fail");
					Logger.getLogger(OrderProcess.class.getClass()).info("支付宝安全支付的失败通知");
				}
			}else{
				out.print("empty data");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(OrderProcess.class.getClass()).error("获取支付宝安全支付的通知信息失败");
		}		
	}
}
