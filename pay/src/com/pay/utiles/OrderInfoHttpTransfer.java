package com.pay.utiles;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/*
 * author: Podevor
 */

public class OrderInfoHttpTransfer implements Serializable {
	private static final long serialVersionUID = -8576553457942741012L;
	private final int RESPCODE_SUCCESS = 200;
	private String URL;
	private String recvMsg;
	private HttpURLConnection urlCon;
	private int errCode;
	private String errMsg;
	InputStream in;
	public OrderInfoHttpTransfer(String url, int timeOut) {
		this(url, timeOut + "");
	}

	public OrderInfoHttpTransfer(String url, String timeOut) {
		this.URL = url;
		System.setProperty("sun.net.client.defaultConnectTimeout", timeOut);
		System.setProperty("sun.net.client.defaultReadTimeout", timeOut);
	}

	private boolean open() {
		try {
			urlCon = (HttpURLConnection) new URL(URL).openConnection();
			return true;
		}catch (Exception e) {
			errCode = -11;
			errMsg = "打开连接失败"+e.getLocalizedMessage();
		}
		return false;
	}
	
	public boolean sendMsg(String msgStr) {
		if (!open())
			return false;
		OutputStream os =null;
		InputStream is=null;
		try {
			try{
				urlCon.setRequestMethod("POST");
				urlCon.setRequestProperty("content-type", "text/html");
				urlCon.setDoOutput(true);
				urlCon.setDoInput(true);
				os =urlCon.getOutputStream();
				OutputStreamWriter writer = new OutputStreamWriter(os);
				writer.write(URLEncoder.encode(msgStr, "UTF-8"));
				writer.flush();
			}catch (Exception e) {
				errCode = -21;
				errMsg = "服务器拒绝连接，请确定是否已经开启服务器"+e.getLocalizedMessage();
				return false;
			}
			try {
				int respCode = urlCon.getResponseCode();
				if (RESPCODE_SUCCESS != respCode) {
					errCode = -31;
					errMsg="httpState=[" + respCode + "]";	
				} 
				is=urlCon.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
				StringBuilder responseBuilder = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					responseBuilder.append(line);
				}
				recvMsg=URLDecoder.decode(responseBuilder.toString(),"utf-8");				
				in = StringToInputStream(recvMsg);			
				
			} catch (Exception e) {
				errCode = -32;
				errMsg = "传输数据异常"+e.getLocalizedMessage();
				return false;
			}  
		}
		finally {
			close(is);
			close(os);
			urlCon.disconnect();
			urlCon = null;
		}
		return true;
	}
	private void close(InputStream stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (Exception e) {
		}
		stream=null;
	}

	private  void close(OutputStream stream) {
		try {
			if (stream != null) {
				stream.close();
			}
		} catch (Exception e) {
		}
		stream=null;
	}

	public int getErrorCode() {
		return errCode;
	}

	public String getErrorMsg() {
		return errMsg;
	}

	public InputStream getRecvMsg() {
		return in;
	}
	public String getReMeg() {
		return recvMsg;
	}
	InputStream StringToInputStream (String recvMsg){
		ByteArrayInputStream stream = new ByteArrayInputStream(recvMsg.getBytes());
		return stream;
	}
	
}
