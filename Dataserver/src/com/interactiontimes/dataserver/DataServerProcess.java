/**
 * 
 */
package com.interactiontimes.dataserver;

import com.interactiontimes.saimadataprocess.SaimaDataProcess;
import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class DataServerProcess {
	public static boolean dataProcess(String xmlDoc){
		boolean processResult = false;
		String gameName = XmlDocResolution.getValueInXml(xmlDoc, "gameName");
		if ("saima".equals(gameName)){
			SaimaDataProcess saimaDataProcess = new SaimaDataProcess(xmlDoc);
			saimaDataProcess.updateProcess();
			processResult = saimaDataProcess.getProcessResult();
			saimaDataProcess.closeProcess();
		}
		return processResult;
	}
}
