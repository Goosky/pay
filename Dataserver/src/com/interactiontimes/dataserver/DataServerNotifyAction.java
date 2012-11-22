/**
 * 
 */
package com.interactiontimes.dataserver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.interactiontimes.dataserveractionprocess.NotifyActionProcess;
import com.opensymphony.xwork2.Action;

/**
 * @author Podevor
 *
 */
public class DataServerNotifyAction implements Action {

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		NotifyActionProcess.notifyActionProcess(request, response);
		return null;
	}
}
