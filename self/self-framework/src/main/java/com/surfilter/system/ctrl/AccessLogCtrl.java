/**
 * Project Name:smcs<br>
 * File Name:AccessLogCtrl.java<br>
 * Package Name:<br>
 * Date:2014年11月19日  上午09:57:23<br>
 *
*/
package com.surfilter.system.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.utils.DateUtils;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.AccessLog;
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.model.User;
import com.surfilter.system.service.AccessLogService;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.FuncModuleService;
import com.surfilter.system.service.SysArgsService;

/**
 * ClassName:AccessLogCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年11月19日  上午09:57:23<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class AccessLogCtrl extends BaseController implements ExcelOperate<AccessLog>{

	/**
	 * 注入accessLogService.
	 */
	@Autowired
	private AccessLogService accessLogService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<AccessLog> excelService;
	
	@Autowired
	private FuncModuleService funcModuleService;
	
	/**
	 * 注入sysArgsService用于查询相关的配置.
	 */
	@Autowired
	private SysArgsService sysArgsService;
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(AccessLogCtrl.class);
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author lenovo
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<AccessLog> getPageModel(AccessLog entity,Integer page,Integer rows){
		if( entity.getTimeAfter() != null){
			entity.setTimeAfter( DateUtils.nextDay( entity.getTimeAfter() ) );
		}
		Page<AccessLog> pageModel = null;
		try {
			
			pageModel = accessLogService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		AccessLog entity = null;
		try {
			entity = accessLogService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author lenovo
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(final AccessLog entity,HttpServletRequest request) {
		if( entity.getTimeAfter() != null){
			entity.setTimeAfter( DateUtils.nextDay( entity.getTimeAfter() ) );
		}
		
		String[] properties = {"userName","time","ip","funcModule","fiMenu","seMenu","methodType","logMsg"};
		String[] headers = {"操作用户","时间","访问端IP","功能模块","一级菜单","二级菜单","方法类型","日志信息"};
  		
  		final User user = (User) request.getSession().getAttribute("user");
//		Page<AccessLog> pages = null;
		List<AccessLog> pages = null;
  		
		ModelAndView mv = null;

		try {
//			pages = accessLogService.getPageModel(entity, PageUtil.get(
//					Integer.parseInt(entity.getBatchnum()),
//					Integer.parseInt(entity.getBatchsize())));
			pages = accessLogService.getPageList(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败", e);
		}

//		mv = excelService.exportExcel(entity, pages.getRows(), properties, headers, user);
		mv = excelService.exportExcel(entity, pages, properties, headers, user);
		return mv;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(AccessLog entity){
	
		try {
			accessLogService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(AccessLog entity){
	
		try {
			accessLogService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author lenovo
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			accessLogService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author lenovo
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			accessLogService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * 
	 * getPlateFormList:获取系统平台模块菜单. <br/>
	 *
	 * @author lenovo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<FuncModule> getPlateFormList(String funcId){
		String[] ids = funcId.split(",");
//		List<FuncModule> list = accessLogService.getPlateFormList(ids);
//		return list;
		return accessLogService.getPlateFormList(ids);
	}
	
	/**
	 * 
	 * getFuncMenuList:根据平台获取模块菜单. <br/>
	 *
	 * @author lenovo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<FuncModule> getFuncMenuList(long parentId){
		return accessLogService.getFuncMenuList(parentId);
//		return list;
	}
	
	/**
	 * 
	 * getMethodType:获取操作的方法类型. <br/>
	 *
	 * @author lenovo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Map<String,String>> getMethodType(){
		return accessLogService.getMethodType();
//		return list;
	}
}
