/**
 * 
 */
package com.alipay.alipayinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Podevor
 *
 */
public class AliPayInfo {
	//partner
	private static String partner = null;
	//seller
	private static String seller = null;
	//merchant private key
	private static String rsa_private = null;
	//alipay public key
	private static String rsa_alipay_public = null;
	//notify url
	private static String notify_url = null;
	//orderid length
	private static int orderIdLength = 0;
	//order desc 
	private static String orderDesc = null;
	//orderId
	private static String orderId = null;
	//orderTime
	private static String orderTime = null;
	
	/**
	 * @return the orderId
	 */
	public static String getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public static void setOrderId(String orderId) {
		AliPayInfo.orderId = orderId;
	}
	/**
	 * @return the orderTime
	 */
	public static String getOrderTime() {
		orderTime = new SimpleDateFormat("yyyyMMddHHmmss")
		.format(new Date());
		return orderTime;
	}
	/**
	 * @return the orderDesc
	 */
	public static String getOrderDesc() {
		return orderDesc;
	}
	/**
	 * @param orderDesc the orderDesc to set
	 */
	public static void setOrderDesc(String orderDesc) {
		AliPayInfo.orderDesc = orderDesc;
	}
	/**
	 * @return the orderIdLength
	 */
	public static int getOrderIdLength() {
		return orderIdLength;
	}
	/**
	 * @param orderIdLength the orderIdLength to set
	 */
	public static void setOrderIdLength(int orderIdLength) {
		AliPayInfo.orderIdLength = orderIdLength;
	}
	/**
	 * @return the notify_url
	 */
	public static String getNotify_url() {
		return notify_url;
	}
	/**
	 * @param notify_url the notify_url to set
	 */
	public static void setNotify_url(String notify_url) {
		AliPayInfo.notify_url = notify_url;
	}
	/**
	 * @return the partner
	 */
	public static String getPartner() {
		return partner;
	}
	/**
	 * @param partner the partner to set
	 */
	public static void setPartner(String partner) {
		AliPayInfo.partner = partner;
	}
	/**
	 * @return the seller
	 */
	public static String getSeller() {
		return seller;
	}
	/**
	 * @param seller the seller to set
	 */
	public static void setSeller(String seller) {
		AliPayInfo.seller = seller;
	}
	/**
	 * @return the rsa_private
	 */
	public static String getRsa_private() {
		return rsa_private;
	}
	/**
	 * @param rsa_private the rsa_private to set
	 */
	public static void setRsa_private(String rsa_private) {
		AliPayInfo.rsa_private = rsa_private;
	}
	/**
	 * @return the rsa_alipay_public
	 */
	public static String getRsa_alipay_public() {
		return rsa_alipay_public;
	}
	/**
	 * @param rsa_alipay_public the rsa_alipay_public to set
	 */
	public static void setRsa_alipay_public(String rsa_alipay_public) {
		AliPayInfo.rsa_alipay_public = rsa_alipay_public;
	}
	
}
