/**
 * Project Name:smcs_zj
 * File Name:FtpUtil.java
 * Package Name:com.surfilter.smcs.commons.utils
 * Date:2014年1月22日上午11:14:13
 *
*/

package com.surfilter.self.ftp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ClassName:FtpUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014年1月22日 上午11:14:13 <br/>
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FtpUtil {
	
	
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
	public static byte[] ftpDownFile(String ip,String user,String password,String filePath){
		byte[] data=null;
		FTPClient ftp = new FTPClient();   
		InputStream inputStream=null;
		ByteArrayOutputStream baos=null;
		try{
			ftp.connect(ip);
			ftp.login(user,password); 
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
			int reply = ftp.getReplyCode();    
			if (!FTPReply.isPositiveCompletion(reply)) {    
	            ftp.disconnect();    
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
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(baos!=null){
					baos.close();
				}
				if(inputStream!=null){
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	
	public static int login(String ip, int port, String user, String password,FTPClient ftp) throws SocketException, IOException {
		ftp.connect(ip,port);
		ftp.login(user,password); 
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
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
                File file1 = new File(file.getPath()+"\\"+files[i] );      
                if(file1.isDirectory()){      
                    upload(ftp,file1);      
                    ftp.changeToParentDirectory();      
                }else{
                    File file2 = new File(file.getPath()+"\\"+files[i]);      
                    FileInputStream input = new FileInputStream(file2);      
                    ftp.storeFile(file2.getName(), input);      
                    input.close();                            
                }                 
            }      
        }else{      
            File file2 = new File(file.getPath()); 
            FileInputStream input = new FileInputStream(file2);      
            ftp.storeFile(file2.getName(), input);      
            input.close();        
        }      
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
			
			FTPFile[] files = ftp.listFiles();
			upload(ftp,file);
			FTPFile[] files1 = ftp.listFiles();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}finally{
			try {
				if(ftp.isConnected()){
					ftp.disconnect();
				}
			} catch (IOException e) {
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		ftpUploadFile("103.16.126.229",21,"tmms","tmms",new File("D:\\data\\smms_home\\backup\\rzx_prdt\\浙-20141124\\4\\2015-12-18\\1448612596237.xml"),"/430000");
	}
	
}

