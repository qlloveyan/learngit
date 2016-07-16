/**
 * Project Name:smcs<br>
 * File Name:CtrlMethodCtrl.java<br>
 * Package Name:<br>
 * Date:2014年11月19日  上午09:57:23<br>
 *
*/
package com.surfilter.system.ctrl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;

import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;

import javax.servlet.http.HttpServletRequest;

import com.surfilter.system.service.ExcelService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.filehandle.model.DateTemplateValidate;
import com.surfilter.framework.filehandle.service.FileHandleService;

import org.springframework.web.servlet.ModelAndView;

import com.surfilter.system.model.User;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.system.service.CtrlMethodService;
import com.surfilter.system.model.CtrlMethod;

/**
 * ClassName:CtrlMethodCtrl.java<br>
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
public class CtrlMethodCtrl extends BaseController implements ExcelOperate<CtrlMethod>{

	/**
	 * 注入ctrlMethodService.
	 */
	@Autowired
	private CtrlMethodService ctrlMethodService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<CtrlMethod> excelService;
	
	@Autowired
	private FileHandleService fileHandleService;
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(CtrlMethodCtrl.class);
	
	
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
	public Page<CtrlMethod> getPageModel(CtrlMethod entity,Integer page,Integer rows){
	
		Page<CtrlMethod> pageModel = null;
		try {
			pageModel = ctrlMethodService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		CtrlMethod entity = null;
		try {
			entity = ctrlMethodService.getEntityById(id);
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
	public ModelAndView exportExcel(final CtrlMethod entity,HttpServletRequest request) {
  		
  		final User user = (User) request.getSession().getAttribute("user");
		Page<CtrlMethod> pages = null;
		ModelAndView mv = null;
		if (entity.isIsback()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Page<CtrlMethod> pagestemp = null;
					ModelAndView mvtemp = null;
					int batchCount = FileUtil.calculateBatch(
							Integer.parseInt(entity.getRecordsum()),
							Integer.parseInt(entity.getBatchsize()));
					for (int i = 1; i <= batchCount; i++) {
						pagestemp = ctrlMethodService.getPageModel(
								entity,PageUtil.get(i,Integer.parseInt(entity.getBatchsize())));
						entity.setBatchnum(String.valueOf(i));
						if (i == batchCount) {
							entity.setBatchEnd(String.valueOf(true));
						} else {
							entity.setBatchEnd(String.valueOf(false));
						}
						mvtemp = excelService.exportExcel(entity,
								pagestemp.getRows(),
								entity.getProperties()[0].split(","),
								entity.getTitles()[0].split(","), user);
					}
				}
			});
			t.start();
			return null;
		} else {

			try {
				pages = ctrlMethodService.getPageModel(entity, PageUtil.get(
						Integer.parseInt(entity.getBatchnum()),
						Integer.parseInt(entity.getBatchsize())));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("分页信息失败", e);
			}

			mv = excelService.exportExcel(entity, pages.getRows(),
					entity.getProperties()[0].split(","),
					entity.getTitles()[0].split(","), user);
		}
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
	public ExtJsObject addEntity(CtrlMethod entity){
	
		try {
			ctrlMethodService.addEntity(entity);
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
	public ExtJsObject editEntity(CtrlMethod entity){
	
		try {
			ctrlMethodService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	/**
	 * equalMethod:验证重复信息.
	 *
	 * @author lenovo
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject equalMethod(String module, String className,String methodName){
		ExtJsObject result = null;
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("module", module);
			map.put("className", className);
			map.put("methodName", methodName);
			List<CtrlMethod> entity = ctrlMethodService.getEntity(map);
			if( entity.size() > 0 ){
				result = new ExtJsObject(true, "同一模块下存在相同的方法");
			}else{
				result = new ExtJsObject(false, "同一模块下不存在相同的方法");
			}
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return result;
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
			ctrlMethodService.delBatchEntity(ids);
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
			ctrlMethodService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	@RequestMapping
	@ResponseBody
	public ExtJsObject importData(HttpServletRequest request){
		ExtJsObject result = null;
		try {
			List<Object> listobj = fileHandleService.getTemplateList(request,CtrlMethod.class,
					new String[]{"module","className","classExplain","methodName","mothodExplain","funcModule","fiMenu","seMenu","methodType"},
					new Class[]{String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class},
					new DateTemplateValidate[]{});
			
			for( Object obj : listobj ){
				CtrlMethod entity = (CtrlMethod)obj;
				if( entity.getModule() != null && !"".equals(entity.getModule()) && //模块名称
					entity.getMethodName() != null && !"".equals(entity.getMethodName()) &&//方法名称
					entity.getClassName() != null && !"".equals(entity.getClassName()) &&//类名称
					entity.getFuncModule() != null && !"".equals(entity.getFuncModule()) &&//功能模块名称
					entity.getFiMenu() != null && !"".equals(entity.getFiMenu()) &&//一级菜单
					entity.getSeMenu() != null && !"".equals(entity.getSeMenu()) &&//二级菜单
					entity.getMethodType() != null && !"".equals(entity.getMethodType())//方法类别
						){
					ctrlMethodService.addEntity(entity);
				}
			}
			result = new ExtJsObject(true, "控制类方法字典导入execl表格成功!");
		}catch(Exception e){
			result = new ExtJsObject(false, "控制类方法字典导入execl表格出错!");
			e.printStackTrace();
		}
		return result;
	}
	
}
