/**
 * Project Name:smcs<br>
 * File Name:ProgramCtrl.java<br>
 * Package Name:<br>
 * Date:2015年05月18日  下午07:07:17<br>
 *
*/
package com.surfilter.scma.ctrl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.scma.framework.BaseController;
import com.surfilter.scma.framework.ExtJsObject;
import com.surfilter.scma.framework.Page;
import com.surfilter.scma.framework.PageUtil;
import com.surfilter.scma.model.Program;
import com.surfilter.scma.service.ProgramService;

/**
 * ClassName:ProgramCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:17<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class ProgramCtrl extends BaseController {

	/**
	 * 注入programService.
	 */
	@Autowired
	private ProgramService programService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(ProgramCtrl.class);
	
	
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
	public Page<Program> getPageModel(Program entity,Integer page,Integer rows){
	
		Page<Program> pageModel = null;
		try {
			pageModel = programService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Program entity = null;
		try {
			entity = programService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
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
	public ExtJsObject addEntity(Program entity){
	
		try {
			programService.addEntity(entity);
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
	public ExtJsObject editEntity(Program entity){
	
		try {
			programService.editEntity(entity);
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
			programService.delBatchEntity(ids);
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
			programService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
}
