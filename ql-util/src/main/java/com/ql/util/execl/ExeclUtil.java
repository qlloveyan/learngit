package com.ql.util.execl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExeclUtil {

	/**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param fileName 读取数据的源Excel文件名
     * @param ignoreRows 读取数据忽略的行数，比如行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static String[][] getData(String fileName, int ignoreRows) throws Exception{
		File file = new File(fileName);
		List<String[]> result = new ArrayList<String[]>();
        int rowSize = 0;
        
        BufferedInputStream input = new BufferedInputStream( new FileInputStream( file ) );
        //打开HSSFWorkbook
//        POIFSFileSystem poiFs = new POIFSFileSystem(input);
        Workbook workBook = WorkbookFactory.create(input);
        //excel单元格
        Cell cell = null;
        //循环读取excel表格中的数据,循环去每一页
        for( int sheetIndex = 0 ; sheetIndex < workBook.getNumberOfSheets() ; sheetIndex++ ){
        	//当前行
        	Sheet st = workBook.getSheetAt(sheetIndex);
        	 // 第一行为标题，不取，循环取每一页中的每行数据
            for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
            	//每一行的数据
            	Row row = st.getRow( rowIndex );
	        	if( row == null ){
	        		continue;
	        	}
	        	int tempRowSize = row.getLastCellNum() + 1;
	        	if( tempRowSize > rowSize ){
	        		rowSize = tempRowSize;
	        	}
	        	String [] values = new String[rowSize];
	        	Arrays.fill(values, "");
	        	boolean hasValue = false;
	        	//循环取每一行中的每一个单元格的数据
	        	for( short columnIndex = 0 ; columnIndex <= row.getLastCellNum() ; columnIndex++ ){
	        		String value = "";
	        		//单个单元格数据
	        		cell = row.getCell(columnIndex);
	        		if( cell != null ){
	        			//根据单元格的数据类型去取相关的参数值
	        			value = getCellValue(cell);
	        		}
//	        		if (columnIndex == 0 && value.trim().equals("")) {
//	        			break;
//                  }
	        		values[columnIndex] = rightTrim(value);
	        		hasValue = true;
	        	}
	        	if (hasValue) {
	                result.add(values);
	            }
            }
        }
        //关闭文件读取流
        input.close();
        String[][] returnArray = new String[result.size()][rowSize];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = (String[]) result.get(i);
        }
        return returnArray;
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
      * 写excel表格，传入二维数组参数
      */
     public static void writeExcel(String[][] sourceData,String destPath , String fileName) {
    	 //往excel中写入数据
    	 File destFile = null;
    	 try {
    		 destFile = new File(destPath + File.separator + fileName);
    		 //创建读写excel的对象
    		 WritableWorkbook book = jxl.Workbook.createWorkbook( destFile );
    		 WritableSheet sheet  = book.createSheet("第一页", 0);
    		 
    		 Label label = null;
    		 for( int i = 0 ; i < sourceData.length ; i++ ){
    			 for( int j = 0 ; j < sourceData[i].length ; j++ ){
    				 label = new Label(j,i,sourceData[i][j]);
    				 //添加单元格数据至工作薄
    				 sheet.addCell(label);
    			 }
    		 }
    		book.write();
			book.close();
		} catch (FileNotFoundException e) {
			System.out.println("目标文件夹："+destPath +"不存在,请先创建该文件夹!");
			return;
		}catch (IOException e) {
			e.printStackTrace();
			return;
		}catch (WriteException e) {
			e.printStackTrace();
			return;
		}
    	 System.out.println("文件写入完成,保存路径为:" + destFile.getAbsolutePath());
     }
     
     public static void main(String[] args) {
//		String filePath = "C:\\Users\\lenovo\\Desktop\\test1.xlsx";
		String filePath = "C:\\Users\\lenovo\\Downloads\\noRecordWebsiteFeeedback.xls";
		try {
			String[][] data = getData(filePath, 1);
			for( int i = 0 ; i < data.length ; i++ ){
				for( int j = 0 ; j < data[i].length ; j++ ){
					System.out.print(data[i][j] + "\t\t");
				}
				System.out.println();
			}
			
//			System.out.println("写文件……");
//			writeExcel(data, filePath.split("\\\\")[filePath.split("\\\\").length - 1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
