/**
 * Project Name:smcs
 * File Name:SystemConstants.java
 * Package Name:com.smcs.core.utils
 * Date:2013-10-14下午5:28:13
 *
*/

package com.surfilter.system;
/**
 * ClassName:Constants <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO 定义系统常量. <br/>
 * Date:     2013-10-14 下午5:28:13 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

public class SystemConstants {
	
	/*****************字典表类型常量定义start*****************/
	/**
	 * 机房性质
	 */
	public static final String DICTIONARY_TYPE_HOUSETYPE = "house_type";
	
	/**
	 * 区域（省，市，县）
	 */
	public static final String DICTIONARY_TYPE_AREATYPE = "area_type";
	
	/**
	 * 系统模块类型
	 */
	public static final String DICTIONARY_TYPE_SYSMODULE = "sysModule_type";
	/*****************字典表类型常量定义end*******************/
	
	/**
	 * 表示没有父模块
	 */
	public static final String NOT_HAS_PARENT_MODULE = "无";
	
	/**
	 * 无父模块的时候id为0
	 */
	public static final String NOT_HAS_PARENT_MODULE_ID = "0";
	
	/**
	 * 树拖拽动作 向上移动
	 */
	public static final String TREE_POINT_APPEND="append";
	
	/**
	 * 向下移动
	 */
	public static final String TREE_POINT_TOP="top";
	
	/**
	 * 移动到新的节点下
	 */
	public static final String TREE_POINT_BOTTOM="bottom";
	
	/**
	 * 分页类型为 查询
	 */
	public static final String GET_PAGE_TYPE_SEARCH = "search";
	
	/**
	 * 分页类型为 导出数据
	 */
	public static final String GET_PAGE_TYPE_EXPORT = "export";
	
	/**
	 * IP地址使用方式
	 */
	public static final String GET_IP_USE_TYPE = "USE_TYPE";
	
	/**
	 * IP分配类型
	 */
	public static final String GET_IP_ASSIGN_TYPE = "ASSIGN_TYPE";
	
	/**
	 * IP地址使用方式export属性
	 */
	public static final String [] properties= {"beginIp","endIp","useTypeName","userName","assignTimeStr","assignTypeName","houseInfoIdName","fromIpSegIdName"};
	
	/**
	 * 菜单
	 */
	public static final String FUNC_TYPE_MENU = "0";
	
	/**
	 * 资源
	 */
	public static final String FUNC_TYPE_URL = "1";
	
	/**
	 * 密码错误次数参数
	 */
	public static final String PSW_ERROR_COUNT = "PASS_ERROR_COUNT";
	/**
	 * 密码错误时间范围
	 */
	public static final String PSW_ERROR_TIME = "PSW_ERROR_TIME";
	/**
	 * 自动解锁时间范围
	 */
	public static final String AUTO_UNLOCK = "AUTO_UNLOCK";
	/**
	 * 密码过期时间
	 */
	public static final String PSW_VALIDATE_DAY = "PASS_PAST_DATE";
	
	/**
	 * 系统单位类型
	 */
	public static int UNIT_TYPE_TMB = 2;//管局
	
	public static int UNIT_TYPE_SW = 3;//涉网
	
	public static int UNIT_TYPE_IDC_ISP = 4;//接入企业（ISP/IDC）
	
	public static int UNIT_TYPE_JC = 5;//基础运营商
	
	public static int UNIT_TYPE_ICP = 6;//增值电信企业（ICP）
	
	public static int UNIT_TYPE_FZX = 7;//分中心
	
	public static int UNIT_TYPE_YMZCS = 8;//域名注册商
	
//	/**
//	 *  不是管理者用户
//	 */
//	public static final String IS_MANGER_0="0";
//	/**
//	 * 是管理者用户
//	 */
//	public static final String IS_MANGER_1="1";
//	
//	/**
//	 * 超级管理员
//	 */
//	public static final String USER_TYPE_0="0";
//	/**
//	 * 普通管理员
//	 */
//	public static final String USER_TYPE_1="1";
//	/**
//	 * 普通用户
//	 */
//	public static final String USER_TYPE_2="2";
//	
//	/**
//	 * 管局侧用户
//	 */
//	public static final String USER_YHLX_0="0";
//	/**
//	 * 企业测用户
//	 */
//	public static final String USER_YHLX_1="1";
	
}

