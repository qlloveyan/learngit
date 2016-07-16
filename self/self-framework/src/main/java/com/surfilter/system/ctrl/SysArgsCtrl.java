/**
 * Project Name:smcs<br>
 * File Name:SysArgsCtrl.java<br>
 * Package Name:<br>
 * Date:2014年01月02日  下午07:21:37<br>
 *
*/
package com.surfilter.system.ctrl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;

import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.surfilter.system.model.SysArgs;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.RedisService;
import com.surfilter.system.service.SysArgsService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;

import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:SysArgsCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月02日  下午07:21:37<br>
 * 
 * @author   dengqw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SysArgsCtrl extends BaseController implements ExcelOperate<SysArgs>{

	/**
	 * 注入sysArgsService.
	 */
	@Autowired
	private SysArgsService sysArgsService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<SysArgs> excelService;
	
	/**
	 * redisService
	 */
	@Autowired
	private RedisService redisService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SysArgsCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author dengqw
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<SysArgs> getPageModel(SysArgs entity,Integer page,Integer rows){
	
		Page<SysArgs> pageModel = null;
		try {
			pageModel = sysArgsService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		SysArgs entity = null;
		try {
			entity = sysArgsService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author dengqw
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(SysArgs entity,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
  		
  		List<SysArgs> list = null;
		
		try {
			list =  sysArgsService.getPageList(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		ModelAndView mv = excelService.exportExcel(entity,list, entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		return mv;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(SysArgs entity){
		if(entity.getIsReadonly()==null){
			entity.setIsReadonly(0L);
		}
		if(entity.getCanNotNull()==null){
			entity.setIsReadonly(0L);
		}
		try {
			sysArgsService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author dengqw
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(SysArgs entity){
		if(entity.getIsReadonly()==null){
			entity.setIsReadonly(0L);
		}
		if(entity.getCanNotNull()==null){
			entity.setIsReadonly(0L);
		}
		try {
			sysArgsService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author dengqw
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			sysArgsService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author dengqw
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			sysArgsService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	/**
	 * 
	 * getArgByType:通过类型获取系统参数
	 *
	 * @author dengqw
	 * @param typeId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<SysArgs> getArgByType(long typeId){
		List<SysArgs> lstArgs=sysArgsService.getArgByType(typeId);
		if(lstArgs==null){
			lstArgs=new ArrayList<SysArgs>();
		}
		return lstArgs;
	}
	/**
	 * 
	 * saveAllEntity:保存所有的属性值
	 *
	 * @author dengqw
	 * @param typeId
	 * @return
	 * @since JDK 1.6
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping
	@ResponseBody
	public ExtJsObject saveAllEntity(HttpServletRequest request){
		try {
			String argType=(String)request.getParameter("typeId");
			if(StringUtils.isNotBlank(argType)){
				Long typeId=Long.valueOf(argType);
				Map<String,String> map=new HashMap<String,String>();
				Enumeration enums=request.getParameterNames();
				if(enums!=null){
					//92代表系统全局配置，如果位92则要同步redis
					String threshold = "";
					String threshold_pre = "";
					while(enums.hasMoreElements()){
						String name=(String) enums.nextElement();
						if("THRESHOLD".equals(name)){
							threshold = request.getParameter(name);
						}
						if("THRESHOLD_PER".equals(name)){
							threshold_pre = request.getParameter(name);
						}
						if(!"typeId".equals(name)){
							String value=request.getParameter(name);
							map.put(name, value);
						}
					}
					Map<String,String> redisMap = new HashMap<String,String>();
					redisMap.put("THRESHOLD", threshold);
					redisMap.put("THRESHOLD_PER", threshold_pre);
					//redisService.save(RedisService.TEMP, "DMDETECT","high", JsonUtil.writeObject(redisMap));
//					System.out.println("threshold :: "+threshold+"   threshold_pre :: "+threshold_pre);
				}
				sysArgsService.saveAll(typeId,map);
//				System.out.println(map);
			}
		} catch (Exception e) {
			log.error("分页信息失败",e);
			e.printStackTrace();
		}
		return renderSuccess();
	}
	/**
	 * 
	 * isUnique:判断唯一性
	 *
	 * @author dengqw
	 * @param args
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject isUnique(SysArgs args){
		 List<SysArgs> lst= sysArgsService.isUnique(args);
		 boolean isUnique=true;
		 if(lst!=null && lst.size()>0){
			 isUnique=false;
		 }
		return renderObject(isUnique,"已存在该Key的数据，请修改Key",lst);
	}
	
	@RequestMapping
	@ResponseBody
	public ExtJsObject getSysArgs(HttpServletRequest request){
		List<SysArgs> lstArgs=sysArgsService.getArgByType(102);
		for(SysArgs a : lstArgs){
			request.getSession().setAttribute(a.getKey(), a.getValue());
		}
		 String ip = request.getHeader("x-forwarded-for");
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        request.getSession().setAttribute("localIp", ip);
		return renderObject(lstArgs);
	}
	
}
