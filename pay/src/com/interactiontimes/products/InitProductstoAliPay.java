/**
 * 
 */
package com.interactiontimes.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Podevor
 * 
 */
public class InitProductstoAliPay {
	private final static int PRODUCTSCOUNT = 9;
	private final static Double PERPRICE = 1.0;
	private static Map<Integer, Map<String,String>> productsMap = new HashMap<Integer, Map<String,String>>();
	private static List<Integer> productsIdList = null;
	private static List<String> productsNameList = null;
	private static List<String> productsDecriptionList = null;
	private static List<Integer> productPricePeerValue = null;
	private static void initProductsId() {
		productsIdList = new ArrayList<Integer>();
		productsIdList.add(1001);
		productsIdList.add(1002);
		productsIdList.add(501);
		productsIdList.add(502);
		productsIdList.add(503);
		productsIdList.add(504);
		productsIdList.add(1);
		productsIdList.add(2);
		productsIdList.add(3);
	}
	private static void initProductsName() {
		productsNameList = new ArrayList<String>();
		productsNameList.add("测试赛券");
		productsNameList.add("比赛券");
		productsNameList.add("顺风铃");
		productsNameList.add("降雨披");
		productsNameList.add("避雷针");
		productsNameList.add("碎冰爪");
		productsNameList.add("常青草");
		productsNameList.add("灵芝膏");
		productsNameList.add("神仙水");
	}
	private static void initProductPricePeerValue() {
		productPricePeerValue = new ArrayList<Integer>();
		productPricePeerValue.add(1);//测试赛券1/¥
		productPricePeerValue.add(1);//比赛券1/¥
		productPricePeerValue.add(15);//顺风铃15/¥
		productPricePeerValue.add(15);//降雨披15/¥
		productPricePeerValue.add(10);//避雷针10/¥
		productPricePeerValue.add(5);//碎冰抓5/¥
		productPricePeerValue.add(10);//常青草10/¥
		productPricePeerValue.add(15);//灵芝膏15/¥
		productPricePeerValue.add(10);//神仙水10/¥
	}
	private static void initProductsDescription() {
		productsDecriptionList = new ArrayList<String>();
		productsDecriptionList.add("价格:1个/元；功能:能在快速游戏下进行比赛，每天赠送3个.");//测试赛券1/¥
		productsDecriptionList.add("价格:1个/元；功能:能在开始游戏下进行比赛，每天赠送3个.");//比赛券1/¥
		productsDecriptionList.add("价格:15个/元；功能:在比赛过程中有风时，速度提升0.03倍.");//顺风铃15/¥;这里不要写成%形式的
		productsDecriptionList.add("价格:15个/元；功能:在比赛过程中有雨时，速度提升0.03倍.");//降雨披15/¥
		productsDecriptionList.add("价格:10个/元；功能:在比赛过程中避免被闪电击中.");//避雷针10/¥
		productsDecriptionList.add("价格:5个/元；功能:在比赛过程中有冰时，直接获得比赛胜利.");//碎冰抓5/¥
		productsDecriptionList.add("价格:10个/元；功能:2秒内速度提升0.02倍.");//常青草10/¥
		productsDecriptionList.add("价格:15个/元；功能:2秒内速度提升0.03倍.");//灵芝膏15/¥
		productsDecriptionList.add("价格:10个/元；功能:2秒内速度提升0.04倍.");//神仙水10/¥
	}
	public static void initProducts() {
		initProductsId();
		initProductsName();
		initProductPricePeerValue();
		initProductsDescription();
		for (int productIndex = 0; productIndex < PRODUCTSCOUNT; productIndex++) {
			Map<String,String> productInfoMap = new HashMap<String,String>();
			productInfoMap.put("product_name", productsNameList.get(productIndex));
			productInfoMap.put("product_peervalue", productPricePeerValue.get(productIndex)+"");
			productInfoMap.put("product_perprice", PERPRICE+"");
			productInfoMap.put("product_desc", productsDecriptionList.get(productIndex));
			productsMap.put(productsIdList.get(productIndex),productInfoMap);
		}
	}
	public static Map<String,String> getProductMapInfoById(int productId){
		return productsMap.get(productId);
	}
}
