/**
 * Project Name:smcs<br>
 * File Name:CtrlMethod.java<br>
 * Package Name:com.surfilter.system.model<br>
 * Date:2015年05月20日  上午09:14:37<br>
 *
*/
package com.surfilter.system.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:CtrlMethod.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月20日  上午09:14:37<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CtrlMethod extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *功能模块
	 */
	private String module;
	/**
	 *类名
	 */
	private String className;
	/**
	 *类说明
	 */
	private String classExplain;
	/**
	 *方法名
	 */
	private String methodName;
	/**
	 *方法说明
	 */
	private String mothodExplain;
	/**
	 *参数名
	 */
	private String paramName;
	/**
	 *参数说明
	 */
	private String paramExplain;
	/**
	 *功能模块
	 */
	private String funcModule;
	/**
	 *一级菜单
	 */
	private String fiMenu;
	/**
	 *二级菜单
	 */
	private String seMenu;
	/**
	 *方法类型
	 */
	private String methodType;
	


	/**	 
	 *设置 :主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**	 
	 *获取 :主键
	 */
	public Long getId() {
		return this.id;
	}

	/**	 
	 *设置 :功能模块
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**	 
	 *获取 :功能模块
	 */
	public String getModule() {
		return this.module;
	}

	/**	 
	 *设置 :类名
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**	 
	 *获取 :类名
	 */
	public String getClassName() {
		return this.className;
	}

	/**	 
	 *设置 :类说明
	 */
	public void setClassExplain(String classExplain) {
		this.classExplain = classExplain;
	}

	/**	 
	 *获取 :类说明
	 */
	public String getClassExplain() {
		return this.classExplain;
	}

	/**	 
	 *设置 :方法名
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**	 
	 *获取 :方法名
	 */
	public String getMethodName() {
		return this.methodName;
	}

	/**	 
	 *设置 :方法说明
	 */
	public void setMothodExplain(String mothodExplain) {
		this.mothodExplain = mothodExplain;
	}

	/**	 
	 *获取 :方法说明
	 */
	public String getMothodExplain() {
		return this.mothodExplain;
	}

	/**	 
	 *设置 :参数名
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**	 
	 *获取 :参数名
	 */
	public String getParamName() {
		return this.paramName;
	}

	/**	 
	 *设置 :参数说明
	 */
	public void setParamExplain(String paramExplain) {
		this.paramExplain = paramExplain;
	}

	/**	 
	 *获取 :参数说明
	 */
	public String getParamExplain() {
		return this.paramExplain;
	}

	/**	 
	 *设置 :功能模块
	 */
	public void setFuncModule(String funcModule) {
		this.funcModule = funcModule;
	}

	/**	 
	 *获取 :功能模块
	 */
	public String getFuncModule() {
		return this.funcModule;
	}

	public String getFiMenu() {
		return fiMenu;
	}

	public void setFiMenu(String fiMenu) {
		this.fiMenu = fiMenu;
	}

	public String getSeMenu() {
		return seMenu;
	}

	public void setSeMenu(String seMenu) {
		this.seMenu = seMenu;
	}

	/**	 
	 *设置 :方法类型
	 */
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	/**	 
	 *获取 :方法类型
	 */
	public String getMethodType() {
		return this.methodType;
	}

}
