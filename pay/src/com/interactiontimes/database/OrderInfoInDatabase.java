/**
 * 
 */
package com.interactiontimes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * @author Podevor
 *
 */
public class OrderInfoInDatabase {
	public  static String getOrderInfo(String orderId,String field){
		Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单信息查询");
		String orderInfo = OrderStatus.NOTFOUND;
		Connection conn = OrderLogDbHandle.getConnection();
		String sql = "SELECT * FROM ORDERIN_HISTORYLOG WHERE ORDERID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderId);	
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单信息查询成功");
				orderInfo = rs.getString(field);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单信息查询异常"+e.getLocalizedMessage());
		}finally{
			OrderLogDbHandle.closeConnection();
		}
		return orderInfo;
	}
}
