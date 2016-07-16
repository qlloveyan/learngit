package com.surfilter.framework.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运算代码转换工具类
 *
 * @author yefeng 2010-04-13
 *
 */

public class OperationUtil {
	private static Logger log = LoggerFactory.getLogger(OperationUtil.class);

	/**
	 * 计算出给定id集合的运算结果并返回
	 *
	 * @param ids
	 * @return
	 */
	public static Long getOperationResult(Collection<Number> ids) {
		Long result = 0L;
		for (Number id : ids) {
			int tempId = id.intValue();
			long temp = 1;
			for (int i = 0; i < tempId; i++) {
				if (i == 0) {
					temp = 1;
					continue;
				}
				temp *= 2;
			}
			result += temp;
		}
		return result;
	}

	/**
	 * 从运算结果获取id集合
	 *
	 * @param result
	 * @return
	 */
	public static List<Number> getIdsFromOperationResult(Long result) {
		List<Number> ids = new ArrayList<Number>();
		String str = Long.toBinaryString(result);
		char[] bits = reverse(str.toCharArray());
		for (int i = 0; i < bits.length; i++) {
			if (bits[i] == '1') {
				ids.add(i + 1);
			}
		}
		return ids;
	}

	/**
	 * 数据库中运算数字解析，将数据库中存储的数据的和解析成2的N次方的数组返回。
	 *
	 * @param number
	 * @return
	 */
	public static List<Long> dataForShow(Long number) {
		DecimalFormat df = new DecimalFormat("0.00");
		String str = StringUtils.convertNumber(number);
		char[] ch = str.toCharArray();
		List<Long> nu = new ArrayList<Long>();
		for (int i = 0; i < ch.length; i++) {
			int j = ch.length - 1 - i;
			if (ch[i] == '1') {
				Double a = Math.pow((double) 2, (double) j);
				try {
					nu.add(Long.parseLong(df.format(a).substring(0,
							df.format(a).indexOf("."))));
				} catch (Exception e) {
					log.error(
							"字符串转换成长整形出错---" + e.getMessage());
					continue;
				}

			}
		}
		return nu;
	}

	/**
	 * 将字符数组反向排列
	 *
	 * @param bits
	 * @return
	 */
	private static char[] reverse(char[] bits) {
		char[] newBits = new char[bits.length];
		for (int i = 0; i < bits.length; i++) {
			newBits[bits.length - 1 - i] = bits[i];
		}
		return newBits;
	}


	public static Long statisFrom(List<Long> logicIds, Long logicId) {
		Long result =0L;
		for(Long id:logicIds){
			if(id<0)
				continue;
			List<Long> ids = OperationUtil.dataForShow(id);
			if(ids.contains(logicId))
				result++;
		}
		return result;
	}


	// public static void main(String[] args) {
	// int[] numbers = { 1, 5, 8 };
	// List ids = new ArrayList();
	// for (int n : numbers) {
	// ids.add(n);
	// }
	// long result = getOperationResult(ids);
	// System.out.println(result);
	//
	// List data = getIdsFromOperationResult(result);
	// System.out.println(data);
	//
	// // List data = StringUtil.dataForShow(result);
	// // System.out.println(data);
	//
	// }

//	public  static  void main(String[] args){
//		List<Long> result = dataForShow(Long.parseLong("8589934592"));
//		for(Long value :result ){
//			System.out.println(value);
//		}
//	}

}
