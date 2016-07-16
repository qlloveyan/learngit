package com.surfilter.framework.filehandle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;


/**
 * 文件处理帮助类
 * ClassName: FileUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2013-12-19 下午3:40:06 <br/>
 *
 * @author wangguohong
 * @version 
 * @since JDK 1.6
 */
public class FileUtil {
	/**
	 * 文件上传缓冲大小.
	 */
	private static final int BUF_SIZE = 1024 * 10;
	/**
	 * 文件长传每次读取长度.
	 */
	private static final int READ_LENGTH= 1024;
	/**
	 * 文件分块上传大小.
	 */
	private static final int CHUNKSIZE = 1024 * 10;
	
	/**
	 * 资源文件名称.
	 */
	public static final String RESOURCES = "resources.properties";
	
	/**
	 * 日志.
	 */
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 保存的文件名，当上传文件为分块多次请求时，需要确保文件名唯一.
	 */
	public static Map<String, String> savedFileNameCache = new HashMap<String, String>();
	
	
	/**
	 * getSavedFileNameCache:获取保存文件名map. <br/>
	 *
	 * @author wangguohong
	 * @return 分块存储名称.
	 * @since JDK 1.6
	 */
	public static Map<String, String> getSavedFileNameCache() {
		return savedFileNameCache;
	}

	/**
	 * setSavedFileNameCache:(设置保存文件名map). <br/>
	 *
	 * @author wangguohong
	 * @param savedFileNameCache 件名map.
	 * @since JDK 1.6
	 */
	public static void setSavedFileNameCache(Map<String, String> savedFileNameCache) {
		FileUtil.savedFileNameCache = savedFileNameCache;
	}

	/**
	 * Creates a new instance of FileUtil.
	 *
	 */
	public FileUtil() {
		savedFileNameCache = new HashMap<String, String>();
	}



