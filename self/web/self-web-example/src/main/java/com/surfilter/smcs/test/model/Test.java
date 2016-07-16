/**
 * Project Name:smcs<br>
 * File Name:Test.java<br>
 * Package Name:com.surfilter.smcs.test.model<br>
 * Date:2016年07月16日  下午07:45:33<br>
 *
*/
package com.surfilter.smcs.test.model;
import java.io.Serializable;
import com.surfilter.framework.filehandle.excel.ExcelModel;


/**
 * ClassName:Test.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2016年07月16日  下午07:45:33<br>
 * 
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Test extends ExcelModel implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private String id;
	/**
	 *名称
	 */
	private String name;
	


	/**	 
	 *设置 :
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**	 
	 *获取 :
	 */
	public String getId() {
		return this.id;
	}

	/**	 
	 *设置 :名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**	 
	 *获取 :名称
	 */
	public String getName() {
		return this.name;
	}

}
