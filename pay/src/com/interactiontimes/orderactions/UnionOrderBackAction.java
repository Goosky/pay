/**
 * 
 */
package com.interactiontimes.orderactions;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.interactiontimes.orderactionprocess.UnionOrderBackActionProcess;
import com.opensymphony.xwork2.Action;

/**
 * @author Podevor
 * 
 */
public class UnionOrderBackAction implements Action {

	public String execute() throws Exception {
		Logger.getLogger(this.getClass()).info("来着前置的订单回馈");
		HttpServletRequest request = ServletActionContext.getRequest();
		UnionOrderBackActionProcess.orderBackProcess(request);
		return null;
	}
}
