/**
 * 
 */
package com.interactiontimes.orderactionprocess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.interactiontimes.database.OrderInHistoryLog;
import com.pay.orderprocesschoose.OrderProcess;
import com.pay.utiles.XmlDocResolution;
/**
 * @author Podevor
 *
 */
public class OrderInActionProcess {
	public static void OrderInProcess(HttpServletRequest request,
			HttpServletResponse response){
		try {
			InputStream in = request.getInputStream();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			StringBuffer responseBuilder = new StringBuffer();
			String line = null;
			while ( (line = reader.readLine()) != null ){
				responseBuilder.append(line);
			}
			String xmlDoc = URLDecoder.decode(responseBuilder.toString(),"UTF-8");
			if(!"".equals(xmlDoc) && xmlDoc != null){
				String gameName = XmlDocResolution.getValueInXml(xmlDoc, "gameName");
				String gameVersion = XmlDocResolution.getValueInXml(xmlDoc, "gameVersion");
				String user = XmlDocResolution.getValueInXml(xmlDoc, "userId");
				String itemId = XmlDocResolution.getValueInXml(xmlDoc, "itemId");
				String itemCnt = XmlDocResolution.getValueInXml(xmlDoc, "itemCnt");
				String payType = XmlDocResolution.getValueInXml(xmlDoc, "payType");
				String result = OrderProcess.OrderProcessChoose(user,payType, itemId, itemCnt);
				Logger.getLogger(OrderInActionProcess.class).info("新的订单处理完成,处理结果:\n"+result);
				//log in database
				boolean logResult = false;
				if (!"85858".equals(result)) {
					//log new line in database : user and the merchantOrderId and pay type : log the order log in database reference the merchantOrderId get the user
					//String merchantOrderId = PayXmlResolution.getValueInXml(result, "merchantOrderId");
					//String merchantOrderTime = PayXmlResolution.getValueInXml(result, "merchantOrderTime");
					logResult = OrderInHistoryLog.OrderInLogInDataBase(OrderProcess.OrderId(), OrderProcess.OrderTime(), OrderProcess.OrderDesc(),payType,itemId,itemCnt,gameName,gameVersion,user);
				}
				if (logResult) {
					out.println(result);
				}else{
					out.println("85858");
				}
			}else{
				out.println("空订单数据");
			}
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(OrderInActionProcess.class).error("订单处理异常");
		} catch (IOException e) {
			Logger.getLogger(OrderInActionProcess.class).error("订单处理异常");
		}
	}
}
