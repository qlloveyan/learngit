/**
 * Project Name:smcs
 * File Name:Excel.java
 * Package Name:com.smcs.core.excel
 * Date:2013-9-23下午2:02:26
 *
 */

package com.surfilter.framework.filehandle.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.model.DateTemplateValidate;
import com.surfilter.framework.filehandle.model.UserTest;
import com.surfilter.framework.page.ReflectUtil;
import com.surfilter.framework.utils.DateUtils;

/**
 * 
 * ClassName: ExcelUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO excel工具类 <br/>
 * date: 2013-9-23 下午4:09:46 <br/>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 */
public class ExcelUtil<T> {
	public static final String EXCEL_PRO_VALUES="values";
	public static final String EXCEL_PRO_PROERTIES="properties";
	public static final String EXCEL_PRO_TITLES="titles";
	public static final String FILE_SUFFIX = ".xls";
	public static final String EXCEL_DEFAULT_SHEET_NAME="sheet1";
	public static final String EXPORT_TYPE="exportType";
	
	/**
	 * 
	 * getTempFilesPath:(获取上传文件服务器文件夹路径). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	protected static String getTempFilesPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/tempFiles");
	}

	/**
	 * 
	 * impExcel:导入excel返回对象集合<br/>
	 *
	 * @author wangguohong
	 * @param clazz 对象类型
	 * @param file  导入的文件
	 * @param fieldNames 需要导入的字段名称
	 * @param fieldsTypes 需要导入的字段对应的类型
	 * @param DateTemplateValidate 模板验证规则
	 * @param request 请求对象
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
//	public static ExeclImport impExcel(Class<?> clazz, File file,
//			String[] fieldNames, Class<?>[] fieldsTypes,DateTemplateValidate[] validate) throws Exception {
//		ExeclImport el = new ExeclImport();
//		//开始读取excel
//		Workbook book;
//		WorkbookSettings workbookSettings = new WorkbookSettings();
//		workbookSettings.setEncoding("UTF-8");
//		book = Workbook.getWorkbook(file, workbookSettings);
//		Sheet sheet = book.getSheet(0);
//		
//		//开始解析excel
//		List<Object> listObj = new ArrayList<Object>();
//		List<ExeclImportError> listerror = new ArrayList<ExeclImportError>();
//		for (int i = 1; i < sheet.getRows(); i++) {
//			Object o = clazz.newInstance();
//			for (int j = 0; j < sheet.getColumns(); j++) {
//				Cell cell = sheet.getCell(j, i);
//				String result = null;
//				if(cell.getType().equals(CellType.DATE)){
//					
//					DateCell dcell = (DateCell)cell;
//					Date date = dcell.getDate();
//					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//					result = sf.format(date);
//				}else{
//					result = cell.getContents();
//					
//				}
//				// 根据字段类型构造value
//				createValue(fieldNames, fieldsTypes,validate, o, j, result);
//			}
//			listObj.add(o);
//		}
//		el.setListobj(listObj);
//		el.setListError(listerror);
//		return el;
//	}
	
	/**
	 * 兼容execl版本
	 * @param clazz
	 * @param file
	 * @param fieldNames
	 * @param fieldsTypes
	 * @param validate
	 * @return
	 * @throws Exception
	 */
	public static ExeclImport impExcelNew(Class<?> clazz, File file,
			String[] fieldNames, Class<?>[] fieldsTypes,DateTemplateValidate[] validate) throws Exception {
		ExeclImport el = new ExeclImport();
		
		boolean isE2007 = false; //判断文件是否是excel 2007
		if(file.getName().endsWith("xlsx")){
			isE2007 = true;
		}
		InputStream input = new FileInputStream(file);
		//开始读取excel
		Workbook wb  = null; 

		//根据文件格式(2003或者2007)来初始化  
	    if(isE2007)  
	    	wb = new XSSFWorkbook(input);  
	    else  
	    	wb = new HSSFWorkbook(input); 

	    Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
	    Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
		
		//开始解析excel
		List<Object> listObj = new ArrayList<Object>();
		List<ExeclImportError> listerror = new ArrayList<ExeclImportError>();
		while (rows.hasNext()) {
			Row row = rows.next(); // 获得行数据
			if(row.getRowNum()==0){
				continue;
			}
			Object o = clazz.newInstance();
			//System.out.println("Row #" + row.getRowNum()); // 获得行号从0开始
			Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
			while (cells.hasNext()) {
				Cell cell = cells.next();
			//	System.out.println("Cell #" + cell.getColumnIndex());
				String result = null;
				switch (cell.getCellType()) { // 根据cell中的类型来输出数据
				case HSSFCell.CELL_TYPE_NUMERIC:
					result = String.valueOf(cell.getNumericCellValue());
					BigDecimal db = new BigDecimal(result);
					result = db.toPlainString();
					if(result.contains(".0")){
						result = result.substring(0,result.length()-2);
					}
//					System.out.println(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					result = cell.getStringCellValue();
					//System.out.println(cell.getStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					result = String.valueOf(cell.getBooleanCellValue());
					//System.out.println(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					result = cell.getCellFormula();
					//System.out.println(cell.getCellFormula());
					break;
				default:
					result = "";
//					System.out.println("unsuported sell type");
					break;
				}

				createValue(fieldNames, fieldsTypes,validate, o, cell.getColumnIndex(), result);
			}
			listObj.add(o);
		}
//		for (int i = 1; i < sheet.getRows(); i++) {
//			Object o = clazz.newInstance();
//			for (int j = 0; j < sheet.getColumns(); j++) {
//				Cell cell = sheet.getCell(j, i);
//				String result = null;
//				if(cell.getType().equals(CellType.DATE)){
//					
//					DateCell dcell = (DateCell)cell;
//					Date date = dcell.getDate();
//					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//					result = sf.format(date);
//				}else{
//					result = cell.getContents();
//					
//				}
//				// 根据字段类型构造value
//				createValue(fieldNames, fieldsTypes,validate, o, j, result);
//			}
//			listObj.add(o);
//		}
		el.setListobj(listObj);
		el.setListError(listerror);
		return el;
	}

