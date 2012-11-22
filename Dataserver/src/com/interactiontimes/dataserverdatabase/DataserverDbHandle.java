/**
 * 
 */
package com.interactiontimes.dataserverdatabase;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

/**
 * @author Podevor
 * 
 */
public class DataserverDbHandle {
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver"; // driver
	private static final String MYSQL_URL = "jdbc:mysql://"
								+InitDataserverDhInfo.dbIp+":"
								+InitDataserverDhInfo.dbPort+"/"
								+InitDataserverDhInfo.dbName
								+"?useUnicode=true&characterEncoding=UTF8";
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static Connection conn = null;

	//
	static {
		try {
			Class.forName(MYSQL_DRIVER).newInstance();
			conn = DriverManager.getConnection(MYSQL_URL,InitDataserverDhInfo.dbUserName,InitDataserverDhInfo.dbPasswd);
			threadLocal.set(conn);
		} catch (Exception e) {
			Logger.getLogger(DataserverDbHandle.class).info(
					"初始化数据库连接句柄异常" + e.getLocalizedMessage());
		}
	}

	// get the database conn handle
	public static Connection getConnection() {
		conn = (Connection) threadLocal.get();
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(MYSQL_URL,InitDataserverDhInfo.dbUserName,InitDataserverDhInfo.dbPasswd);
				threadLocal.set(conn);
			} catch (Exception e) {
				Logger.getLogger(DataserverDbHandle.class).info(
						"初始化数据库连接句柄异常" + e.getLocalizedMessage());
			}
		}
		return conn;
	}

	// close the database
	public static boolean closeConnection() {
		boolean isClosed = true;
		conn = (Connection) threadLocal.get();
		threadLocal.set(null);
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				isClosed = false;
				Logger.getLogger(DataserverDbHandle.class).info(
						"数据库连接句柄关闭异常" + e.getLocalizedMessage());
			}
		}
		conn = null;
		return isClosed;
	}
}
