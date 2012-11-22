/**
 * 
 */
package com.interactiontimes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.interactiontimes.dataserver.DataServer;

/**
 * @author Podevor
 *
 */
public class OrderStatusChange {
	public static void updateProcess(String orderId, String orderStatus) {
		Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录更新");
		Connection conn = OrderLogDbHandle.getConnection();
		String sql = "UPDATE ORDERIN_HISTORYLOG SET STATUS = ? WHERE ORDERID = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderStatus);
			pstmt.setString(2, orderId);	
			conn.setAutoCommit(false);
			if (pstmt.executeUpdate() != 0) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录更新成功");
				conn.commit();
			}else{
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录更新失败");
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("银联订单记录更新异常"+e1.getLocalizedMessage());
			}
			Logger.getLogger(OrderInHistoryLog.class.getClass()).info("银联订单记录更新异常"+e.getLocalizedMessage());
		}finally{
			OrderLogDbHandle.closeConnection();
		}
		if (OrderStatus.SUCCESS.equals(orderStatus)) {
			DataServer.updateOrderInfo(orderId);
		}
	}
}
