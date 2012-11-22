/**
 * 
 */
package com.interactiontimes.dataserver;

import org.apache.log4j.Logger;

import com.interactiontimes.database.OrderInfoField;
import com.interactiontimes.database.OrderInfoInDatabase;
import com.interactiontimes.database.OrderStatus;
import com.interactiontimes.payinfo.InitInteractionTimesPayInfo;
import com.pay.utiles.OrderInfoHttpTransfer;

/**
 * @author Podevor
 *
 */
public class DataServer {
	private static int tick = 0;
	public static void updateOrderInfo(String orderId){
		Logger.getLogger(DataServer.class.getClass()).info("数据服务器数据更新请求");
		String userId = OrderInfoInDatabase.getOrderInfo(orderId, OrderInfoField.USERID);
		String itemId = OrderInfoInDatabase.getOrderInfo(orderId, OrderInfoField.ITEMID);
		String itemCnt = OrderInfoInDatabase.getOrderInfo(orderId, OrderInfoField.ITEMCNT);
		String gameName = OrderInfoInDatabase.getOrderInfo(orderId, OrderInfoField.GAMENAME);
		String gameVersion = OrderInfoInDatabase.getOrderInfo(orderId, OrderInfoField.GAMEVERION);
		if (!OrderStatus.NOTFOUND.equals(userId)
				&& !OrderStatus.NOTFOUND.equals(itemId)
				&& !OrderStatus.NOTFOUND.equals(itemCnt)) {
			//sent telegram to the data server
			String telegramToDataServer = DataServerXmlTelegram.updateTelegram(userId, itemId, itemCnt,gameName,gameVersion);
			OrderInfoHttpTransfer connectDataServer = new OrderInfoHttpTransfer(InitInteractionTimesPayInfo.DATASERVERURL,"");	
			connectDataServer.sendMsg(telegramToDataServer);
			while(!DataServerProcessStatus.PROCESSSUCCESS.equals(connectDataServer.getReMeg())){
				try {
					Thread.sleep(1000);
					tick++;
					if (tick == 5) {
						connectDataServer.sendMsg(telegramToDataServer);
						tick = 0;
					}
				} catch (InterruptedException e) {
					Logger.getLogger(DataServer.class.getClass()).info("数据服务器数据更新频率请求异常"+e.getLocalizedMessage());
				}
			};
			tick = 0;
		}
	}
}
