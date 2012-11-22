/**
 * 
 */
package com.interactiontimes.orderactions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.*;


import com.interactiontimes.orderactionprocess.OrderInActionProcess;
import com.opensymphony.xwork2.Action;

/**
 * @author Podevor
 *
 */
public class OrderInAction implements Action {

	public String execute() throws Exception {
		Logger.getLogger(this.getClass()).info("新的订单进入");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		OrderInActionProcess.OrderInProcess(request, response);
		return null;
	}
}
