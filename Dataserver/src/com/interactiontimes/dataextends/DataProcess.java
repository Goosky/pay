/**
 * 
 */
package com.interactiontimes.dataextends;

import java.sql.Connection;
import java.sql.ResultSet;

import com.interactiontimes.dataserverdatabase.DataserverDbHandle;

/**
 * @author Podevor
 *
 */
public abstract class DataProcess {
	private Connection conn = DataserverDbHandle.getConnection();
	private boolean result = false;
	private ResultSet rs = null;
	public DataProcess(String xml){
	}
	public abstract void updateProcess();
	public abstract void selectProcess();
	public abstract void deteleProcess();
	public abstract void insertProcess();
	public boolean getProcessResult(){
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public Connection getConn(){
		return conn;
	}
	
	public boolean closeProcess(){
		return DataserverDbHandle.closeConnection();
	}
	
	public void setRs(ResultSet rs){
		this.rs = rs;
	}
	public ResultSet getRs(){
		return rs;
	}
}
