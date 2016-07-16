/**
 * Project Name:smcs<br>
 * File Name:SysUnitCtrl.java<br>
 * Package Name:<br>
 * Date:2013年10月25日  下午07:41:42<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.SystemConstants;
import com.surfilter.system.model.AreaCode;
import com.surfilter.system.model.SysUnit;
import com.surfilter.system.model.UserRelUnitPositionDepartment;
import com.surfilter.system.service.DepartmentService;
import com.surfilter.system.service.SysUnitService;
import com.surfilter.system.service.UserRelUnitPositionDepartmentService;

/**
 * ClassName:SysUnitCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年10月25日  下午07:41:42<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SysUnitCtrl extends BaseController {

	/**
	 * 注入sysUnitService.
	 */
	@Autowired
	private SysUnitService sysUnitService;
	/**
	 * 注入sysUnitService.
	 */
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * userRelUnitPositionDepartmentService:TODO(注入userRelUnitPositionDepartmentService.).
	 * @since JDK 1.6
	 */
	@Autowired
	private UserRelUnitPositionDepartmentService userRelUnitPositionDepartmentService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SysUnitCtrl.class);
	
	
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
	public Page<SysUnit> getPageModel(SysUnit entity,Integer page,Integer rows){
	
		Page<SysUnit> pageModel = null;
		try {
			pageModel = sysUnitService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * listAllSysUnit: 查询所有模块列表. <br/>
	 *
	 * @author hongcheng
	 * @return	模块信息列表
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listAllSysUnit(){
		
		UnCheckTreeNodeBean uiUnits = new UnCheckTreeNodeBean();
		
		uiUnits = sysUnitService.listChildSysUnitByLevelAndParentId(uiUnits,null,"0",true);
				
		List<UnCheckTreeNodeBean> list = new ArrayList<UnCheckTreeNodeBean>();
		return uiUnits.getChildren()==null?list:uiUnits.getChildren();
	}
	
	/**
	 * 
	 * listAllSysUnitToCombobox:单位信息. <br/>
	 *
	 * @author hongcheng
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UnCheckTreeNodeBean> listAllSysUnitToCombobox(){
		UnCheckTreeNodeBean uiUnits = new UnCheckTreeNodeBean();
		uiUnits = sysUnitService.listChildSysUnitByLevelAndParentId(uiUnits, null, "0", true);
		
		List<UnCheckTreeNodeBean> list = uiUnits.getChildren();
		List<UnCheckTreeNodeBean> listTemp = new ArrayList<UnCheckTreeNodeBean>();
		for (UnCheckTreeNodeBean unCheckTreeNodeBean : list) {
			listTemp.add(unCheckTreeNodeBean);
		}
		
		boolean ishasTopmenu = false;
		UnCheckTreeNodeBean topmenu = new UnCheckTreeNodeBean();
		//如果菜单里没有添加“无”这个菜单节点则添加一个
		for(UnCheckTreeNodeBean temp : listTemp){
			if(temp.getId().equals(SystemConstants.NOT_HAS_PARENT_MODULE_ID)){
				ishasTopmenu = true;
			}
		}
		Map<String,Object> m = new HashMap<String,Object>();
		if(!ishasTopmenu){
			topmenu.setId(SystemConstants.NOT_HAS_PARENT_MODULE_ID);
			topmenu.setText(SystemConstants.NOT_HAS_PARENT_MODULE);
			if(list !=null && list.size()>0){
				topmenu.setLeaf(false);
			}else{
				topmenu.setLeaf(true);
			}
			m.put("parentId", 0);
			topmenu.setAttributes(m);
			listTemp.add(topmenu);
		}
		return listTemp;
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
	
		SysUnit entity = null;
		try {
			entity = sysUnitService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author hongcheng
	 * @param parentId 父
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntityByParentId(String parentId){
	
		List<SysUnit> entity = null;
		try {
			entity = sysUnitService.getEntityByParentId(parentId);
		} catch (Exception e) {
			e.printStackTrace();
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
	public ExtJsObject addEntity(SysUnit entity){
	
		try {
			sysUnitService.addEntity(entity);
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
	public ExtJsObject editEntity(SysUnit entity){
	
		try {
			sysUnitService.editEntity(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	
	/**
	 * checkNativeId:(根据nativeId获取单位集合以及父id). <br/>
	 *
	 * @author wangguohong
	 * @param nativeId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<SysUnit> checkNativeId(SysUnit entity){
		List<SysUnit> list = null;
		try {
			list = sysUnitService.listByNativeId(entity.getNativeId(),entity.getParentId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return list;
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
			sysUnitService.delBatchEntity(ids);
			for(long id:ids){
				departmentService.delEntityByUnitId(id);
			}
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
			sysUnitService.delEntity(id);
			//删除下属部门
			//departmentService.delEntityByUnitId(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * checkUserByUnit:(检查单位下是否存在用户). <br/>
	 *
	 * @author wangguohong
	 * @param unitid
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<UserRelUnitPositionDepartment> checkUserByUnit(Long unitid){
		UserRelUnitPositionDepartment entity = new UserRelUnitPositionDepartment();
		entity.setUnitId(unitid);
		List<UserRelUnitPositionDepartment> list = userRelUnitPositionDepartmentService.getPageList(entity);
		return list;
	}
	
	/**
	 * list:根据单位类型条件查询实体信息.
	 *
	 * @author zhangjw
	 * @param unitType 单位类型
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<SysUnit> getListByUnitType(String unitType){
		return sysUnitService.getListByUnitType(unitType);
	}
	
	/**
	 * getAreaByType:查询所有市的信息
	 *
	 * @author ql
	 * @return 查询实体集合
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public List<AreaCode> getAreaByType(String provinceCode){
		List<AreaCode> result = null;
		try {
			result = sysUnitService.getAreaByType(provinceCode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
