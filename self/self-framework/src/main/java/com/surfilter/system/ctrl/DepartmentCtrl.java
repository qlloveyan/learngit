/**
 * Project Name:smcs<br>
 * File Name:DepartmentCtrl.java<br>
 * Package Name:<br>
 * Date:2014年02月25日  下午07:43:23<br>
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
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.surfilter.system.service.ExcelService;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import org.springframework.web.servlet.ModelAndView;
import com.surfilter.system.model.User;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.system.service.DepartmentService;
import com.surfilter.system.model.Department;

/**
 * ClassName:DepartmentCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年02月25日  下午07:43:23<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class DepartmentCtrl extends BaseController implements ExcelOperate<Department>{

	/**
	 * 注入departmentService.
	 */
	@Autowired
	private DepartmentService departmentService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<Department> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(DepartmentCtrl.class);
	
	
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
	public Page<Department> getPageModel(Department entity,Integer page,Integer rows){
	
		Page<Department> pageModel = null;
		try {
			pageModel = departmentService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Department entity = null;
		try {
			entity = departmentService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * 导出excel.
	 * @author wangguohong
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(final Department entity,HttpServletRequest request) {
		
  		
  		final User user = (User) request.getSession().getAttribute("user");
		Page<Department> pages = null;
		ModelAndView mv = null;
		if (entity.isIsback()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Page<Department> pagestemp = null;
					ModelAndView mvtemp = null;
					int batchCount = FileUtil.calculateBatch(
							Integer.parseInt(entity.getRecordsum()),
							Integer.parseInt(entity.getBatchsize()));
					for (int i = 1; i <= batchCount; i++) {
						pagestemp = departmentService.getPageModel(
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
				pages = departmentService.getPageModel(entity, PageUtil.get(
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
	 * @author wangguohong
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(Department entity){
		entity.setPId((long)0);
		try {
			departmentService.addEntity(entity);
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
	public ExtJsObject editEntity(Department entity){
	
		try {
			departmentService.editEntity(entity);
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
			departmentService.delBatchEntity(ids);
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
			departmentService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
}
