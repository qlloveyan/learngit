/**
 * Project Name:smcs<br>
 * File Name:FileHandleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年11月12日  下午05:22:29<br>
 *
 */
package com.surfilter.framework.filehandle.ctrl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.filehandle.DataImportHandle;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.ExeclImportError;
import com.surfilter.framework.filehandle.model.DataTemplate;
import com.surfilter.framework.filehandle.model.DateTemplateValidate;
import com.surfilter.framework.filehandle.model.FileHandle;
import com.surfilter.framework.filehandle.model.UserTest;
import com.surfilter.framework.filehandle.service.FileHandleService;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.MsgRel;
import com.surfilter.system.service.MsgRelService;

/**
 * 文件处理controller ClassName:FileHandleCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason: TODO ADD REASON.<br>
 * Date: 2013年11月12日 下午05:22:29<br>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 * @see
 */
@Controller
@RequestMapping
public class FileHandleCtrl extends BaseController implements DataImportHandle{

	/**
	 * 注入msgRelService.
	 */
	@Autowired
	private MsgRelService msgRelService;
	/**
	 * 注入fileHandleService.
	 */
	@Autowired
	private FileHandleService fileHandleService;

	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(FileHandleCtrl.class);

	/**
	 * getPageModel:分页查询.
	 * 
	 * @author wangguohong
	 * @param entity
	 *            查询实体
	 * @param page
	 *            页数
	 * @param rows
	 *            每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<FileHandle> getPageModel(FileHandle entity, Integer page,
			Integer rows) {

		Page<FileHandle> pageModel = null;
		try {
			pageModel = fileHandleService.getPageModel(entity,
					PageUtil.get(page, rows));
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return pageModel;
	}

	/**
	 * getEntity:通过ID查询实体.
	 * 
	 * @author wangguohong
	 * @param id
	 *            实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id) {

		FileHandle entity = null;
		try {
			entity = fileHandleService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return renderObject(entity);
	}

	/**
	 * 
	 * list:(获取集合). <br/>
	 * @author wangguohong
	 * @param filehandle
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<FileHandle> list(FileHandle filehandle) {
		return fileHandleService.list(filehandle);
	}
	
	/**
	 * addEntity:新增实体.
	 * 
	 * @author wangguohong
	 * @param entity
	 *            实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(FileHandle entity) {

		try {
			fileHandleService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return renderSuccess();
	}

	/**
	 * editEntity:编辑实体信息.
	 * 
	 * @author wangguohong
	 * @param entity
	 *            实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(FileHandle entity) {

		try {
			fileHandleService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return renderSuccess();
	}

	/**
	 * delBatchEntity:批量删除.
	 * 
	 * @author wangguohong
	 * @param ids
	 *            需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids) {

		try {
			fileHandleService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return renderSuccess();
	}

	/**
	 * delEntity:删除实体信息.
	 * 
	 * @author wangguohong
	 * @param id
	 *            实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id) {

		try {
			fileHandleService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败", e);
		}
		return renderSuccess();
	}

	/**
	 * 
	 * upload:(上传文件). <br/>
	 * 
	 * @author wangguohong
	 * @param request
	 * @since JDK 1.6
	 */
	@RequestMapping
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getSession().getAttribute("userId").toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String justpath = fileHandleService.upload(request, response, userId, userName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(justpath);
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		String md5="";
		try {
			md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
		IOUtils.closeQuietly(fis);  
		//System.out.println("MD5:"+md5);
//		entity.setMd5(md5);
		request.getSession().setAttribute("justpath", justpath);
		request.getSession().setAttribute("md5", md5);

	}

	/**
	 * loadTopImage:(获取机房top图). <br/>
	 * 
	 * @author wangguohong
	 * @param resouceId
	 *            资源编号
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public String loadTopImage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String resourceId = request.getParameter("resourceId");
			String resourceCode = request.getParameter("resourceCode");
			response.setContentType("image/*");
			ServletOutputStream out = response.getOutputStream();
			String savePath = FileUtil.getResouseValue("uploadpath");

			FileHandle file = new FileHandle();
			file.setResourceId(resourceId);
			file.setResourceCode(resourceCode);
			List<FileHandle> fileList = fileHandleService.getImglist(file);// 通过resouceId查找
			file = new FileHandle();
			File dir = null;
			if (fileList != null && fileList.size() > 0) {
				file = fileList.get(0);
				dir = new File(savePath + "/" + file.getPath());
			}
			if (dir != null) {
				byte tmp[] = new byte[1024];
				InputStream is = new FileInputStream(dir);
				int i = 0;
				while ((i = is.read(tmp)) != -1) {
					out.write(tmp, 0, i);
				}
				is.close();
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			log.error("获取图片文件失败", e);
		}
		return null;
	}

	@RequestMapping
	@ResponseBody
	public String checkIsExist(String resourceId, String resourceCode) {
		FileHandle file = new FileHandle();
		file.setResourceId(resourceId);
		file.setResourceCode(resourceCode);
		List<FileHandle> fileList = fileHandleService.getImglist(file);// 通过resouceId查找
		if (fileList != null && fileList.size() > 0) {
			return "suc";
		} else {
			return "fail";
		}
	}

	/**
	 * loadDataModel:(根据资源模块编码获取数据模板列表). <br/>
	 * 
	 * @author wangguohong
	 * @param request
	 * @param resourceCode
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject loadDataModel(HttpServletRequest request) {
		String resourceCode = request.getParameter("resourceCode");
		List<DataTemplate> listtemplate = fileHandleService
				.getTemplateByResourceCode(request, resourceCode);
		return renderObject(listtemplate);
	}

	/**
	 * downloadtemplate:(下载模板). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @throws IOException
	 * @since JDK 1.6
	 */
	@RequestMapping
	public void downloadtemplate(HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		String path = request.getSession().getServletContext().getRealPath("/");
//		String downLoadPath = new String(request.getParameter("path").getBytes("ISO-8859-1"),"UTF-8");
		String downLoadPath  = URLDecoder.decode(new String(request.getParameter("path")), "UTF-8");
		
//		String fileCnames = new String(request.getParameter("filename").getBytes("ISO-8859-1"),"UTF-8");
		String fileCnames = URLDecoder.decode(new String(request.getParameter("filename")), "UTF-8");
		
		if("/".equals(File.separator)){
			downLoadPath = downLoadPath.replace("\\", "/");
		}
		try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="+new String(fileCnames.getBytes("UTF-8"), "UTF-8"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))){
                bos.write(buff, 0, bytesRead);
            }
            //如果是消息提供的下载信息 则需要修改消息的状态
    		String msgid = request.getParameter("msgId");
    		if(null != msgid){
    			
    			MsgRel msgRel = msgRelService.getEntityById(Long.parseLong(msgid));
    			msgRel.setState(1l);//设置消息为已读
    			msgRelService.editEntity(msgRel);
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
	 * TODO 根据模板导入数据.
	 * @param request
	 * @return
	 * @see com.surfilter.framework.filehandle.DataImportHandle#importDate(javax.servlet.http.HttpServletRequest)
	 */
	@RequestMapping
	@ResponseBody
	@Override
	public ExtJsObject importData(HttpServletRequest request) {
		ExeclImportError er = null;
		try {
			er = new ExeclImportError();
			List<Object> listobj = fileHandleService.getTemplateList(request,UserTest.class,
					new String[]{"userName","age","sex"},
					new Class[]{String.class,int.class,String.class},
					new DateTemplateValidate[]{});
			for(Object o : listobj){
				UserTest u = (UserTest)o;
				System.out.println(u.getUserName()+" "+u.getAge()+" "+u.getSex());
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//处理 入库
//		return renderObject(er);
		return renderSuccess();
	}
	
	/**
	 * 
	 * downOpenFile:下载明文文件
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public void downOpenFile(String filePath, String fileName, HttpServletRequest request, HttpServletResponse response){
		String msg = null;
		if(filePath.contains("../")){
			msg="文件路径不合法!";
			returnMsg(msg,response);
			return;
		}
		if(filePath == null || "".equals(filePath)){
			msg="没有此条记录!";
			returnMsg(msg,response);
			return;
		}
		if(!StringUtils.isNotBlank(filePath)){
			msg="文件名为空！";
			returnMsg(msg,response);
			return;
		}
		
		byte[] data = getRealFile(filePath);
		if(data == null || data.length == 0){
			msg="文件为空！";
			returnMsg(msg,response);
			return;
		}
		
		try {
			fileName = URLDecoder.decode(fileName, "UTF-8");
			System.out.println(fileName);
			downloadFile(request, response, fileName, data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 
	 * 下载文件
	 * @param id
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public void downFile(long fileid, String fileName, HttpServletRequest request, HttpServletResponse response){
		String msg = null;
		FileHandle f = fileHandleService.getEntityById(fileid);
		if(f==null){
			msg="文件不存在!";
			returnMsg(msg,response);
			return;
		}
		String filepath = f.getPath();
		byte[] data = getRealFile(filepath);
		if(data == null || data.length == 0){
			msg="文件为空！";
			returnMsg(msg,response);
			return;
		}
		
		try {
			fileName = URLDecoder.decode(fileName, "UTF-8");
			System.out.println(fileName);
			downloadFile(request, response, fileName, data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 获取文件路劲对应的文件的字节流
	 * @param filePath
	 * @return
	 */
	private byte[] getRealFile(String filePath) {
		InputStream inputStream = null;
		byte[] data = null;
		try{
			String basePath = FileUtil.getResouseValue("uploadpath");
			String realFilePath = "";
			if(filePath.indexOf(".") > 0){
				realFilePath = basePath + File.separator + filePath.substring(0, filePath.lastIndexOf("."));
			}else{
				realFilePath = basePath + File.separator + filePath;
			}
			
			File file = new File(realFilePath);
			inputStream = new FileInputStream(file);
			data=new byte[inputStream.available()];
			inputStream.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
    /**
	 * 
	 * downloadFile:下载文件.
	 * @param response	
	 * @param fileName	文件名
	 * @param data		文件内容
	 * @since JDK 1.6	
	 */
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName, byte[] data) {
		try{
			// fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
			
			String agent = request.getHeader("User-Agent");
			boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);

			if (isMSIE) {
			    fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
			    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setCharacterEncoding("utf-8");
			response.reset();  
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream()); 
		    outputStream.write(data);
		    outputStream.flush();  
		    outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 返回消息
	 * @param msg
	 * @param response
	 */
	private void returnMsg(String msg, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter out=response.getWriter();
			out.println("<div align='center'><h2>");
			out.println("<font size='14' color='red'>");
			out.println(msg);
			out.println("</font>");
			out.println("</h2></div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
