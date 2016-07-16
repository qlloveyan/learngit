/**
 * Project Name:smcs<br>
 * File Name:SafetyCtrl.java<br>
 * Package Name:<br>
 * Date:2014年03月11日  下午02:05:48<br>
 *
*/
package com.surfilter.system.ctrl;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.verification.CustomGenericManageableCaptchaService;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.Safety;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.SafetyService;

import sun.awt.image.ImageFormatException;

/**
 * ClassName:SafetyCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2014年03月11日  下午02:05:48<br>
 * 
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SafetyCtrl extends BaseController implements ExcelOperate<Safety>{

	/**
	 * 注入safetyService.
	 */
	@Autowired
	private SafetyService safetyService;
	
//	@Resource(name="imageCaptchaService")  
//	private ImageCaptchaService imageCaptchaService;  
	
	@Resource(name="captchaService")  
	private CustomGenericManageableCaptchaService captchaService;  
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<Safety> excelService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SafetyCtrl.class);
	
	
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
	public Page<Safety> getPageModel(Safety entity,Integer page,Integer rows){
	
		Page<Safety> pageModel = null;
		try {
			pageModel = safetyService.getPageModel(entity,PageUtil.get(page,rows));
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
	
		Safety entity = null;
		try {
			entity = safetyService.getEntityById(id);
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
	public ModelAndView exportExcel(final Safety entity,HttpServletRequest request) {
		
  		
  		final User user = (User) request.getSession().getAttribute("user");
		Page<Safety> pages = null;
		ModelAndView mv = null;
		if (entity.isIsback()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Page<Safety> pagestemp = null;
					ModelAndView mvtemp = null;
					int batchCount = FileUtil.calculateBatch(
							Integer.parseInt(entity.getRecordsum()),
							Integer.parseInt(entity.getBatchsize()));
					for (int i = 1; i <= batchCount; i++) {
						pagestemp = safetyService.getPageModel(
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
				pages = safetyService.getPageModel(entity, PageUtil.get(
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
	public ExtJsObject addEntity(Safety entity){
	
		try {
			safetyService.addEntity(entity);
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
	public ExtJsObject editEntity(Safety entity){
	
		try {
			safetyService.editEntity(entity);
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
			safetyService.delBatchEntity(ids);
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
			safetyService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * verification:(输入验证码). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @param model
	 * @throws ServletException
	 * @throws IOException
	 * @throws ImageFormatException
	 * @since JDK 1.6
	 */
//	@RequestMapping
//	public void verification(HttpServletRequest request,
//			HttpServletResponse response, Model model) throws ServletException,
//			IOException, ImageFormatException {
//		byte[] captchaChallengeAsJpeg = null;
//		request.getSession().setAttribute("message", "");
//		// the output stream to render the captcha image as jpeg into
//		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//		try {
//			// get the session id that will identify the generated captcha.
//			// the same id must be used to validate the response, the session id
//			// is a good candidate!
//			String captchaId = request.getSession().getId();
//			// call the ImageCaptchaService getChallenge method
//			BufferedImage challenge = imageCaptchaService
//					.getImageChallengeForID(captchaId, request.getLocale());
//			//import com.sun.image.codec.jpeg.JPEGCodec;
//			//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//			// a jpeg encoder
//			JPEGImageEncoder jpegEncoder = JPEGCodec
//					.createJPEGEncoder(jpegOutputStream);
//			jpegEncoder.encode(challenge);
//		} catch (IllegalArgumentException e) {
//		} catch (CaptchaServiceException e) {
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//		// flush it in the response
//		response.setHeader("Cache-Control", "no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", 0);
//		response.setContentType("image/jpeg");
//		ServletOutputStream responseOutputStream = response.getOutputStream();
//		//responseOutputStream.write(captchaChallengeAsJpeg);
//		responseOutputStream.flush();
//		responseOutputStream.close();
//	}

	@RequestMapping
	public void verification(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ServletException,
			IOException, ImageFormatException {
		request.getSession().setAttribute("message", "");
		String captchaId = request.getSession().getId();
		BufferedImage challenge = captchaService.getImageChallengeForID(
				captchaId, request.getLocale());

		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		ImageIO.write(challenge, "jpeg", responseOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
