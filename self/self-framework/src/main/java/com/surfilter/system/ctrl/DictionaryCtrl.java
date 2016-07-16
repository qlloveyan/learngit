/**
 * Project Name:smcs<br>
 * File Name:DictionaryCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.Dictionary;
import com.surfilter.system.service.DictionaryService;

/**
 * ClassName:DictionaryCtrl.java<br>
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
public class DictionaryCtrl extends BaseController {

	/**
	 * 注入dictionaryService
	 */
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 日志
	 */
	private static Logger log = Logger.getLogger(DictionaryCtrl.class);
	
	
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
	public Page<Dictionary> getPageModel(Dictionary entity,Integer page,Integer rows){
	
		Page<Dictionary> pageModel = null;
		try {
			pageModel = dictionaryService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getPageList:查询所有
	 *
	 * @author zhangwei
	 * @param entity 查询实体
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Dictionary> getPageList(Dictionary entity){
		List<Dictionary> list = null;
		try{
			list = dictionaryService.getPageList(entity);
		}catch(Exception e){
			log.error("查询信息失败",e);
		}
		return list;
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
	
		Dictionary entity = null;
		try {
			entity = dictionaryService.getEntityById(id);
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
	public ExtJsObject addEntity(Dictionary entity){
	
		try {
			dictionaryService.addEntity(entity);
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
	public ExtJsObject editEntity(Dictionary entity){
	
		try {
			dictionaryService.editEntity(entity);
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
			dictionaryService.delBatchEntity(ids);
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
			dictionaryService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * 
	 * getDictionaryByType:根据字典类型获取字典. <br/>
	 *
	 * @param type 字典类型
	 * @author wangguohong
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Dictionary> getDictionaryByType(String type){
		List<Dictionary> list = new ArrayList<Dictionary>();
		try {
			if(type != null){
				list = dictionaryService.getDictionaryByType(type);
			}
		} catch (Exception e) {
			log.error("获取数据失败！",e);
		}
		
		return list;
	}
	
	/**
	 * 
	 * getDictionaryByKey:根据type ，key 获取字典数据. <br/>
	 *
	 * @author wangguohong
	 * @param type
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Dictionary getDictionaryByKey(String type,String key){
		Dictionary d = null;
		try {
			 d = dictionaryService.getDictionaryByKey(type,key);
		} catch (Exception e) {
			log.error("获取数据失败！",e);
		}
		
		return d;
	}
	
	/**
	 * getTreeGridAllDic: 获取所有的节点，以层级关系展示节点,同步树，一次取出所有的节点
	 * getTreeGridAllDic:(这里用一句话描述这个方法的作用). <br/>
	 * @author tangbiao
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Dictionary> getTreeGridAllDic(String codeKey){
		Dictionary dic = new Dictionary();
		return dictionaryService.getTreeGridAllDic(dic, 1L, codeKey).getChildren();
	}
	
	/**
	 * 
	 * getTreeGridByParentId:异步树通过父节点获取子节点. <br/>
	 *
	 * @author tangbiao
	 * @param parentId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<Dictionary> getTreeGridByParentId(Long parentId){
		if(parentId == null){
			parentId = 1L;  // 顶级节点的ID
		}
		return dictionaryService.getTreeGridByParentId(parentId);
	}
}
