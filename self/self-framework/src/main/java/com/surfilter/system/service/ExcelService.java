/**
 * Project Name:smcs
 * File Name:ExcelService.java
 * Package Name:com.surfilter.system.service
 * Date:2013-10-25下午6:36:19
 *
*/

package com.surfilter.system.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.BackWriteExcel;
import com.surfilter.framework.filehandle.excel.ExcelUtil;
import com.surfilter.framework.filehandle.excel.IDCExcelView;
import com.surfilter.framework.filehandle.excel.IDCExcelViewBack;
import com.surfilter.framework.page.ReflectUtil;
import com.surfilter.system.model.User;

/**
 * excel 导出导入
 * ClassName: ExcelService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2013-10-26 上午10:31:41 <br/>
 *
 * @author wangguohong
 * @version @param <T>
 * @since JDK 1.6
 */
@Service
@Transactional
public class ExcelService<T> {
	
	/**
	 * 注入msgService.
	 */
	@Autowired
	private MsgService msgService;
	/**
	 * 注入msgService.
	 */
	@Autowired
	private MsgRelService msgRelService;
	
	/**
	 * exportExcel:(导出excel). <br/>
	 * @author wangguohong
	 * @param list
	 * @param properties
	 * @param headers
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView exportExcel(T t,List<T> list,String[] properties,String[] headers,User user){
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = Arrays.asList(headers); //表格head
		dataMap.put(ExcelUtil.EXCEL_PRO_TITLES, titles);
		dataMap.put(ExcelUtil.EXCEL_PRO_VALUES, list);//数据集合
		List<String> listproperties = Arrays.asList(properties); //显示的属性集合 
		dataMap.put(ExcelUtil.EXCEL_PRO_PROERTIES, listproperties);
		ModelAndView mv = null;
		try {
			IDCExcelView<T> idcw = new IDCExcelView<T>();
			Field f = ReflectUtil.getAllClassField(t.getClass(), "isback");
			f.setAccessible(true);
			//f.get(t);
			boolean isback = f.getBoolean(t);
			
			if(isback){
				//如果超过立即下载记录数量阀值是转成后台下载
				mv = exprotBack(t,list, properties, headers,user);
			}else{
				mv = new ModelAndView(idcw,dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * exprotBack:(后台导出保存当服务器上提供下载). <br/>
	 *
	 * @author wangguohong
	 * @param list
	 * @param properties
	 * @param headers
	 * @return
	 * @since JDK 1.6
	 */
	public ModelAndView exprotBack(T t,List<T> list,String[] properties,String[] headers,User user){
		IDCExcelViewBack<T> idcwb = new IDCExcelViewBack<T>();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = Arrays.asList(headers); //表格head
		dataMap.put(ExcelUtil.EXCEL_PRO_TITLES, titles);
		dataMap.put(ExcelUtil.EXCEL_PRO_VALUES, list);//数据集合
		List<String> listproperties = Arrays.asList(properties); //显示的属性集合 
		dataMap.put(ExcelUtil.EXCEL_PRO_PROERTIES, listproperties);
		String fileName = getPropertyValue(t,"fileName");
		String batchnum = getPropertyValue(t,"batchnum");
		String batcsize = getPropertyValue(t,"batchsize");
		String batchEnd = getPropertyValue(t,"batchEnd");
		dataMap.put(FrameworkGlobal.EXCEL_NAME, fileName);
		dataMap.put(FrameworkGlobal.BATCHNUM, batchnum);
		dataMap.put(FrameworkGlobal.BATCSIZE, batcsize);
		dataMap.put(FrameworkGlobal.BATCHEND, batchEnd);
		String datapth = FileUtil.getResouseValue(FrameworkGlobal.DATA_PATH);
		File f = new File(datapth);
		if(!f.exists()){
			try {
				f.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		String filepath = datapth+File.separator+fileName;
		dataMap.put(FrameworkGlobal.FULL_EXCEL_PATH, filepath);
		ModelAndView mv = new ModelAndView(idcwb,dataMap);
		
		//获取导出类型
		int exportType = 1;
		try {
			Field exportTypef = ReflectUtil.getAllClassField(t.getClass(), "exportType");
			
			exportTypef.setAccessible(true);
			exportType= exportTypef.getInt(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataMap.put(ExcelUtil.EXPORT_TYPE, exportType);
		
		Thread td = new Thread(new BackWriteExcel<T>(dataMap,msgService,msgRelService,user));
		td.start();
		//System.out.println("开始返回数据");
		return mv;
	}
	
	/**
	 * getFileName:(获取文件名). <br/>
	 * @author wangguohong
	 * @param list
	 * @return
	 * @since JDK 1.6
	 */
	public String getPropertyValue(T t,String propertyName){
		String fileName=null;
		try {
//			Method m = t.getClass().getMethod("getFileName", t.getClass());
//			m.setAccessible(true);
//			fileName = m.invoke(t).toString();
			Field f = ReflectUtil.getAllClassField(t.getClass(), propertyName);
			f.setAccessible(true);
			Object o = f.get(t);
			fileName = o.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return fileName;
	}
}

