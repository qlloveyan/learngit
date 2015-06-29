package com.surfilter.fe.utils;

import java.util.ArrayList;
import java.util.List;

import com.surfilter.fe.model.Medic;

public class DataFactory {

	private static DataFactory df = new DataFactory();
	
	private DataFactory(){}
	
	public static DataFactory getInstance(){
		return df;
	}
	
	public static List<String> getData(){
		List<String> sourceStr = new ArrayList<String>();
		String data1  = JsonUtil.writeObject(new Medic(1,"银花 感冒 颗粒","功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
        String data2  = JsonUtil.writeObject(new Medic(2,"感冒  止咳糖浆","功能主治：感冒止咳糖浆,解表清热，止咳化痰。"));
        String data3  = JsonUtil.writeObject(new Medic(3,"感冒灵颗粒","功能主治：解热镇痛。头痛 ,清热。"));
        String data4  = JsonUtil.writeObject(new Medic(4,"感冒  灵胶囊","功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
        String data5  = JsonUtil.writeObject(new Medic(5,"仁和 感冒 颗粒","功能主治：疏风清热，宣肺止咳,解表清热，止咳化痰。"));
        sourceStr.add(data1);
        sourceStr.add(data2);
        sourceStr.add(data3);
        sourceStr.add(data4);
        sourceStr.add(data5);
        return sourceStr;
	}
}
