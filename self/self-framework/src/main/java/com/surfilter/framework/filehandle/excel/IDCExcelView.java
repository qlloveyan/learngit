/**
 * Project Name:smcs
 * File Name:ExcelView.java
 * Package Name:com.smcs.core.excel
 * Date:2013-9-27下午1:47:58
 *
*/

package com.surfilter.framework.filehandle.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.utils.DateUtils;

/**
 * ClassName:IDCExcelView <br/>
 * Date:     2013-9-27 下午1:47:58 <br/>
 * 
 * 例子：
 * <pre>
	public ModelAndView export2Excel(){
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("编号");
		titles.add("用户名");
		titles.add("地址");
		dataMap.put("titles", titles);
		List<User> userList = new ArrayList<User>();
		for(int i=0;i<10;i++){
			User user = new User();
			user.setId(Long.parseLong(String.valueOf(i)));
			user.setUserName("name"+i);
			user.setAddr("dizhi"+i);
			userList.add(user);
		}
		dataMap.put("values", userList);
		List properties = new ArrayList();
		properties.add("id");
		properties.add("userName");
		properties.add("addr");
		dataMap.put("properties", properties);
		IDCExcelView<User> erv = new IDCExcelView<User>();
		ModelAndView mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	</pre>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 
 * 	 
 */
public class IDCExcelView<T> extends AbstractExcelView{

	/**
	 * 
	 * TODO 封装excel.
	 * @param model
	 * @param workbook
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @author wangguohong
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		Date date = new Date();
		String filename = DateUtils.dateToSpecialcHARString(date);
		HSSFSheet sheet;
		HSSFCell cell;
		//设置输出格式为
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("sheet1");//创建excel sheet
		List<String> titles = (List<String>) model.get(ExcelUtil.EXCEL_PRO_TITLES);
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		//循环设置标题
		for(int i=0; i<len; i++){ 
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<T> listvalue = (List<T>) model.get(ExcelUtil.EXCEL_PRO_VALUES);
		List<String> properties = (List<String>) model.get(ExcelUtil.EXCEL_PRO_PROERTIES); //获取需要导出的字段属性
		int tCount = listvalue.size();
		int cols = properties.size();
		String columnName = null; //属性名称
		Object value = null; //属性值
		for (int i=0; i<tCount; i++) {
			T t = (T) listvalue.get(i);
			for (int j = 0; j < cols; j++) {
				columnName = properties.get(j);
				if(columnName==null || columnName.equals("")){
					continue;
				}
				//判断如果属性有注解则用注解显示的名称作为属性名称
				Field field= null;
				//获取所有分类包含本身
				List<Class> listClasses = new ArrayList<Class>();
				listClasses = FileUtil.getClasses(t.getClass(), listClasses);
				//循环所有父类以及子类
				for(Class s : listClasses){
					Field[] fields = s.getDeclaredFields();
					for(Field f : fields){
						if(f.getName().equals(columnName)){
							field = f;
							break;
						}
					}
						//field= s.getDeclaredField(columnName);
				}
				
				if(null != field.getAnnotation(ShowTypeName.class)){
					columnName = field.getAnnotation(ShowTypeName.class).showName();
				}
				value = PropertyUtils.getProperty(t, columnName);
				cell = getCell(sheet, i+1, j);
				cell.setCellStyle(contentStyle);
				setText(cell, value==null ? "" :DateUtils.toMString(value));
			}
		}
		
	}


	
}

