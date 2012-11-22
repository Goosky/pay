/**
 * 
 */
package com.interactiontimes.saimadataprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.interactiontimes.dataextends.DataProcess;
import com.pay.utiles.XmlDocResolution;

/**
 * @author Podevor
 *
 */
public class SaimaDataProcess extends DataProcess {
	private int userId = 0;
	private int itemId = 0;
	private int itemCnt = 0;
	private int itemOldCnt = 0;
	public SaimaDataProcess(String xml) {
		super(xml);
		userId = Integer.parseInt(XmlDocResolution.getValueInXml(xml, "userId"));
		itemId = Integer.parseInt(XmlDocResolution.getValueInXml(xml, "itemId"));
		itemCnt = Integer.parseInt(XmlDocResolution.getValueInXml(xml, "itemCnt"));
	}
	@Override
	public void updateProcess() {
		selectProcess();
		String sql = "UPDATE GAME_ITEMS SET ITEM_NUM = ? WHERE USERID = ? AND ITEM_ID = ?;";
		Connection conn = super.getConn();
		boolean result = false;
		try {
			itemCnt += itemOldCnt;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, itemCnt);
			pstmt.setInt(2, userId);	
			pstmt.setInt(3, itemId);
			conn.setAutoCommit(false);
			if (pstmt.executeUpdate() != 0) {
				Logger.getLogger(this.getClass()).info("赛马数据信息更新成功");
				result = true;
				conn.commit();
			}else{
				Logger.getLogger(this.getClass()).info("赛马数据信息更新失败");
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				Logger.getLogger(this.getClass()).info("赛马数据信息更新异常"+e1.getLocalizedMessage());
			}
			result = false;
			Logger.getLogger(this.getClass()).info("赛马数据信息更新异常"+e.getLocalizedMessage());
		}
		setResult(result);
	}
	@Override
	public void selectProcess() {
		Logger.getLogger(this.getClass()).info("ITEM信息查询");
		String sql = "SELECT * FROM GAME_ITEMS WHERE USERID = ? AND ITEM_ID = ?;";
		Connection conn = super.getConn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);	
			pstmt.setInt(2, itemId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				itemOldCnt = rs.getInt("ITEM_NUM");
			}
		} catch (SQLException e) {
			Logger.getLogger(this.getClass()).info("订单信息查询异常"+e.getLocalizedMessage());
		}
	}
	@Override
	public void deteleProcess() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insertProcess() {
		// TODO Auto-generated method stub
		
	}
}
