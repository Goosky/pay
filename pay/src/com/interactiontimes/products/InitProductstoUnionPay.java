/**
 * 
 */
package com.interactiontimes.products;

import java.util.*;

/**
 * @author Podevor
 *
 */
public class InitProductstoUnionPay {
	private static 
	Map<Integer, Integer> productsPriceMap = new HashMap<Integer, Integer>();
	private static 
	Map<Integer, String> productsNameMap = new HashMap<Integer, String>();
	private static 
	Map<Integer, String> productsPriceDescMap = new HashMap<Integer, String>();
	public static void initProducts(){
		//init price
		productsPriceMap.put(1001,100);//unit minute
		productsPriceMap.put(1002,100);
		productsPriceMap.put(501,100);
		productsPriceMap.put(502,100);
		productsPriceMap.put(503,100);
		productsPriceMap.put(504,100);
		productsPriceMap.put(1, 100);
		productsPriceMap.put(2, 100);
		productsPriceMap.put(3, 100);
		//init product name
		productsNameMap.put(1001,"测试赛券");
		productsNameMap.put(1002,"比赛券");
		productsNameMap.put(501,"顺风铃");
		productsNameMap.put(502,"降雨披");
		productsNameMap.put(503,"避雷针");
		productsNameMap.put(504,"碎冰爪");
		productsNameMap.put(1,"常青草");
		productsNameMap.put(2,"灵芝膏");
		productsNameMap.put(3,"神仙水");
		//price desc
		productsPriceDescMap.put(1001,"价格:1个/元；功能:能在快速游戏下进行比赛，每天赠送3个.");
		productsPriceDescMap.put(1002,"价格:1个/元；功能:能在开始游戏下进行比赛，每天赠送3个.");
		productsPriceDescMap.put(501,"价格:15个/元；功能:在比赛过程中有风时，速度提升0.03倍.");
		productsPriceDescMap.put(502,"价格:15个/元；功能:在比赛过程中有雨时，速度提升0.03倍.");
		productsPriceDescMap.put(503,"价格:10个/元；功能:在比赛过程中避免被闪电击中.");
		productsPriceDescMap.put(504,"价格:5个/元；功能:在比赛过程中有冰时，直接获得比赛胜利.");
		productsPriceDescMap.put(1,"价格:10个/元；功能:2秒内速度提升0.02倍.");
		productsPriceDescMap.put(2,"价格:15个/元；功能:2秒内速度提升0.03倍.");
		productsPriceDescMap.put(3,"价格:10个/元；功能:2秒内速度提升0.04倍.");
	}
	public static Integer getItemPriceById(int itemId){
		return productsPriceMap.get(itemId);
	}
	public static String getItemNameById(int itemId){
		return productsNameMap.get(itemId);
	}	
	public static String getItemDescById(int itemId){
		return productsPriceDescMap.get(itemId);
	}
}