	/**
	 * validateCell:(验证excel行数据). <br/>
	 *
	 * @author wangguohong
	 * @param validate
	 * @param cell
	 * @return
	 * @since JDK 1.6
	 */
	public static int validateCell(DateTemplateValidate validate,Cell cell){
		return 0;
	}
	/**
	 * 
	 * createValue:(根据字段类型设置属性值 注意字段集合与字段类型集合 顺序要统一). <br/>
	 * 
	 * @author wangguohong
	 * @param fieldNames
	 *            字段集合
	 * @param fieldsTypes
	 *            字段类型集合
	 * @param o
	 *            实体对象
	 * @param j
	 *            字段序号
	 * @param result
	 *            字段值
	 * @throws Exception
	 * @since JDK 1.6
	 */
	private static void createValue(String[] fieldNames,
			Class<?>[] fieldsTypes, DateTemplateValidate[] validates,Object o, int j, String result)
			throws Exception {
		if (fieldsTypes[j].equals(int.class)) {
			Integer value = new Integer(result);
			ReflectUtil.setClassField(o, fieldNames[j], value);
		}
		if (fieldsTypes[j].equals(String.class)) {
			String value = new String(result);
			ReflectUtil.setClassField(o, fieldNames[j], value);
		}
		if (fieldsTypes[j].equals(long.class)) {
			Long value = new Long(result);
			ReflectUtil.setClassField(o, fieldNames[j], value);
		}
		if (fieldsTypes[j].equals(double.class)) {
			Double value = new Double(result);
			ReflectUtil.setClassField(o, fieldNames[j], value);
		}
	}
	
	
	
