/**
 * 
 */
package com.interactiontimes.dataserveractionprocess;

import java.io.*;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.interactiontimes.dataextends.DataServerProcessStatus;
import com.interactiontimes.dataserver.DataServerProcess;

/**
 * @author Podevor
 *
 */
public class NotifyActionProcess {
	public static void notifyActionProcess(HttpServletRequest request,
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

			String outResult = null;
			if(!"".equals(xmlDoc) && xmlDoc != null){
				Logger.getLogger(NotifyActionProcess.class.getClass()).info("数据信息更新操作开始");
				boolean result = DataServerProcess.dataProcess(xmlDoc);
				if (result){
					outResult = DataServerProcessStatus.PROCESSSUCCESS;
				}else{
					outResult = DataServerProcessStatus.PROCESSFAILURE;
				}
			}else{
				outResult = DataServerProcessStatus.PROCESSFAILURE;
			}
			out.print(outResult);
			out.flush();
			out.close();
		} catch (IOException e) {
			Logger.getLogger(NotifyActionProcess.class.getClass()).info("数据信息更新操作异常");
		}
	}
}
