/**
 * Project Name:smcs<br>
 * File Name:MsgRelCtrl.java<br>
 * Package Name:<br>
 * Date:2014年01月25日  下午01:44:32<br>
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

import com.surfilter.system.service.ExcelService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import org.springframework.web.servlet.ModelAndView;
import com.surfilter.system.service.MsgRelService;
import com.surfilter.system.model.MsgRel;
import com.surfilter.system.model.User;

/**
 * ClassName:MsgRelCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年01月25日  下午01:44:32<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class MsgRelCtrl extends BaseController implements ExcelOperate<MsgRel>{

	/**
	 * 注入msgRelService.
	 */
	@Autowired
	private MsgRelService msgRelService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<MsgRel> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(MsgRelCtrl.class);
	
	
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
	public Page<MsgRel> getPageModel(MsgRel entity,Integer page,Integer rows){
	
		Page<MsgRel> pageModel = null;
		try {
			pageModel = msgRelService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	
	/**
	 * getMyMsg:(获取自己的消息). <br/>
	 *
	 * @author wangguohong
	 * @param entity
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<MsgRel> getMyMsg(MsgRel entity,Integer page,Integer rows,HttpServletRequest request){
	
		Page<MsgRel> pageModel = null;
		try {
			if(null!=getSessionObj(request, "userId")){
				
				String userId = getSessionObj(request, "userId").toString();
				entity.setRecevieUserId(Long.parseLong(userId));
				entity.setState(0l);
				pageModel = msgRelService.getMyMsg(entity,PageUtil.get(page,rows));
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
		MsgRel entity = null;
		try {
			entity = msgRelService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	
	/**
	 * getMsgRelByUser:(根据接收者获取消息集合). <br/>
	 *
	 * @author wangguohong
	 * @param userId
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getMsgRelByUser(HttpServletRequest request){
		
		List<MsgRel> list = null;
		try {
			if(null != getSessionObj(request, "userId")){
				
				String userId = getSessionObj(request, "userId").toString();
				list = msgRelService.getMsgByUser(userId);
			}
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(list);
	}
	
	/**
	 * 导出excel.
	 * @author wangguohong
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(MsgRel entity,HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("user");
  		List<MsgRel> list = null;
		
		try {
			list =  msgRelService.getPageList(entity);
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
	public ExtJsObject addEntity(MsgRel entity){
	
		try {
			msgRelService.addEntity(entity);
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
	public ExtJsObject editEntity(MsgRel entity){
	
		try {
			msgRelService.editEntity(entity);
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
			msgRelService.delBatchEntity(ids);
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
			msgRelService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	

	/**
	 * delEntity:修改状态.
	 *
	 * @author wangguohong
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject upateState(long id,long state){

		MsgRel entity = null;
		try {
			entity = msgRelService.getEntityById(id);
			entity.setState(state);
			msgRelService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		
		return renderSuccess();
	}
}
