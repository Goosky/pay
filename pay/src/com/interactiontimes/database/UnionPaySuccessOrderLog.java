/**
 * 
 */
package com.interactiontimes.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.unionpay.unionpayinfo.UnionPayNotifyQueryTelegramModel;

/**
 * @author Podevor
 *
 */
public class UnionPaySuccessOrderLog {
	public static void UnionPaySuccessOrderLogInDatabase(UnionPayNotifyQueryTelegramModel unionPayNotifyQueryTelegramModel){
		Logger.getLogger(OrderInHistoryLog.class.getClass()).info("查询银联订单入库");
		Connection conn = OrderLogDbHandle.getConnection();
		String sql = "INSERT INTO UNION_SUCCESSORDERLOG VALUES(?,?,?,?,?,?,?,?,?,?,?)";	
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, unionPayNotifyQueryTelegramModel.getMerchantOrderId());//merchantOrderId
			pstmt.setString(2, unionPayNotifyQueryTelegramModel.getMerchantOrderTime());//merchantOrderTime
			pstmt.setString(3, unionPayNotifyQueryTelegramModel.getSettleDate());//settleDate
			pstmt.setString(4, unionPayNotifyQueryTelegramModel.getSetlAmt());//setlAmt
			pstmt.setString(5, unionPayNotifyQueryTelegramModel.getSetlCurrency());//setlCurrency
			pstmt.setString(6, unionPayNotifyQueryTelegramModel.getConverRate());//converRate
			pstmt.setString(7, unionPayNotifyQueryTelegramModel.getCupsQid());//cupsQid
			pstmt.setString(8, unionPayNotifyQueryTelegramModel.getCupsTraceNum());//cupsTraceNum
			pstmt.setString(9, unionPayNotifyQueryTelegramModel.getCupsTraceTime());//cupsTraceTime
			pstmt.setString(10, unionPayNotifyQueryTelegramModel.getCupsRespCode());//cupsRespCode
			pstmt.setString(11, unionPayNotifyQueryTelegramModel.getCupsRespDesc());//cupsRespDesc
			conn.setAutoCommit(false);
			if (pstmt.executeUpdate() != 0) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("查询的订单记录成功");
				conn.commit();
			}else{
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("查询的订单记录失败");
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				Logger.getLogger(OrderInHistoryLog.class.getClass()).info("查询的银联订单记录失败-可能这个订单已经入库"+e1.getLocalizedMessage());
			}
			Logger.getLogger(OrderInHistoryLog.class.getClass()).info("查询的银联订单记录失败-可能这个订单已经入库"+e.getLocalizedMessage());
		}finally{
			OrderLogDbHandle.closeConnection();
		}
	}
}
