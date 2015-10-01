package com.ql.basepro.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {

	// 验证手机号码的正则表达式
	public final static String PHONE_CHECK = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	// 验证数字
	public final static String NUMBER_CHECK = "^[0-9]*$";
	
	//验证价格
	public final static String PRICE_CHECK = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";

	// 验证身份证号码
	public final static String IDENTITYCARD_CHECK = "^\\d{6}(18|19|20)?\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X)$";
	
	//验证坐标经纬度
	public final static String LNG_LAT_CHECK = "";

	public static boolean validate(String regex, String content) {
		if (content == null) {
			return false;
		} else {
			Pattern pattern = Pattern.compile(regex);
			return pattern.matcher(content).find();
		}
	}

	/*
	 * 根据〖中华人民共和国国家标准 GB
	 * 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成
	 * 。排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * 地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
	 * 出生日期码表示编码对象出生的年、月、日，其中年份用四位数字表示，年、月、日之间不用分隔符。
	 * 顺序码表示同一地址码所标识的区域范围内，对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性，偶数分给女性。
	 * 校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
	 * 
	 * 出生日期计算方法。 15位的身份证编码首先把出生年扩展为4位，简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
	 * 2000年后出生的肯定都是18位的了没有这个烦恼，至于1800年前出生的,那啥那时应该还没身份证号这个东东，⊙﹏⊙b汗... 下面是正则表达式:
	 * 出生日期1800-2099 (18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])
	 * 身份证正则表达式
	 * /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i
	 * 15位校验规则 6位地址编码+6位出生日期+3位顺序号 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
	 * 
	 * 校验位规则 公式:∑(ai×Wi)(mod 11)……………………………………(1) 公式(1)中：
	 * i----表示号码字符从由至左包括校验码在内的位置序号； ai----表示第i位置上的号码字符值；
	 * Wi----示第i位置上的加权因子，其数值依据公式Wi=2^(n-1）(mod 11)计算得出。 i 18 17 16 15 14 13 12
	 * 11 10 9 8 7 6 5 4 3 2 1 Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
	 */
	// 身份证号合法性验证
	// 支持15位和18位身份证号
	// 支持地址编码、出生日期、校验位验证
	// 身份证号码验证：start

	public static boolean IDCardValidate(String IDStr) {
        boolean tipInfo = true;// 记录错误信息  
        try {
        	String Ai = "";  
            // 判断号码的长度 15位或18位  
            if (IDStr.length() != 15 && IDStr.length() != 18) {  
                tipInfo = false;  
                return tipInfo;  
            }  
               
       
            // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字  
            if (IDStr.length() == 18) {  
                Ai = IDStr.substring(0, 17);  
            } else if (IDStr.length() == 15) {  
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);  
            }  
            if (isNumeric(Ai) == false) {  
                tipInfo = false;  
                return tipInfo;  
            }  
               
       
            // 判断出生年月是否有效   
            String strYear = Ai.substring(6, 10);// 年份  
            String strMonth = Ai.substring(10, 12);// 月份  
            String strDay = Ai.substring(12, 14);// 日期  
            if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {  
                tipInfo = false;  
                return tipInfo;  
            }  
            GregorianCalendar gc = new GregorianCalendar();  
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");  
            try {  
                if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 
                        || (gc.getTime().getTime() - s.parse(  
                                strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {  
                    tipInfo = false;  
                    return tipInfo;  
                }  
            } catch (NumberFormatException e) {  
                e.printStackTrace();  
            } catch (java.text.ParseException e) {  
                e.printStackTrace();  
            }  
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {  
                tipInfo = false;  
                return tipInfo;  
            }  
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {  
                tipInfo = false;  
                return tipInfo;  
            }  
               
       
            // 判断地区码是否有效   
            Hashtable<String,String> areacode = GetAreaCode();  
            //如果身份证前两位的地区码不在Hashtable，则地区码有误  
            if (areacode.get(Ai.substring(0, 2)) == null) {  
                tipInfo = false;  
                return tipInfo;  
            }  
               
            if(isVarifyCode(Ai,IDStr)==false){  
                tipInfo = false;  
                return tipInfo;  
            }  
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("身份证号码验证失败!");
		}
        return tipInfo;  
    }  
       
       
     /* 
      * 判断第18位校验码是否正确 
     * 第18位校验码的计算方式：  
        　　1. 对前17位数字本体码加权求和  
        　　公式为：S = Sum(Ai * Wi), i = 0, ... , 16  
        　　其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2  
        　　2. 用11对计算结果取模  
        　　Y = mod(S, 11)  
        　　3. 根据模的值得到对应的校验码  
        　　对应关系为：  
        　　 Y值：     0  1  2  3  4  5  6  7  8  9  10  
        　　校验码： 1  0  X  9  8  7  6  5  4  3   2 
     */  
    private static boolean isVarifyCode(String Ai,String IDStr) {  
         String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4","3", "2" };  
         String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };  
         int sum = 0;  
         for (int i = 0; i < 17; i++) {  
            sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);  
         }  
         int modValue = sum % 11;  
         String strVerifyCode = VarifyCode[modValue];  
         Ai = Ai + strVerifyCode;  
         if (IDStr.length() == 18) {  
             if (Ai.equals(IDStr) == false) {  
                 return false;  
                   
             }  
         }   
         return true;  
    }  
       
   
    /** 
     * 将所有地址编码保存在一个Hashtable中     
     * @return Hashtable 对象 
     */  
      
    private static Hashtable<String,String> GetAreaCode() {  
        Hashtable<String,String> hashtable = new Hashtable<String,String>();  
        hashtable.put("11", "北京");  
        hashtable.put("12", "天津");  
        hashtable.put("13", "河北");  
        hashtable.put("14", "山西");  
        hashtable.put("15", "内蒙古");  
        hashtable.put("21", "辽宁");  
        hashtable.put("22", "吉林");  
        hashtable.put("23", "黑龙江");  
        hashtable.put("31", "上海");  
        hashtable.put("32", "江苏");  
        hashtable.put("33", "浙江");  
        hashtable.put("34", "安徽");  
        hashtable.put("35", "福建");  
        hashtable.put("36", "江西");  
        hashtable.put("37", "山东");  
        hashtable.put("41", "河南");  
        hashtable.put("42", "湖北");  
        hashtable.put("43", "湖南");  
        hashtable.put("44", "广东");  
        hashtable.put("45", "广西");  
        hashtable.put("46", "海南");  
        hashtable.put("50", "重庆");  
        hashtable.put("51", "四川");  
        hashtable.put("52", "贵州");  
        hashtable.put("53", "云南");  
        hashtable.put("54", "西藏");  
        hashtable.put("61", "陕西");  
        hashtable.put("62", "甘肃");  
        hashtable.put("63", "青海");  
        hashtable.put("64", "宁夏");  
        hashtable.put("65", "新疆");  
        hashtable.put("71", "台湾");  
        hashtable.put("81", "香港");  
        hashtable.put("82", "澳门");  
        hashtable.put("91", "国外");  
        return hashtable;  
    }  
   
    /** 
     * 判断字符串是否为数字,0-9重复0次或者多次    
     * @param strnum 
     * @return 
     */  
    private static boolean isNumeric(String strnum) {  
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(strnum);  
        if (isNum.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
   
    /** 
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天 
     *  
     * @param string 
     * @return 
     */ 
    public static boolean isDate(String strDate) {  
       
        Pattern pattern = Pattern  
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");  
        Matcher m = pattern.matcher(strDate);  
        if (m.matches()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  

	// 身份证号码验证：end

	public static void main(String[] args) {
//		System.out.println(ValidateUtil.validate(NUMBER_CHECK, "1112212121212"));
//		System.out.println(ValidateUtil.validate(NUMBER_CHECK, "AWDAWD"));
//		System.out.println(ValidateUtil.validate(PHONE_CHECK, "-11"));
		System.out.println(ValidateUtil.validate(PRICE_CHECK, "-11"));
		System.out.println(ValidateUtil.validate(PRICE_CHECK, "11"));
		System.out.println(ValidateUtil.validate(PRICE_CHECK, "11.00"));
//
//		System.out.println(new Date().getTime());
//		System.out.println(IDCardValidate("421022199103023917"));
//		System.out.println(IDCardValidate("42900520041013180X"));
//		System.out.println(IDCardValidate("429005200410131801"));
//		System.out.println(IDCardValidate("42102219900302"));
//		System.out.println(IDCardValidate("421022199003020000"));
	}
}
