/**
 * Project Name:smcs<br>
 * File Name:CodeCtrl.java<br>
 * Package Name:<br>
 * Date:2015年06月04日  下午03:28:50<br>
 *
*/
package com.ql.basepro.system.ctrl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ql.basepro.framework.BaseController;
import com.ql.basepro.framework.ExtJsObject;
import com.ql.basepro.framework.Page;
import com.ql.basepro.framework.utils.PageUtil;
import com.ql.basepro.system.model.Code;
import com.ql.basepro.system.service.CodeService;

/**
 * ClassName:CodeCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年06月04日  下午03:28:50<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping(value="code")
public class CodeCtrl extends BaseController {

	/**
	 * 注入codeService.
	 */
	@Autowired
	private CodeService codeService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(CodeCtrl.class);
	
	
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
	@RequestMapping(value="/getPageModel")
	@ResponseBody
	public Page<Code> getPageModel(Code entity,Integer page,Integer rows){
	
		Page<Code> pageModel = null;
		try {
			pageModel = codeService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	@RequestMapping(value="/getParentCode")
	@ResponseBody
	public List<Code> getParentCode(){
		
		List<Code> pageModel = null;
		try {
			pageModel = codeService.getParentCode();
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
	@RequestMapping(value="/getEntity")
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		Code entity = null;
		try {
			entity = codeService.getEntityById(id);
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
	@RequestMapping(value="/addEntity")
	@ResponseBody
	public ExtJsObject addEntity(Code entity){
	
		try {
			codeService.addEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
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
	@RequestMapping(value="/editEntity")
	@ResponseBody
	public ExtJsObject editEntity(Code entity){
	
		try {
			codeService.editEntity(entity);
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
	@RequestMapping(value="/delBatchEntity")
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			codeService.delBatchEntity(ids);
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
	@RequestMapping(value="/delEntity")
	@ResponseBody
	public ExtJsObject delEntity(long id){
		ExtJsObject result = new ExtJsObject(true, "操作成功!");
		try {
			Code entity = new Code();
			entity.setPid( id+"" );
			List<Code> list = codeService.getPageList(entity);
			if( list != null && list.size() > 0 ){
				result = new ExtJsObject(false, "不能对存在子节点的字典进行删除操作!");
			}else{
				codeService.delEntity(id);
			}
		} catch (Exception e) {
			result = new ExtJsObject(false, "操作失败!");
			log.error("分页信息失败",e);
		}
		return result;
	}
	
	/**
	 * 根据类型获取下属数据字典
	 */
	@RequestMapping(value="/getDictionaryByType")
	@ResponseBody
	public List<Code> getDictionaryByType(String type){
		Code entity = new Code();
		entity.setCodeType(type);
		List<Code> result = null;
		try {
			result = codeService.getDictionary(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据类型获取下属数据字典
	 */
	@RequestMapping(value="/changeUsing")
	@ResponseBody
	public ExtJsObject changeUsing(long id,int status){
		ExtJsObject result = new ExtJsObject(true, "状态修改成功!");
		try {
			Code entity = new Code();
			entity.setId( id+"");
			entity.setIsUsing( status == 1?2:1 );
			codeService.editEntity(entity);
		} catch (Exception e) {
			result = new ExtJsObject(true, "状态修改失败!");
			e.printStackTrace();
		}
		return result;
	}
	
}
