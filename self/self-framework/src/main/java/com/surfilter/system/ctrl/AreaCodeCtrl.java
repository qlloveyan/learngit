/**
 * Project Name:smcs<br>
 * File Name:AreaCodeCtrl.java<br>
 * Package Name:<br>
 * Date:2013年12月19日  下午06:21:35<br>
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

import com.surfilter.system.dao.AreaCodeMapper;
import com.surfilter.system.model.AreaCode;
import com.surfilter.system.model.User;
import com.surfilter.system.service.AreaCodeService;
import com.surfilter.system.service.ExcelService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;

import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:AreaCodeCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年12月19日  下午06:21:35<br>
 * 
 * @author   onion
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
/**
 * @author onion
 *
 */
@Controller
@RequestMapping
public class AreaCodeCtrl extends BaseController implements ExcelOperate<AreaCode>{

	/**
	 * 注入areaCodeService.
	 */
	@Autowired
	private AreaCodeService areaCodeService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<AreaCode> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(AreaCodeCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 * @author onion
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<AreaCode> getPageModel(AreaCode entity,Integer page,Integer rows){
		Page<AreaCode> pageModel = null;
		try {
			pageModel = areaCodeService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	
	/**
	 * getPageList:查询List.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<AreaCode> getPageList(AreaCode entity){
		List<AreaCode>  result = null;
		try {
			result = areaCodeService.getPageList(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	
	/**
	 * description :通过区域编码查询区域信息
	 * @param areaCode
	 * @return AreaCode
	 */
	@RequestMapping
	@ResponseBody
	public AreaCode getEntityByCode(Long areaCode){
		AreaCode result = null;
		try {
			if(areaCode != null){
				result = areaCodeService.getEntityByCode(areaCode);
			}
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author onion
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		AreaCode entity = null;
		try {
			entity = areaCodeService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author onion
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(AreaCode entity,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
  		
  		List<AreaCode> list = null;
		
		try {
			list =  areaCodeService.getPageList(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		ModelAndView mv = excelService.exportExcel(entity,list, entity.getProperties()[0].split(","), entity.getTitles()[0].split(","),user);
		return mv;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author onion
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(AreaCode entity){
	
		try {
			areaCodeService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author onion
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(AreaCode entity){
	
		try {
			areaCodeService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author onion
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			areaCodeService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author onion
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			areaCodeService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	/** 
	 * 
	 * getAreasByProvinceAreaCode:根据省查询所属市的信息网安
	 *
	 * @author Administrator
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<AreaCode> getAreasByProvinceAreaCode(AreaCode entity){
		List<AreaCode>  result = null;
		try {
			result = areaCodeService.getListByProvinceAreaCode(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return result;
	}
}
