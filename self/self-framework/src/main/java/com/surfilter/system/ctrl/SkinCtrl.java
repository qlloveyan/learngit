/**
 * Project Name:smcs<br>
 * File Name:SkinCtrl.java<br>
 * Package Name:<br>
 * Date:2013年12月25日  上午10:27:00<br>
 *
*/
package com.surfilter.system.ctrl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import org.springframework.web.bind.annotation.ResponseBody;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surfilter.system.service.ExcelService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import org.springframework.web.servlet.ModelAndView;
import com.surfilter.system.service.SkinService;
import com.surfilter.system.model.Skin;
import com.surfilter.system.model.User;

/**
 * ClassName:SkinCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月25日  上午10:27:00<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SkinCtrl extends BaseController implements ExcelOperate<Skin>{

	/**
	 * 注入skinService.
	 */
	@Autowired
	private SkinService skinService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<Skin> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SkinCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author wangguohong
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<Skin> getPageModel(Skin entity,Integer page,Integer rows){
	
		Page<Skin> pageModel = null;
		try {
			pageModel = skinService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		Skin entity = null;
		try {
			entity = skinService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * getCurrentUserSkin:(获取当前用户的皮肤). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getCurrentUserSkin(HttpServletRequest request,HttpServletResponse response){
		long userid = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Skin entity = skinService.getEntityByUserId(userid);
		request.getSession().setAttribute("skinCode", entity.getSkinCode());
		return renderObject(entity);
	}
	
	/**
	 * setCurrentUserSkin:(设置当前用户的皮肤). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject setCurrentUserSkin(HttpServletRequest request,HttpServletResponse response){
		long userid = Long.parseLong(request.getSession().getAttribute("userId").toString());
		Skin entity =skinService.setEntityByUserId(userid, request, response);
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author wangguohong
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(Skin entity,HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("user");
  		List<Skin> list = null;
		
		try {
			list =  skinService.getPageList(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		ModelAndView mv = excelService.exportExcel(entity,list, entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		return mv;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(Skin entity){
	
		try {
			skinService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(Skin entity){
	
		try {
			skinService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author wangguohong
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			skinService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			skinService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
}
