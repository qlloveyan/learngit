/**
 * Project Name:smcs<br>
 * File Name:FileHandleService.java<br>
 * Package Name:com.surfilter.framework.filehandle.service<br>
 * Date:2013年11月12日  下午05:22:29<br>
 *
*/
package com.surfilter.framework.filehandle.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.IFileHandle;
import com.surfilter.framework.filehandle.dao.DataTemplateMapper;
import com.surfilter.framework.filehandle.dao.FileHandleMapper;
import com.surfilter.framework.filehandle.excel.ExcelUtil;
import com.surfilter.framework.filehandle.model.DataTemplate;
import com.surfilter.framework.filehandle.model.DateTemplateValidate;
import com.surfilter.framework.filehandle.model.FileHandle;
import com.surfilter.framework.page.Page;

/**
 * ClassName:FileHandleService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年11月12日  下午05:22:29<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

@Service
@Transactional
public class FileHandleService implements IFileHandle{

	/**
	 *注入fileHandleMapper
	 */
	@Autowired
	private FileHandleMapper fileHandleMapper;
	
	/**
	 *注入dataTemplateMapper
	 */
	@Autowired
	private DataTemplateMapper dataTemplateMapper;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<FileHandle> getPageModel(FileHandle entity,RowBounds rowBounds){
		Page<FileHandle> page = new Page<FileHandle>();
		long total = fileHandleMapper.count(entity);
		List<FileHandle> rows = fileHandleMapper.list(entity,rowBounds);
		page = new Page<FileHandle>(total, rows);
		return page;
	}
	
	public List<FileHandle> list(FileHandle filehandle){
		return fileHandleMapper.list(filehandle);
	}
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(FileHandle entity){
		fileHandleMapper.addEntity(entity);
	}
	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(FileHandle entity){
		return fileHandleMapper.editEntity(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author wangguohong
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public FileHandle getEntityById(long id){
		return fileHandleMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return fileHandleMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author wangguohong
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return fileHandleMapper.delBatchEntity(ids);
    }

	@Override
	public String upload(HttpServletRequest request,
			HttpServletResponse response,
			String userid,String userName) {
			boolean flag = true;
			String savePath = FileUtil.getResouseValue("uploadpath");
			String resouseCode = request.getParameter("resouseCode");
			String resouseId = request.getParameter("resouseId");
			String filename = null;
			// 当前正在处理的文件分块序号
			int chunk = Integer.valueOf(request.getParameter("chunk"));
			// 分块上传总数
			int chunks = Integer.valueOf(request.getParameter("chunks"));

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// 判断当前表单是否为"multipart/form-data"
			String fullPath = null;
			String relativePath = null;
			try {
				if (isMultipart) {
					MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) request;
					Iterator<?> i = multiReq.getFileNames();
					long fileSize = 0;
					while (i.hasNext()) {
						multiReq.getAttribute("chunk");
						MultipartFile f = multiReq.getFile((String) i.next());
						//System.err.println(f.getOriginalFilename()+"--"+chunk);
						InputStream input = f.getInputStream();
						
						// 文件名
						filename = f.getOriginalFilename();
						String suffic = filename.substring(filename.lastIndexOf("."));
						fileSize+=f.getSize();
						if (!FileUtil.savedFileNameCache.containsKey(filename)) {
							FileUtil.savedFileNameCache.put(filename,
									FileUtil.getSavedFileName(filename));
						}
						// 保存文件目录绝对路径
						File dir = new File(savePath+"/"+resouseCode);
						if (!dir.isDirectory() || !dir.exists()) {
							dir.mkdirs();
						}
						// 保存文件绝对路径
						fullPath = dir + "/"+ FileUtil.savedFileNameCache.get(filename);
						relativePath = resouseCode+"/"+ FileUtil.savedFileNameCache.get(filename);
						if (chunk == 0) {
							File file = new File(fullPath);
							if (file.exists()) {
								file.delete();
							}
							// 上传文件
							FileUtil.uploadFile(input, fullPath);
						}
						if (chunk > 0) {
							// 追加文件
							FileUtil.uploadFile(input, fullPath, true);
						}
						if (chunk + 1 == chunks || chunks == 0) {
							FileUtil.savedFileNameCache.remove(filename);
							//合并文件后把文件信息保存到数据
							FileHandle fm = new FileHandle();
							fm.setFileName(filename);
							fm.setPath(relativePath);
							fm.setUploadTime(new Date());
							fm.setUploadUserid(userid);
							fm.setUploadUsername(userName);
							fm.setResourceCode(request.getParameter("resouseCode"));
							fm.setResourceId(request.getParameter("resouseId"));
							fm.setFileSize(fileSize);
							fm.setSuffixName(suffic);
							fileHandleMapper.addEntity(fm);
							break;
						}
					}
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
			//String fullPathtemp = fullPath.replaceAll("\\\\","\\\\\\\\");
			return fullPath;
	}

	@Override
	public boolean deletFile(FileHandle file) {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean download(HttpServletRequest request,
			HttpServletResponse response, FileHandle file) {
		
		// TODO Auto-generated method stub
		return false;
	}

	public byte[] getFileByte(String resouceId) {
		// TODO Auto-generated method stub
		return null;
	}

	public FileHandle getEntityByRid(String resourceId,String resourceCode) {
		return fileHandleMapper.getEntityByRid(resourceId,resourceCode);
	}
	public List<FileHandle> getImglist(FileHandle filehandle){
		return fileHandleMapper.getImglist(filehandle);
	}

	
	/**
	 * TODO 下载导入数据模板.
	 * @param request
	 * @param response
	 * @param template
	 * @return
	 * @see com.surfilter.framework.filehandle.IFileHandle#downTemplate(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.surfilter.framework.filehandle.model.DataTemplate)
	 */
	@Override
	public boolean downTemplate(HttpServletRequest request,
			HttpServletResponse response, DataTemplate template) {
		
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * 
	 * getTemplateList:(返回解析模板后的数据). <br/>
	 *
	 * @author wangguohong
	 * @param request http请求
	 * @param clazz 模板对应的vo实体类型
	 * @param fieldNames 模板字段对应的实体字段名称
	 * @param fieldsTypes 模板字段对应的实体字的类型
	 * @return
	 * @since JDK 1.6
	 */
	public List<Object> getTemplateList(HttpServletRequest request,Class<?> clazz,
			String[] fieldNames, Class<?>[] fieldsTypes,DateTemplateValidate[] validates ){
		String path = request.getSession().getAttribute("justpath").toString();
//		String path = request.getParameter("filepath");
		File f = new File(path);
		List<Object> list = null;
		try {
			list = ExcelUtil.impExcelNew(clazz, f, fieldNames, fieldsTypes, new DateTemplateValidate[]{}).getListobj();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return list;
	}
	
	/**
	 * TODO 根据资源模板获取导入数据模板列表.
	 * @param resourceCode
	 * @return
	 * @see com.surfilter.framework.filehandle.IFileHandle#getTemplateByResourceCode(java.lang.String)
	 */
	@Override
	public List<DataTemplate> getTemplateByResourceCode(HttpServletRequest request,String resourceCode) {
		String tomcatPath = request.getSession().getServletContext().getRealPath("/");
		String path = FileUtil.getResouseValue(FrameworkGlobal.TEMPLATE_PATH);
		String realpath = tomcatPath + path + File.separator + resourceCode;
		
		List<DataTemplate> listTemplate = new ArrayList<DataTemplate>();
		try {
			List<File> listf = FileUtil.getFiles(realpath);
			if(listf.size()>0){
				for(File f : listf){
					DataTemplate dt = new DataTemplate();
					dt.setTemplateFileName(f.getName());
					dt.setTemplagePath(f.getPath());
					listTemplate.add(dt);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listTemplate;
	}
}
