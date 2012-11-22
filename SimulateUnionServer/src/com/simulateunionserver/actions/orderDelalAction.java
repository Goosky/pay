/**
 * 
 */
package com.simulateunionserver.actions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.unionpay.unionpayinfo.UnionPayInfo;
import com.unionpay.unionpayinfo.UnionXmlSign;
import com.unionpay.utils.UnionPayXmlHttpConnection;
import com.unionpay.utils.UnionPayXmlResolution;

/**
 * @author Podevor
 *
 */
public class orderDelalAction implements Action {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Action#execute()
	 */
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		InputStream in = request.getInputStream();
		HttpServletResponse response = ServletActionContext.getResponse();
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
			//to server telegram
			String merchantId = UnionPayXmlResolution.getValueInXmlBy(xmlDoc, "merchantId");
			String merchantOrderId = UnionPayXmlResolution.getValueInXmlBy(xmlDoc, "merchantOrderId");
			String merchantOrderTime = UnionPayXmlResolution.getValueInXmlBy(xmlDoc, "merchantOrderTime");
			String merchantAmt = UnionPayXmlResolution.getValueInXmlBy(xmlDoc, "merchantOrderAmt");
			String xml1 = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"
					  +"<upomp version='1.0.0' application='SubmitOrder.Rsp'>"
					  +"<merchantId>"+merchantId+"</merchantId>"
					  +"<merchantOrderId>"+merchantOrderId+"</merchantOrderId>"
					  +"<merchantOrderTime>"+merchantOrderTime+"</merchantOrderTime>"
					  +"<merchantOrderAmt>"+merchantAmt+"</merchantOrderAmt>"
					  +"<respCode>0000</respCode>"
					  +"<respDesc>订单操作成功</respDesc>"
					  +"</upomp>";

			
			String xml2 = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>"
					   +"<upomp application='QueryOrder.Rsp' version='1.0.0'>" 
						+"<transType>01</transType>"
						+"<merchantId>898000000000002</merchantId>"
						+"<merchantOrderId>26597502398730806225</merchantOrderId>"
						+"<merchantOrderTime>20121017211548</merchantOrderTime>"
						+"<sign>aadadad</sign>"
						+"<queryResult>0</queryResult>"
						+"<settleDate>022231350463290108</settleDate>"
						+"<setlAmt>1234</setlAmt>"
						+"<setlCurrency>156</setlCurrency>"
						+"<converRate>0.001</converRate>"
						+"<cupsQid>898565623656</cupsQid>"
						+"<cupsTraceNum>12235333265689</cupsTraceNum>"
						+"<cupsTraceTime>56598989865</cupsTraceTime>"
						+"<cupsRespCode>00</cupsRespCode>"
						+"<cupsRespDesc>成功</cupsRespDesc>"
					+"</upomp>";
			int runIndex = 10;
			if (runIndex == 0){

				out.println(xml1);
			}else{

				out.println(xml2);
			}
		}else{
			out.println("空的数据报文");
		}

		return null;
	}
}
