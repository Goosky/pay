/**
 * 
 */
package com.interactiontimes.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
/**
 * @author Podevor
 *
 */
public class OrderInHistoryLog {
	public static boolean OrderInLogInDataBase(String orderId,
			String orderTime,String desc,String payType,String itemId,String itemCnt,String gameName,String gameVersion,String user){
		Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录入库");
		Connection conn = OrderLogDbHandle.getConnection();
		String sql = "INSERT INTO ORDERIN_HISTORYLOG VALUES(?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderId);
			pstmt.setString(2, orderTime);
			pstmt.setString(3, desc);
			pstmt.setString(4, payType);
			pstmt.setString(5, itemId);
			pstmt.setString(6, itemCnt);
			pstmt.setString(7, gameName);
			pstmt.setString(8, gameVersion);
			pstmt.setString(9, OrderStatus.READY);
			pstmt.setString(10, user);
			conn.setAutoCommit(false);
			if (pstmt.executeUpdate() != 0) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录成功");
				result = true;
				conn.commit();
			}else{
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("订单记录失败");
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("银联订单记录失败"+e1.getLocalizedMessage());
			}
			Logger.getLogger(OrderInHistoryLog.class.getClass()).info("银联订单记录失败"+e.getLocalizedMessage());
		}finally{
			OrderLogDbHandle.closeConnection();
		}
		return result;
	}
}
