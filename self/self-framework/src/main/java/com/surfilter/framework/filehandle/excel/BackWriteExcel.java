/**
 * Project Name:lichen
 * File Name:BackWriteExcel.java
 * Package Name:com.surfilter.framework.filehandle.excel
 * Date:2014-1-27上午11:48:21
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.csv.CSVHandler;
import com.surfilter.system.model.Msg;
import com.surfilter.system.model.MsgRel;
import com.surfilter.system.model.User;
import com.surfilter.system.service.MsgRelService;
import com.surfilter.system.service.MsgService;

/**
 * ClassName:BackWriteExcel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-27 上午11:48:21 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

public class BackWriteExcel<T> implements Runnable{

	private MsgService msgService;
	private MsgRelService msgRelService;
	private Map<String,Object> dataMap;
	private HttpServletRequest request;
	private User user;
	
	
	
	/**
	 * Creates a new instance of BackWriteExcel.
	 *
	 * @param dataMap
	 */
	
	public BackWriteExcel(Map<String, Object> dataMap,MsgService msgService,MsgRelService msgRelService,User user) {
		this.dataMap = dataMap;
		this.msgService = msgService;
		this.user = user;
		this.msgRelService = msgRelService;
	}

	/**
	 * dataMap.
	 *
	 * @return  the dataMap
	 * @since   JDK 1.6
	 */
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * dataMap.
	 *
	 * @param   dataMap    the dataMap to set
	 * @since   JDK 1.6
	 */
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public void run() {
		try {
			//Thread.sleep(10000);
			writeExcel();
			//System.out.println("后台开始下载");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
	}
	
	/**
	 * 
	 * writeExcel:(写入数据到excel). <br/>
	 *
	 * @author wangguohong
	 * @since JDK 1.6
	 */
	public void writeExcel(){
		try {
			String suffix = "";
			//获取导出类型
			int exportType = Integer.parseInt(dataMap.get(ExcelUtil.EXPORT_TYPE).toString());
			switch (exportType) {
			case 1:
				suffix = ExcelUtil.FILE_SUFFIX;
				ExcelUtil<T> e = new ExcelUtil<T>();
				e.writeExcel(dataMap);
				break;
			case 2:
				suffix = CSVHandler.FILE_CSV_SUFFIX;
				CSVHandler<T> cvshandler = new CSVHandler<T>();
				cvshandler.writeCsv(dataMap, msgService, msgRelService, user);
				break;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

