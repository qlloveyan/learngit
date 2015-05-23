package com.ql.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件工具类
 * @author ql
 * time: 2014年11月24日
 */
public class FileUtil {
	
	/**
	 * 读取文件内容
	 * @param filePath 文件路径
	 * @param codeing  文件编码
	 * @return 读取到的文件内容
	 */
	public static String readFile(String filePath,String codeing) throws Exception{
		StringBuffer content = new StringBuffer();
		
		File file = new File(filePath);
		InputStreamReader in = new InputStreamReader(new FileInputStream(file),codeing);
		BufferedReader reader = new BufferedReader( in );
		for( String temp = null ; (temp = reader.readLine()) != null ; ){
			content.append( temp );
		}
		reader.close();
		return content.toString();
	}
	
	/**
	 * 写文件的方法
	 * @param content 写入文件的内容
	 * @param destPath 文件保存路径
	 * @param fileName 文件名
	 * @throws Exception
	 */
	public static void createFile(String content , String destPath , String fileName) throws Exception{
		//判断文件夹是否存在
		if( !isExist(destPath) ){//不存在则创建相关文件夹目录
			mkdirs(destPath);
		}
		
		File file = new File(destPath + File.separator + fileName);
		FileWriter fw = new FileWriter(file);
		fw.write(content);
		fw.close();
	}
	
	/**
	 * 
	 * ensureFile:确保文件存在. <br/>
	 *
	 * @author hongcheng
	 * @param fileName 文件名
	 * @return 文件
	 * @throws IOException io异常
	 * @since JDK 1.6
	 */
	public static boolean isExist(String fileName) throws IOException {
		File file = new File(fileName);
		return file.exists();
	}
	
	/**
	 * mkdirs: 根据路径创建文件夹.
	 * 		         进行递归创建，一直到整个路径的文件夹都存在便不再创建.
	 * @author ql
	 * @param path 文件夹路径
	 * @return 创建之后的文件夹对象
	 * @since JDK 1.6
	 */
	public static File mkdirs(String path) {
		if ( path == null || "".equals(path) ) {
			return null;
		}
		File dir = new File(path);
		if (!dir.exists()) {
			if (dir.mkdirs()) {
				return dir;
			}
		}
		return null;
	}
}
