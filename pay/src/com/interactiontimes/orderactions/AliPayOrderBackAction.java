/**
 * 
 */
package com.interactiontimes.orderactions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.interactiontimes.orderactionprocess.AliPayOrderBackActionProcess;
import com.opensymphony.xwork2.Action;

/**
 * @author Podevor
 *
 */
public class AliPayOrderBackAction implements Action {
	public String execute() throws Exception {
		Logger.getLogger(this.getClass()).info("来着支付宝安全支付的订单回馈");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AliPayOrderBackActionProcess.orderBackProcess(request,response);
		return null;
	}
}