	/**
	 * getSavedFileName:(获取文件系统保存的文件名). <br/>
	 *
	 * @author wangguohong
	 * @param filename 文件名
	 * @return 新文件名
	 * @since JDK 1.6
	 */
	public static String getSavedFileName(String filename) {
//		StringBuffer sb = new StringBuffer();
//		String suffic = filename.substring(filename.lastIndexOf("."));
//		String file = filename.substring(0, filename.lastIndexOf("."));
////		sb.append(file).append("_")
////				.append(DateUtil.dateToString(new Date(), "yyyyMMddHHssmm"))
////				.append(suffic);
//		sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHssmm"));
//		UUID.randomUUID();
//		return sb.toString();
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
	

	/**
	 * getFilePaths:(根据文件夹名称获取文件夹下的文件名称). <br/>
	 *
	 * @author wangguohong
	 * @param fileName
	 * @return
	 * @since JDK 1.6
	 */
	public static String[] getFilePaths(String filedirPath){
		String[] fileNames = null;
		File filedir = new File(filedirPath);
		if(filedir.exists() && filedir.isDirectory()){
			fileNames = filedir.list();
		}
		return fileNames;
	}
	
	

	/**
	 * readFile:(读取文件). <br/>
	 *
	 * @author wangguohong
	 * @param file 文件
	 * @return 返回字节数组
	 * @throws IOException io异常
	 * @since JDK 1.6
	 */
	public static byte[] readFile(File file) throws IOException{
		byte[] bytes = null;
		try {
			long len = file.length();
			bytes = new byte[(int) len];
			BufferedInputStream bfis = new BufferedInputStream(
					new FileInputStream(file));
			int r = bfis.read(bytes);
			if (r != len) {
				bfis.close();
				throw new IOException("读取文件不正确");
			}
			bfis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	
	
	/**
	 * writeFile:(写文件). <br/>
	 *
	 * @author wangguohong
	 * @param data 字节数组
	 * @param filename 文件名
	 * @since JDK 1.6
	 */
	public static void writeFile(byte[] data, String filename) {
		try {
			File file = new File(filename);
			file.getParentFile().mkdir();
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			bos.write(data);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	/**
	 * 
	 * delete:(删除文件). <br/>
	 *
	 * @author wangguohong
	 * @param path 文件名
	 * @since JDK 1.6
	 */
	public static void delete(String path) {
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
			log.debug("删除" + path + "文件成功");
		}
	}



	/**
	 * @Describtion: 获取当前系统格式
	 * @return String
	 */
	public static String getSystemProperties() {
		Properties pop = new Properties(System.getProperties());
		return pop.getProperty("sun.desktop");
	}

	

	/**
	 * getResouseValue:(获取资源属性值). <br/>
	 * 
	 * @author wangguohong
	 * @param propertyName 属性名称
	 * @return 返回资源值
	 * @since JDK 1.6
	 */
	public static String getResouseValue(String propertyName) {
		String value = "";
		InputStream is = null;
		try {
			URL configs = ClassUtils.getDefaultClassLoader().getResource(
					FileUtil.RESOURCES);
			is = configs.openStream();
			Properties pro = new Properties();
			pro.load(is);
			value = pro.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return value;
	}
	
	
	/**
	 * getResouseValue:(获取资源属性值). <br/>
	 * 
	 * @author wangguohong
	 * @param propertyName 属性名称
	 * @return 返回资源值
	 * @since JDK 1.6
	 */
	public static String getResouseValue(String propertyName,String fileName) {
		String value = "";
		InputStream is = null;
		try {
			URL configs = ClassUtils.getDefaultClassLoader().getResource(
					fileName);
			is = configs.openStream();
			Properties pro = new Properties();
			pro.load(is);
			value = pro.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		return value;
	}

	/**
	 * 
	 * uploadFile:(写文件). <br/>
	 *
	 * @author wangguohong
	 * @param is 文件输入流.
	 * @param fullName 文件绝对路径.
	 * @return 返回文件写入状态.
	 * @since JDK 1.6
	 */
	public static boolean uploadFile(InputStream is, String fullName) {
		try {
			OutputStream bos = new FileOutputStream(fullName);
			int bytesRead = 0;
			byte[] buffer = new byte[BUF_SIZE];
			while ((bytesRead = is.read(buffer, 0, READ_LENGTH)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			is.close();
			return true;
		} catch (Exception e) {
			log.error("上传文件时发生错误：", e);
			return false;
		}
	}

	/**
	 * 
	 * uploadFile:(追加文件). <br/>
	 *
	 * @author wangguohong
	 * @param is 输入流.
	 * @param fullName 文件绝对路径.
	 * @param isAppend 是否追加文件.
	 * @return 上传合并文件状态.
	 * @since JDK 1.6
	 */
	public static boolean uploadFile(InputStream is, String fullName,
			boolean isAppend) {
		int chunkSize = CHUNKSIZE;
		try {
			OutputStream bos = new FileOutputStream(fullName, isAppend);
			int bytesRead = 0;
			byte[] buffer = new byte[chunkSize];
			while ((bytesRead = is.read(buffer, 0, READ_LENGTH)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			is.close();
			return true;
		} catch (Exception e) {
			log.error("上传文件时发生错误：", e);
			return false;
		}
	}
	
	/**
	 * 
	 * getClasses:(获取所有超类以及类本身). <br/>
	 * @author wangguohong
	 * @param clazz
	 * @param listClasses
	 * @return
	 * @since JDK 1.6
	 */
	public static  List<Class> getClasses(Class clazz,List<Class> listClasses){
		
		listClasses.add(clazz);
		if(!clazz.getSuperclass().equals(Object.class)){
			listClasses = getClasses(clazz.getSuperclass(),listClasses);
		}
		return listClasses;
	}

	/**
	 * getFiles:(获取指定路径下的所有文件). <br/>
	 *
	 * @author wangguohong
	 * @param path
	 * @return
	 * @since JDK 1.6
	 */
	public static List<File> getFiles (String path)throws FileNotFoundException, IOException {
		List<File> listfile = new ArrayList<File>();
		File fdir = new File(path);
		if(fdir.isDirectory()){
			String[] filelist = fdir.list();
			for(String s : filelist){
				File readFile = new File(fdir+"/"+s);
				if(readFile.isFile()){
					listfile.add(readFile);
				}
			}
		}
		return listfile;
	}
	
	/**
	 * calculateBatch:(计算导出数据批次数量). <br/>
	 *
	 * @author wangguohong
	 * @param count 记录总数
	 * @param batchlimit 批次大小
	 * @return
	 * @since JDK 1.6
	 */
	public static int calculateBatch(Integer count,Integer batchlimit){
		int batchCount = 0;
		if(count%batchlimit==0){
			batchCount = count/batchlimit;
		}else{
			batchCount = (int) Math.floor(Double.parseDouble(count.toString())/Double.parseDouble(batchlimit.toString()))+1;
		}
		return batchCount;
	}
	
	/**
	 * 将文件大小转成 字符输出(如1B、1KB,1MB,1GB)
	 * @param size  文件大小
	 */
	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
 
        if (size >= gb) {
        	 float f = (float) size / gb;
            return String.format(f%1 ==0 ? "%.0f GB" : "%.2f GB", f);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f%1 ==0 ? "%.0f MB" : "%.2f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f%1 ==0 ? "%.0f KB" : "%.2f KB", f);
        } else
            return String.format("%d B", size);
    }
}





