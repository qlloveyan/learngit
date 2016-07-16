package com.surfilter.framework.filehandle.ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@Deprecated
public class DownloadExcelModelCtrl {
	
	
	@RequestMapping
    public ModelAndView downloadExcelModel(HttpServletRequest request, HttpServletResponse response,String fileEnName,String fileCName)throws Exception {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String fileCnames="";
        if(StringUtils.isEmpty(fileEnName)){
        	return null;
        }
        if(StringUtils.isEmpty(fileCName)){
        	fileCnames=fileEnName;
        }else{
        	int fileNameIndex=fileEnName.lastIndexOf(".");
        	if(fileNameIndex!=-1){
        		fileCnames=fileCName+fileEnName.substring(fileNameIndex);
        	}else{
        		fileCnames=fileCName;
        	}
        }
        
        String ctxPath = request.getSession().getServletContext().getRealPath("/")+"resources"+File.separator+"excel"+File.separator;
        String downLoadPath = ctxPath + fileEnName;
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="+fileCnames);
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
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
        return null;
    }
}

