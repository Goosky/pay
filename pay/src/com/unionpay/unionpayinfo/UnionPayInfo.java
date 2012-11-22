package com.unionpay.unionpayinfo;


import java.text.*;
import java.util.*;

/**
 * @author Podevor
 * 
 */
public class UnionPayInfo {
	/*
	 * unionpay need values from merchant
	 */
	// merchantName
	private static String merchantName = null;
	// merchantID
	private static String merchantId = null;
	// merchant password
	private static String merchantPassword = null;
	//order length
	private static int orderIdLength = 0;
	// orderId
	private static String merchantOrderId = null;
	// order transTimeOut
	private static String transTimeout = null;
	// orderTime
	private static String merchantOrderTime = null;
	// orderamt:unit:mintute
	private static String merchantOrderAmt = null;
	// orderdescription
	private static String merchantOrderDesc = null;
	// backEndUrl
	private static String backEndUrl = null;
	// SRC
	private static String SourceSign = null;
	// xmlSign
	private static String xmlSign = null;
	// merchant public key
	private static String merchantPublicCer = null;
	//merchant public key path
	private static String merchantPublicCerPath = null;
	// merchant private key alias
	private static String merchantPrivateKeyAlias = null;
	// merchant private key path
	private static String privateKeyPath = null;
	// search type
	private static String type = null;
	// order cupsqid
	private static String cupsQid = null;
	// log tag
	private static String tag = "Sys";
	/*
	 * union pay need value to union server
	 */
	// union server url
	private static String unionServerUrl = null;
	// unin server time out
	private static String unionServerTimeOut = null;

	
	/**
	 * @return the merchantPublicCerPath
	 */
	public static String getMerchantPublicCerPath() {
		return merchantPublicCerPath;
	}

	/**
	 * @param merchantPublicCerPath the merchantPublicCerPath to set
	 */
	public static void setMerchantPublicCerPath(String merchantPublicCerPath) {
		UnionPayInfo.merchantPublicCerPath = merchantPublicCerPath;
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
		UnionPayInfo.orderIdLength = orderIdLength;
	}

	public static String getMerchantOrderId() {
		return merchantOrderId;
	}

	public static void setMerchantOrderId(String merchantOrderId) {
		UnionPayInfo.merchantOrderId = merchantOrderId;
	}

	/**
	 * @return the unionServerUrl
	 */
	public static String getUnionServerUrl() {
		return unionServerUrl;
	}

	/**
	 * @param unionServerUrl
	 *            the unionServerUrl to set
	 */
	public static void setUnionServerUrl(String unionServerUrl) {
		UnionPayInfo.unionServerUrl = unionServerUrl;
	}

	/**
	 * @return the unionServerTimeOut
	 */
	public static String getUnionServerTimeOut() {
		return unionServerTimeOut;
	}

	/**
	 * @param unionServerTimeOut
	 *            the unionServerTimeOut to set
	 */
	public static void setUnionServerTimeOut(String unionServerTimeOut) {
		UnionPayInfo.unionServerTimeOut = unionServerTimeOut;
	}

	/**
	 * @return the transTimeout
	 */
	public static String getTransTimeout() {
		return transTimeout;
	}

	/**
	 * @param transTimeout
	 *            the transTimeout to set
	 */
	public static void setTransTimeout(String transTimeout) {
		UnionPayInfo.transTimeout = transTimeout;
	}

	/**
	 * @return the merchantPassword
	 */
	public static String getMerchantPassword() {
		return merchantPassword;
	}

	/**
	 * @param merchantPassword
	 *            the merchantPassword to set
	 */
	public static void setMerchantPassword(String merchantPassword) {
		UnionPayInfo.merchantPassword = merchantPassword;
	}

	/**
	 * @return the merchantName
	 */
	public static String getMerchantName() {
		return merchantName;
	}

	/**
	 * @param merchantName
	 *            the merchantName to set
	 */
	public static void setMerchantName(String merchantName) {
		UnionPayInfo.merchantName = merchantName;
	}