	/**
	 * writeExcel:(将数据写入excel). <br/>
	 *
	 * @author wangguohong
	 * @param model
	 * @param path
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @since JDK 1.6
	 */
	public  String writeExcel(Map<String, Object> model) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		Date date = new Date();
		String filename = DateUtils.dateToSpecialcHARString(date);
		HSSFSheet sheet;
		HSSFCell cell;
		HSSFRow hssfrow;
		sheet = hssfworkbook.createSheet("sheet1");//创建excel sheet
		List<String> titles = (List<String>) model.get(ExcelUtil.EXCEL_PRO_TITLES);
		int len = titles.size();
		//设置输出格式为
		HSSFCellStyle headerStyle = hssfworkbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = hssfworkbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		//循环设置标题
		hssfrow= sheet.createRow(0);
		for(int i=0; i<len; i++){ 
			String title = titles.get(i);
			//cell = getCell(sheet, 0, i);
			
			cell = hssfrow.createCell(i);
			cell.setCellStyle(headerStyle);
			//setText(cell,title);
			cell.setCellValue(title);
		}
		HSSFCellStyle contentStyle = hssfworkbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<T> listvalue = (List<T>) model.get(ExcelUtil.EXCEL_PRO_VALUES);
		List<String> properties = (List<String>) model.get(ExcelUtil.EXCEL_PRO_PROERTIES); //获取需要导出的字段属性
		int tCount = listvalue.size();
		int cols = properties.size();
		String columnName = null; //属性名称
		Object value = null; //属性值
		for (int i=0; i<tCount; i++) {
			hssfrow = sheet.createRow(i+1);
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
				//cell = getCell(sheet, i+1, j);
				cell  = hssfrow.createCell(j);
				cell.setCellStyle(contentStyle);
				//setText(cell, value==null ? "" :toMString(value));
				cell.setCellValue(value==null ? "" :DateUtils.toMString(value));
			}
		}
		
		
		FileOutputStream fileoutputstream = null;
		String filepath = model.get(FrameworkGlobal.FULL_EXCEL_PATH).toString()+FILE_SUFFIX;
		try {
			
			File f = new File(filepath);
			if(!f.exists()){
				f.createNewFile();
			}
			fileoutputstream = new FileOutputStream(filepath);
			hssfworkbook.write(fileoutputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fileoutputstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return filepath;
	}
	
	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		BigDecimal percentageValue = null;
		String percentageStr = "";
		if (cell == null)
			return "";
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return StringUtils.trim(cell.getStringCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return StringUtils.trim(String.valueOf(cell.getBooleanCellValue()));
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return StringUtils.trim(cell.getCellFormula());
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			//如果单元格内容是百分比
			if (cell.getCellStyle().getDataFormatString().indexOf("%") != -1) {
				percentageValue = new BigDecimal(cell.getNumericCellValue()*100);
				percentageStr = percentageValue.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue()+"%";
				return percentageStr;
			}else if(HSSFDateUtil.isCellDateFormatted(cell)){
				Date d = cell.getDateCellValue();
				percentageStr = DateUtils.dateToString(d, DateUtils.LONG_DATE_FORMAT);
				return percentageStr;
			}
			else{
				return StringUtils.trim(String.valueOf((int)cell.getNumericCellValue()));
			}
		}
		return "";
	}
	
	
	
	public static void main(String[] args) {
		File f = new File("d:/test.xls");
		try {
		//	System.out.println(f.getName());
			ExeclImport el = ExcelUtil.impExcelNew(UserTest.class, f, new String[]{"userName","age","sex","hight"}, new Class[]{String.class,String.class,String.class,double.class},new DateTemplateValidate[]{});
			
			for(Object o : el.getListobj()){
				 System.out.println("========================================");
				 Method[] fd = o.getClass().getDeclaredMethods();
				 for(Method ff : fd){
					 	if(ff.getName().startsWith("get")){
					 		System.out.println(ff.invoke(o));
					 	}
					 	//System.out.println(ff.getName());
//						Object value = PropertyUtils.getProperty(o, ff.getName());
//						System.out.print(ff.getName()+":"+value+"  ");
				 }
				
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}

}
