/**
 * Project Name:smcs<br>
 * File Name:ReviewCtrl.java<br>
 * Package Name:<br>
 * Date:2015年05月18日  下午07:07:35<br>
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
import com.surfilter.scma.model.Review;
import com.surfilter.scma.service.ReviewService;

/**
 * ClassName:ReviewCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年05月18日  下午07:07:35<br>
 * 
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class ReviewCtrl extends BaseController{

	/**
	 * 注入reviewService.
	 */
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(ReviewCtrl.class);
	
	
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
	public Page<Review> getPageModel(Review entity,Integer page,Integer rows){
	
		Page<Review> pageModel = null;
		try {
			pageModel = reviewService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Review entity = null;
		try {
			entity = reviewService.getEntityById(id);
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
	public ExtJsObject addEntity(Review entity){
	
		try {
			reviewService.addEntity(entity);
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
	public ExtJsObject editEntity(Review entity){
	
		try {
			reviewService.editEntity(entity);
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
			reviewService.delBatchEntity(ids);
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
			reviewService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
}