	/**
	 * @return the merchantId
	 */
	public static String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public static void setMerchantId(String merchantId) {
		UnionPayInfo.merchantId = merchantId;
	}

	/**
	 * @return the merchantOrderAmt
	 */
	public static String getMerchantOrderAmt() {
		return merchantOrderAmt;
	}

	/**
	 * @param merchantOrderAmt
	 *            the merchantOrderAmt to set
	 */
	public static void setMerchantOrderAmt(String merchantOrderAmt) {
		UnionPayInfo.merchantOrderAmt = merchantOrderAmt;
	}

	/**
	 * @return the merchantOrderDesc
	 */
	public static String getMerchantOrderDesc() {
		return merchantOrderDesc;
	}

	/**
	 * @param merchantOrderDesc
	 *            the merchantOrderDesc to set
	 */
	public static void setMerchantOrderDesc(String merchantOrderDesc) {
		UnionPayInfo.merchantOrderDesc = merchantOrderDesc;
	}

	/**
	 * @return the backEndUrl
	 */
	public static String getBackEndUrl() {
		return backEndUrl;
	}

	/**
	 * @param backEndUrl
	 *            the backEndUrl to set
	 */
	public static void setBackEndUrl(String backEndUrl) {
		UnionPayInfo.backEndUrl = backEndUrl;
	}

	/**
	 * @return the sourceSign
	 */
	public static String getSourceSign() {
		return SourceSign;
	}

	/**
	 * @param sourceSign
	 *            the sourceSign to set
	 */
	public static void setSourceSign(String sourceSign) {
		SourceSign = sourceSign;
	}

	/**
	 * @return the xmlSign
	 */
	public static String getXmlSign() {
		return xmlSign;
	}

	/**
	 * @param xmlSign
	 *            the xmlSign to set
	 */
	public static void setXmlSign(String xmlSign) {
		UnionPayInfo.xmlSign = xmlSign;
	}

	/**
	 * @return the merchantPublicCer
	 */
	public static String getMerchantPublicCer() {
		return merchantPublicCer;
	}

	/**
	 * @param merchantPublicCer
	 *            the merchantPublicCer to set
	 */
	public static void setMerchantPublicCer(String merchantPublicCer) {
		UnionPayInfo.merchantPublicCer = merchantPublicCer;
	}

	/**
	 * @return the merchantPrivateKeyAlias
	 */
	public static String getMerchantPrivateKeyAlias() {
		return merchantPrivateKeyAlias;
	}

	/**
	 * @param merchantPrivateKeyAlias
	 *            the merchantPrivateKeyAlias to set
	 */
	public static void setMerchantPrivateKeyAlias(String merchantPrivateKeyAlias) {
		UnionPayInfo.merchantPrivateKeyAlias = merchantPrivateKeyAlias;
	}

	/**
	 * @return the privateKeyPath
	 */
	public static String getPrivateKeyPath() {
		return privateKeyPath;
	}

	/**
	 * @param privateKeyPath
	 *            the privateKeyPath to set
	 */
	public static void setPrivateKeyPath(String privateKeyPath) {
		UnionPayInfo.privateKeyPath = privateKeyPath;
	}


	/**
	 * @return the merchantOrderTime
	 */
	public static String getMerchantOrderTime() {
		merchantOrderTime = new SimpleDateFormat("yyyyMMddHHmmss")
				.format(new Date());
		return merchantOrderTime;
	}



	/**
	 * @return the type
	 */
	public static String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public static void setType(String type) {
		UnionPayInfo.type = type;
	}

	/**
	 * @param cupsQid the cupsQid to set
	 */
	public static void setCupsQid(String cupsQid) {
		UnionPayInfo.cupsQid = cupsQid;
	}

	/**
	 * @return the cupsQid
	 */
	public static String getCupsQid() {
		return cupsQid;
	}

	/**
	 * @return the tag
	 */
	public static String getTag() {
		return tag;
	}
}
