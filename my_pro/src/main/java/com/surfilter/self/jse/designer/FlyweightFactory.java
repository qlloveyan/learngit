package com.surfilter.self.jse.designer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* @ClassName: FlyweightFactory  
* @Description: 享元模式
* @author quanli 
* @date 2016年3月20日 下午5:04:38  
*
 */
public class FlyweightFactory {

	//常见的享元模式为池的概念
	public static void main(String[] args) {
		DBPool dbPool = new FlyweightFactory().new DBPool();
		dbPool.init();
		
		String str1 = dbPool.getDBConnection();
		System.out.println(str1);
		String str2 = dbPool.getDBConnection();
		System.out.println(str2);
		String str3 = dbPool.getDBConnection();
		System.out.println(str3);
		String str4 = dbPool.getDBConnection();
		System.out.println(str4);
		String str5 = dbPool.getDBConnection();
		System.out.println(str5);
		String str6 = dbPool.getDBConnection();
		System.out.println(str6);
		String str7 = dbPool.getDBConnection();
		System.out.println(str7);
		String str8 = dbPool.getDBConnection();
		System.out.println(str8);
		String str9 = dbPool.getDBConnection();
		System.out.println(str9);
		String str10 = dbPool.getDBConnection();
		System.out.println(str10);
		String str11 = dbPool.getDBConnection();
		System.out.println(str11);
		
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dbPool.free(str8);
		
		str11 = dbPool.getDBConnection();
		System.out.println(str11);
		
		str8 = dbPool.getDBConnection();
		System.out.println(str8);
	}
	
	class DBPool{
		private List<String> strList = new ArrayList<String>();
		
		public void init(){
			for(int i = 0 ; i < 10 ; i++){
				strList.add("数据连接"+i);
			}
		}
		
		public String getDBConnection(){
			String str = null;
			if( strList.size() > 0){
				str = strList.get(strList.size()-1);
				strList.remove(strList.size()-1);
				return str;
			}else{
				System.out.println("数据库连接池已用完");
				return null;
			}
		}
		
		public void free(String dbConn){
			strList.add(dbConn);
		}
	}
	
	
}
