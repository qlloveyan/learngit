package com.ql.basepro.framework.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理的公共类
 * @author ql
 *
 */
public class ExcelUtil {
	
	//定义文件后缀判断标志位
	private static boolean isExcel2007;
	
	//读取excel的时候忽略的行数
	private static int ingroneNum = 1;
	
	/**
	 * 获取上传的excel文件的内容
	 * @param file 上传的文件对象
	 * @param objClass 返回的请求对象
	 * @param filedNames 实体字段集合
	 * @param filedType 实体字段类型
	 * @return 封装的集合,规则 key(当前sheet的名称) -->  value(当前sheet页的数据集合)
	 */
	public static Map<String,Object> getTemplateList(HttpServletRequest request,MultipartFile file, Class<?> objClass,String[] filedNames,Class<?>[] filedTypes) throws Exception{
		//封装数据
		Map<String,Object> result = new HashMap<String, Object>();
		//判断文件类型
		String originalFile = file.getOriginalFilename();
		if( originalFile.endsWith(".xlsx") ){
			isExcel2007 = true;
		}
		//文件需要现在服务器中保存一份,在进行excel读取
		String fileName = saveLoadFile(file,request);
		//获取输入流对象
		File fileTemp = new File(fileName);
		InputStream input = new FileInputStream(fileTemp);
		//获取excel处理对象
		Workbook workbook = null;
		if( isExcel2007 ){//excel2007以上
			workbook = new XSSFWorkbook(input);
		}else{
			workbook = new HSSFWorkbook(input);
		}
		//循环读取excel的sheet单元格
		for( int sheetNum = 0 ; sheetNum < workbook.getNumberOfSheets() ; sheetNum++ ){
			List<Object> data = new ArrayList<Object>();
			//获取当前sheet的名称作为当前页的
			Sheet currentSheet = workbook.getSheetAt(sheetNum);
			//获取sheet的名称
			String sheetName = currentSheet.getSheetName();
			//读取sheet内容
			for( int hnum = ingroneNum ; hnum <= currentSheet.getLastRowNum() ; hnum++ ){
				//循环读行
				//初始化对象
				Object o = objClass.newInstance();
				
				Row row = currentSheet.getRow(hnum);
				//行数据数组
				String[] cellData = new String[row.getLastCellNum()];
				for( int rNum = 0 ; rNum < row.getLastCellNum() ; rNum++ ){
					//初始化单元格对象
					Cell cell = row.getCell(rNum);
					String value = "";
					if( cell != null ){
	        			//根据单元格的数据类型去取相关的参数值
	        			value = getCellValue(cell);
	        		}
					cellData[rNum] = rightTrim(value);
				}
				createObj(filedNames,filedTypes,cellData,o);
				//添加返回数据
				data.add(o);
			}
			//封装数据
			result.put(sheetName, data);
		}
		//关闭文件流
		input.close();
		//处理完成之后删除文件
		deleteFile(fileName);
		
		//返回数据
		return result;
	}
	
	/**
	 * 文件上传保存至服务器处理
	 * @param file 上传的文件
	 * @param request http请求对象
	 * @return 保存后的文件路径
	 */
	public static String saveLoadFile(MultipartFile file,HttpServletRequest request) throws Exception{
		String destPath;
		// 获取文件流对象
		InputStream input = file.getInputStream();
		//获取文件保存路径
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		//获取文件原名称
		String fileName = file.getOriginalFilename();
		//保存文件目录绝对路径
		File dir = new File(savePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		//封装返回文件
		String fileNameTemp = UUID.randomUUID()+fileName;
		destPath = dir.getAbsolutePath() + File.separator + fileNameTemp;
		OutputStream bos = new FileOutputStream(destPath);
		//写文件
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = input.read(buffer, 0, 1024)) != -1) {
			bos.write(buffer, 0, bytesRead);
		}
		bos.close();
		input.close();

		return destPath;
	}

	/**
	 * 根据文件名称删除文件
	 * @param filePath
	 */
	public static void deleteFile(String filePath){
		File file = new File(filePath);
		if( file.exists() ){
			file.delete();
		}
	}
	
	/**
	 * 去单元格中的值
	 * @param cell：单元格
	 * @return 单元格的值
	 */
	public static String getCellValue(Cell cell){
		String result = "";
		switch ( cell.getCellType() ) {
		case Cell.CELL_TYPE_BLANK://空格
			result = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN://boolean类型的值
			result = cell.getBooleanCellValue() == true?"true":"false";
			break;
		case Cell.CELL_TYPE_ERROR://错误信息
			result = "";
			break;
		case Cell.CELL_TYPE_FORMULA:// 导入时如果为公式生成的数据则无值
			if (!cell.getStringCellValue().equals("")) {
				result = cell.getStringCellValue();
             } else {
            	 result = cell.getNumericCellValue() + "";
             }
			break;
		case Cell.CELL_TYPE_NUMERIC://数值类型
			result = cell.getNumericCellValue() +"";
			break;
		case Cell.CELL_TYPE_STRING://字符串类型
			result = cell.getStringCellValue();
			break;
		default:
			result = "";
		}
		
		return result;
	}
	
	/**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }
    
    /**
     * 根据每行的数据和数据类型封装对应的数据 
     * @param filedNames 字段名称
     * @param filedType 字段类型
     * @param values 字段值数组
     * @param o 封装目标对象
     * @return 封装完成的对象
     */
    public static Object createObj(String[] fieldNames,Class<?>[] filedTypes,String[] values,Object o) throws Exception{
    	 
    	//循环遍历取数据进行封装
    	for( int i = 0 ; i < fieldNames.length ; i++ ){
    		if (filedTypes[i].equals(int.class)) {
    			Integer value = new Integer(values[i]);
    			ReflectUtil.setClassField(o, fieldNames[i], value);
    		}
    		if (filedTypes[i].equals(String.class)) {
    			String value = new String(values[i]);
    			ReflectUtil.setClassField(o, fieldNames[i], value);
    		}
    		if (filedTypes[i].equals(long.class)) {
    			Long value = new Long(values[i]);
    			ReflectUtil.setClassField(o, fieldNames[i], value);
    		}
    		if (filedTypes[i].equals(double.class)) {
    			Double value = new Double(values[i]);
    			ReflectUtil.setClassField(o, fieldNames[i], value);
    		}
    	}
    	return o;
    }
}
