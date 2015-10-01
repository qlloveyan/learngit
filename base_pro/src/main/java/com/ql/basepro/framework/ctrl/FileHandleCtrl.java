package com.ql.basepro.framework.ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ql.basepro.Constants;
import com.ql.basepro.framework.BaseController;
import com.ql.basepro.framework.model.FileHandle;
import com.ql.basepro.framework.utils.JsonUtil;

/**
 * 用户信息控制类
 * @author ql
 */
@Controller
@RequestMapping(value="file")
public class FileHandleCtrl extends BaseController{
	
	/**
	 * 根据模板文件夹获取模板文件
	 * @param request
	 * @param module 文件夹名称
	 * @return
	 */
	@RequestMapping(value="/getTemplate")
	@ResponseBody
	public List<FileHandle> getTemplate(HttpServletRequest request,String module){
		List<FileHandle> result = new ArrayList<FileHandle>();
		try {
			
			//获取模板文件存放路径
			String modulePath = request.getSession().getServletContext().getRealPath("/template");
			//拼接模板存放路径
			modulePath = modulePath + File.separator + module;
			
			File file = new File(modulePath);
			FileHandle temp = null;
			if( file.exists() ){
				String[] files = file.list();
				File ftemp = null;
				for( String str : files ){
					ftemp = new File(str);
					//封装对象
					temp = new FileHandle();
					temp.setResourcePath( modulePath+File.separator+ftemp.getName() );
					temp.setFileName( ftemp.getName() );
					
					result.add(temp);
				}
			}
		} catch (Exception e) {
			System.out.println("文件模板获取失败!");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 下载模板文件
	 * @param request
	 * @param path 文件夹路径
	 * @return
	 */
	@RequestMapping(value="/downTemplate")
	public void downTemplate(HttpServletRequest request , HttpServletResponse response ,  String path) throws Exception{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
//			path = new String(path.getBytes("iso8859-1"),"utf-8");
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			
			if("/".equals(File.separator)){
				path = path.replace("\\", "/");
			}
			File file = new File(path);
            long fileLength = file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="+new String(file.getName().getBytes("UTF-8"), "ISO8859-1" ));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(path));  
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))){
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bis != null){
              bis.close();
            }
            if(bos != null){
              bos.close();
            }
        }
	}
	
	/**
	 * 客户端将文件上传到服务器代码
	 * @param request
	 * @param response
	 * @param path 保存文件跟路径
	 */
	@RequestMapping(value="/uploadFile")
	public void uploadFile( HttpServletRequest request , HttpServletResponse response ){
		//定义返回类型
		Map<String,Object> result = new HashMap<String,Object>();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			//获取文件上传需要保存的路径
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
			 String myFileName = "";
			//创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){ 
	        	 //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){
	    	        		//保存文件
	    	        		OutputStream outputStream = new FileOutputStream( new File( uploadPath + File.separator+ myFileName) );
	    	        		InputStream input = file.getInputStream();
	    	        		
	    	        		int length = 0;
	    	        		byte[] buf = new byte[1024];
	    	        		while( ( length = input.read(buf) ) != -1 ){
	    	        			outputStream.write(buf,0,length); 
	    	        		}
	    	        		input.close();
	    	        		outputStream.close();
	                    }
	               }
	            }
	        }
			
	        result.put(Constants.RETCODE, Constants.RET_CODE_SUCCESS);
	        result.put(Constants.MESSAGE, "文件上传成功");
	        result.put("fileName", myFileName);
	        
		} catch (Exception e) {
		    result.put(Constants.RETCODE, Constants.RET_CODE_SERVER);
	        result.put(Constants.MESSAGE, Constants.RET_MSG_SERVER);
	        result.put("fileName", "");
			System.out.println("文件上传处理失败!");
			e.printStackTrace();
		}
		out.print( JsonUtil.writeObject(result) );
		out.flush();
		out.close();
	}
}
