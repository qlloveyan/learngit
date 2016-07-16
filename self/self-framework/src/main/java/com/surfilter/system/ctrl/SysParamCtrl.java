/**
 * Project Name:smcs<br>
 * File Name:SysParamCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.SysParam;
import com.surfilter.system.service.SysParamService;

/**
 * ClassName:SysParamCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SysParamCtrl extends BaseController {

	/**
	 * 注入sysParamService
	 */
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(SysParamCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<SysParam> getPageModel(SysParam entity,Integer page,Integer rows){
	
		Page<SysParam> pageModel = null;
		try {
			pageModel = sysParamService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		SysParam entity = null;
		try {
			entity = sysParamService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(SysParam entity){
	
		try {
			sysParamService.addEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(SysParam entity){
	
		try {
			sysParamService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author hongcheng
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			sysParamService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			sysParamService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	
	/**
	 * getpropertyInfile:删除实体信息.
	 *
	 * @author wangguohong
	 * @param propertyName 属性名称
	 * @param fileName 资源文件名称
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public String getpropertyInfile(String propertyName,String fileName){
		String str = "";
		try {
			str = FileUtil.getResouseValue(propertyName,fileName);
		} catch (Exception e) {
			log.error("获取资源文件失败",e);
			e.printStackTrace();
		}
		return str;
	}
}
