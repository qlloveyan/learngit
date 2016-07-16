/**
 * Project Name:smcs<br>
 * File Name:DataTemplate.java<br>
 * Package Name:com.surfilter.framework.filehandle.model<br>
 * Date:2014年01月21日  下午03:03:49<br>
 *
*/
package com.surfilter.framework.filehandle.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * 数据导入模板实体
 * ClassName:DataTemplate.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月21日  下午03:03:49<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DataTemplate extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *主键
	 */
	private Long id;
	/**
	 *模板中文名称
	 */
	private String templateCnName;
	/**
	 *模板路径
	 */
	private String templatePath;
	/**
	 *业务资源编号
	 */
	private String resourceCode;
	/**
	 *模板文件名称
	 */
	private String templateFileName;
	


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
	 *设置 :模板中文名称
	 */
	public void setTemplateCnName(String templateCnName) {
		this.templateCnName = templateCnName;
	}

	/**	 
	 *获取 :模板中文名称
	 */
	public String getTemplateCnName() {
		return this.templateCnName;
	}

	/**	 
	 *设置 :模板路径
	 */
	public void setTemplagePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/**	 
	 *获取 :模板路径
	 */
	public String getTemplagePath() {
		return this.templatePath;
	}

	/**	 
	 *设置 :业务资源编号
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**	 
	 *获取 :业务资源编号
	 */
	public String getResourceCode() {
		return this.resourceCode;
	}

	/**	 
	 *设置 :模板文件名称
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	/**	 
	 *获取 :模板文件名称
	 */
	public String getTemplateFileName() {
		return this.templateFileName;
	}

}
