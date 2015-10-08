/**
 * Project Name:smcs_zj
 * File Name:FtpUtil.java
 * Package Name:com.surfilter.smcs.utils
 * Date:2014年1月22日上午11:14:13
 *
*/

package com.ql.basepro.framework.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;



/**
 * ClassName:FtpUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014年1月22日 上午11:14:13 <br/>
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FtpUtils {
	
	private static Logger logger=Logger.getLogger(FtpUtils.class);
	
	private static String FTP_CHART_SET="ISO-8859-1";
	/**
	 * 
	 * ftpDownFile:从Ftp服务器上下载文件
	 * @author dengqw
	 * @param ip
	 * @param user
	 * @param password
	 * @param filePath
	 * @return
	 * @since JDK 1.6
	 */
	public static byte[] ftpDownFile(String ip,int port,String user,String password,String filePath){
		byte[] data=null;
		FTPClient ftp = new FTPClient();   
		InputStream inputStream=null;
		ByteArrayOutputStream baos=null;
		try{
			int reply = login(ip, port, user, password, ftp);    
			if (!FTPReply.isPositiveCompletion(reply)) {    
	            ftp.disconnect();   
	            return null;
	        }   
			
			baos=new ByteArrayOutputStream();
			inputStream=ftp.retrieveFileStream(filePath);
			if(inputStream!=null){
				int bt;
				while((bt=inputStream.read())!=-1){
					baos.write(bt);
				}
			}
			data=baos.toByteArray();
			
		} catch (IOException e) {
			logger.error(e);
			return null;
		}finally{
			try {
				if(baos!=null){
					baos.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
				ftp.completePendingCommand();
				if(ftp.isConnected()){
					ftp.disconnect();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return data;
	}
	/**
	 * 
	 * scanFTPFile:返回文件名和文件内容
	 *
	 * @author dengqw
	 * @param ip
	 * @param port
	 * @param user
	 * @param password
	 * @param filePath
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static Map<String,String> scanFTPFile(String ip,int port,String user,String password,String filePath){
		FTPClient ftp = new FTPClient();
		Map<String,String> map=new HashMap<String,String>();
		FTPFile[] files=null;
		try{
			int reply=login(ip, port, user, password, ftp);
			if (!FTPReply.isPositiveCompletion(reply)) {    
	            ftp.disconnect();   
	            return map;
	        } 
			
			boolean flag=changeDirectory(filePath, ftp);
			if(!flag){
				return map;
			}
			files=ftp.listFiles();
			
			if(files!=null){
				for(FTPFile file: files){
					if(file.isFile()){
						InputStream inputStream=null;
						ByteArrayOutputStream baos=null;
						byte[] data=null;
						try {
							baos=new ByteArrayOutputStream();
							inputStream=ftp.retrieveFileStream(file.getName());
							if(inputStream!=null){
								int bt;
								while((bt=inputStream.read())!=-1){
									baos.write(bt);
								}
							}
							data=baos.toByteArray();
							baos.close();
							if(inputStream!=null){
								inputStream.close();
							}
							ftp.completePendingCommand();
						} catch (Exception e) {
							logger.error(e);
						}
						String msg=new String(data,"UTF-8");
						map.put(file.getName(), msg);
					}
				}
			}
		}catch(Exception e){
			logger.error(e);
		}finally{
			if(ftp.isConnected()){
				try {
					ftp.disconnect();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
		
		return map;
	}
	private static boolean changeDirectory(String filePath, FTPClient ftp)
			throws IOException {
		boolean flag=false;
		if(StringUtils.isNotBlank(filePath)){
			if(filePath.startsWith("/")){
				ftp.changeWorkingDirectory("/");
				filePath=filePath.substring(1);
			}
			String[] paths=filePath.split("/");
			if(paths!=null){
				for(String p:paths){
					ftp.makeDirectory(p);
					flag=ftp.changeWorkingDirectory(p+"/");
					System.out.println(p+":"+flag);
					if(!flag){
						break;
					}
				}
			}else{
				flag=true;
			}
		}
		return flag;
	}
	
	public static void deleteFiles(String ip,int port,String user,String password,String filePath,List<String> fileNames) throws SocketException, IOException{
		FTPClient ftp = new FTPClient();
		try {
			int reply=login(ip, port, user, password, ftp);
			if (!FTPReply.isPositiveCompletion(reply)) {    
	            ftp.disconnect();   
	            return ;
	        } 
			boolean flag=changeDirectory(filePath, ftp);
			if(!flag){
				return;
			}
			for(String fileName:fileNames){
				int tryTime=0;
				boolean deleFlag=false;
				while(!deleFlag && tryTime<3){
					deleFlag=ftp.deleteFile(fileName);
					tryTime++;
				}
			}
		}catch(Exception e){
			logger.error(e);
		}finally{
			if(ftp.isConnected()){
				ftp.disconnect();
			}
		}
	}
	/**
	 * 
	 * ftpUploadFile:上传文件到FTP
	 *
	 * @author dengqw
	 * @param ip	IP
	 * @param port	端口
	 * @param user	 用户名
	 * @param password 密码
	 * @param file	文件
	 * @param path  切换的目录
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean ftpUploadFile(String ip,int port,String user,String password,File file,String path){
		FTPClient ftp = new FTPClient();   
		try{
			int reply = login(ip, port, user, password, ftp);    
			if (!FTPReply.isPositiveCompletion(reply)) {      
	           ftp.disconnect();      
	           return false;
		    }
			boolean flag=changeDirectory(path, ftp);
			if(!flag){
				return false;
			}
			upload(ftp,file);
		}catch(IOException e){
			logger.error(e);
			return false;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}finally{
			try {
				if(ftp.isConnected()){
					ftp.disconnect();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
		return true;
	}

	public static int login(String ip, int port, String user, String password,FTPClient ftp) throws SocketException, IOException {
		ftp.connect(ip,port);
		ftp.login(user,password); 
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
		ftp.setControlEncoding("UTF-8");
		int reply = ftp.getReplyCode();
		return reply;
	}
   
    /**  
     *   
     * @param file 上传的文件或文件夹  
     * @throws Exception  
     */    
	public static void upload(FTPClient ftp,File file) throws Exception{     
        if(file.isDirectory()){           
            ftp.makeDirectory(file.getName());                
            ftp.changeWorkingDirectory(file.getName());      
            String[] files = file.list();             
            for (int i = 0; i < files.length; i++) {      
                File file1 = new File(file.getPath()+File.separator+files[i] );      
                if(file1.isDirectory()){      
                    upload(ftp,file1);      
                    ftp.changeToParentDirectory();      
                }else{
                    File file2 = new File(file.getPath()+File.separator+files[i]);      
                    FileInputStream input = new FileInputStream(file2);      
                    ftp.storeFile(file2.getName(), input);      
                    input.close();                            
                }                 
            }      
        }else{      
            File file2 = new File(file.getPath()); 
            FileInputStream input = new FileInputStream(file2);      
            String ftpFileName=new String(file2.getName().getBytes(),FTP_CHART_SET);
            ftp.storeFile(ftpFileName, input);      
            input.close();        
        }      
    }  
    
    public boolean existDirectory(FTPClient ftpClient,String path) throws IOException{
    	boolean flag=false;
    	FTPFile[] ftpFiles=ftpClient.listFiles(path);
    	for(FTPFile ftpFile:ftpFiles){
   			if(ftpFile.isDirectory() && ftpFile.getName().equalsIgnoreCase(path)){
   				flag=true;
   				break;
   			}
    	}
    	return flag;
    }
    
    public static void main(String[] args) {
		ftpUploadFile("172.31.26.3", 21, "quanli", "123456", new File("C:\\Users\\lenovo\\Desktop\\111.doc"), "/smms_home");
	}
}

