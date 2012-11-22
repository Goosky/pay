/**
 * 
 */
package com.interactiontimes.dataserver;

/**
 * @author Podevor
 *
 */
public class DataServerXmlTelegram {
	public static String updateTelegram(String user , String itemId ,String itemCnt,String gameName,String gameVersion){
		return "<?xml version='1.0' encoding='UTF-8'?>" +
				"<interactiontimes version='1.0'>" +
				"<processType>00</processType>"+
				"<gameName>"+gameName+"</gameName>"+
				"<gameVersion>"+gameVersion+"</gameVersion>"+
				"<userId>"+user+"</userId>"+
				"<itemId>"+itemId+"</itemId>" +
				"<itemCnt>"+itemCnt+"</itemCnt>" +
				"</interactiontimes>";
	}
}
