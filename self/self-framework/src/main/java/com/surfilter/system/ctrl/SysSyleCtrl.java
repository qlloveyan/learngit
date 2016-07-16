/**
 * Project Name:smcs<br>
 * File Name:SysSyleCtrl.java<br>
 * Package Name:<br>
 * Date:2015年09月24日  上午10:44:06<br>
 *
*/
package com.surfilter.system.ctrl;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.ExcelOperate;
import com.surfilter.framework.page.Page;
import com.surfilter.framework.page.PageUtil;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.auth.UserAuthorization;
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.model.SysSyle;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;
import com.surfilter.system.service.FuncModuleService;
import com.surfilter.system.service.SysSyleService;

/**
 * ClassName:SysSyleCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年09月24日  上午10:44:06<br>
 * 
 * @author   zhangwei
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class SysSyleCtrl extends BaseController implements ExcelOperate<SysSyle>{
	public static final long DEFAULTUSERID = 0;
	/**
	 * 注入sysSyleService.
	 */
	@Autowired
	private SysSyleService sysSyleService;
	
	
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<SysSyle> excelService;
	
	/**
	 * 注入funcModuleService.
	 */
	@Autowired
	private FuncModuleService funcModuleService;
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SysSyleCtrl.class);
	
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author zhangwei
	 * @param entity 查询实体
	 * @param page 页数
	 * @param rows 每页行数
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public Page<SysSyle> getPageModel(SysSyle entity,Integer page,Integer rows){
	
		Page<SysSyle> pageModel = null;
		try {
			pageModel = sysSyleService.getPageModel(entity,PageUtil.get(page,rows));
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return pageModel;
	}
	
	/**
	 * getEntity:通过ID查询实体.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getEntity(long id){
	
		SysSyle entity = null;
		try {
			entity = sysSyleService.getEntityById(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
	}
	
	/**
	 * getMainInitEntity:初始化菜单图标.
	 *
	 * @author zhangwei
	 * @param 
	 * @return 查询结果
	 * @since JDK 1.6
	 */
    @RequestMapping
	@ResponseBody
	public ExtJsObject getMainInitEntity(HttpServletRequest request , FuncModule entity){
		List<FuncModule> entitys = null;
		try {
			entitys = funcModuleService.getFunCode();
		} catch (Exception e) {
			log.error("初始化主菜单失败",e);
		}
		return renderObject(entitys);
	}
    
	/**
	 * getEntity:通过查询条件查询实体.
	 *
	 * @author zhangwei
	 * @param 
	 * @return 查询结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getInitEntity(HttpServletRequest request , SysSyle entity){
		List<SysSyle> entitys = null;
		long userid = Long.parseLong(request.getSession().getAttribute("userId").toString()); 
		entity.setUserId(userid);
		try {
			entitys = sysSyleService.getInitEntity(entity);
		} catch (Exception e) {
			log.error("初始化图标",e);
		}
		return renderObject(entitys);
	}
	
	/**
	 * 导出excel.
	 * @author zhangwei
	 * @param entity 实体
	 * @return model
	 */
	@RequestMapping
	public ModelAndView exportExcel(final SysSyle entity,HttpServletRequest request) {
		
  		
  		final User user = (User) request.getSession().getAttribute("user");
		Page<SysSyle> pages = null;
		ModelAndView mv = null;
		if (entity.isIsback()) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					Page<SysSyle> pagestemp = null;
					ModelAndView mvtemp = null;
					int batchCount = FileUtil.calculateBatch(
							Integer.parseInt(entity.getRecordsum()),
							Integer.parseInt(entity.getBatchsize()));
					for (int i = 1; i <= batchCount; i++) {
						pagestemp = sysSyleService.getPageModel(
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
				pages = sysSyleService.getPageModel(entity, PageUtil.get(
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
	 * addEntity:新增实体用户logo信息.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @return 新增操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject addEntity(HttpServletRequest request,SysSyle entity){
		//菜单信息为空 2添加必须根据一级菜单来确定添加
		long userid = Long.parseLong(request.getSession().getAttribute("userId").toString()); 
		entity.setUserId(userid);
		try {
			Long id = getUserValidate(entity);
			if (id == null) {
			    sysSyleService.addEntity(entity);
			    
			    
				//初始化用户上下图标-取管理員的logo值
				SysSyle logoUd = new SysSyle();
				logoUd.setElementType("2");
				
				SysSyle adminLogoUd = new SysSyle();
				adminLogoUd.setUserId(DEFAULTUSERID);
				adminLogoUd.setElementType("2");
				List<SysSyle> LogoEntitys = sysSyleService.getInitEntity(adminLogoUd);
				if (LogoEntitys != null) {
					
					logoUd.setValue(LogoEntitys.get(0).getValue());
				}
				else {
					logoUd.setValue("js/sysui/images/logoimage/top-left_prdt.png");
				}
				logoUd.setUserId(userid);
				logoUd.setSyleType("1");
				sysSyleService.addEntity(logoUd);
					
				SysSyle backgroundUd = new SysSyle();
			    backgroundUd.setElementType("3");
				backgroundUd.setValue("deepblue");
				backgroundUd.setUserId(userid);
				backgroundUd.setSyleType("1");
				sysSyleService.addEntity(backgroundUd);
				 
				//初始化用户左右图标
				SysSyle logoLr = new SysSyle();
				logoLr.setElementType("2");
				if (LogoEntitys != null) {
					
					logoLr.setValue(LogoEntitys.get(0).getValue());
				}
				else {
					logoLr.setValue("js/sysui/images/logoimage/top-left_prdt.png");
				}
				logoLr.setUserId(userid);
				logoLr.setSyleType("2");
				sysSyleService.addEntity(logoLr);
				
				SysSyle backgroundLr = new SysSyle();
				backgroundLr.setElementType("3");
				backgroundLr.setValue("deepblue");
				backgroundLr.setUserId(userid);
			    backgroundLr.setSyleType("2");
				sysSyleService.addEntity(backgroundLr);
				
				//只有管理員能更改logo故設置着全表全部更新log圖標
				/*SysSyle entityLogo = new SysSyle();
				entityLogo.setElementType("2");
				entityLogo.setValue(entity.getValue());
				sysSyleService.editEntityLogo(entityLogo);*/
				
				UserAuthorization userAuth = getSessionObj(request, UserAuthorization.class, FrameworkGlobal.AUTHORIZATION_TOKEN);
				List<UnCheckTreeNodeBean> allMenus = userAuth.getUIMenus().getChildren();
				//循环判断用户是不是有对应的图标
				for (int i = 0 ;i < allMenus.size() ; i++) {
					if (!allMenus.get(i).getAttributes().get("funcCode").equals("COMM_MENU")) {
						//System.out.println(allMenus.get(i).getAttributes().get("funcCode"));
						if (allMenus.get(i).getAttributes().get("funcCode").equals("IndustryMgr")) {
						    SysSyle industryMgrUd = new SysSyle();
							industryMgrUd.setElementId("IndustryMgr");
							industryMgrUd.setElementType("1");
							industryMgrUd.setValue("js/sysui/images/industryMgr/industryMgr_2.png");
							industryMgrUd.setUserId(userid);
							industryMgrUd.setSyleType("1");
							sysSyleService.addEntity(industryMgrUd);
							 
							SysSyle industryMgrLr = new SysSyle();
							industryMgrLr.setElementId("IndustryMgr");
							industryMgrLr.setElementType("1");
							industryMgrLr.setValue("js/sysui/images/industryMgr/industryMgr_2.png");
							industryMgrLr.setUserId(userid);
							industryMgrLr.setSyleType("2");
							sysSyleService.addEntity(industryMgrLr); 
						}
						else if (allMenus.get(i).getAttributes().get("funcCode").equals("NS_NetSecurity")) {
							
							SysSyle nsNetSecurityUd = new SysSyle();
							nsNetSecurityUd.setElementId("NS_NetSecurity");
							nsNetSecurityUd.setElementType("1");
							nsNetSecurityUd.setValue("js/sysui/images/nsNetSecurity/nsNetSecurity_2.png");
							nsNetSecurityUd.setUserId(userid);
							nsNetSecurityUd.setSyleType("1");
							sysSyleService.addEntity(nsNetSecurityUd);
							 
							SysSyle nsNetSecurityLr = new SysSyle();
							nsNetSecurityLr.setElementId("NS_NetSecurity");
							nsNetSecurityLr.setElementType("1");
							nsNetSecurityLr.setValue("js/sysui/images/nsNetSecurity/nsNetSecurity_2.png");
							nsNetSecurityLr.setUserId(userid);
							nsNetSecurityLr.setSyleType("2");
							sysSyleService.addEntity(nsNetSecurityLr);
							
						}
						else if (allMenus.get(i).getAttributes().get("funcCode").equals("EmergencyCmd")) {
							SysSyle emergencyCmdUd = new SysSyle();
							emergencyCmdUd.setElementId("EmergencyCmd");
							emergencyCmdUd.setElementType("1");
							emergencyCmdUd.setValue("js/sysui/images/emergencyCmd/emergencyCmd_3.png");
							emergencyCmdUd.setUserId(userid);
							emergencyCmdUd.setSyleType("1");
							sysSyleService.addEntity(emergencyCmdUd);
							 
							SysSyle emergencyCmdLr = new SysSyle();
							emergencyCmdLr.setElementId("EmergencyCmd");
							emergencyCmdLr.setElementType("1");
							emergencyCmdLr.setValue("js/sysui/images/emergencyCmd/emergencyCmd_3.png");
							emergencyCmdLr.setUserId(userid);
							emergencyCmdLr.setSyleType("2");
							sysSyleService.addEntity(emergencyCmdLr);
						}
						else if (allMenus.get(i).getAttributes().get("funcCode").equals("InfoSecurity")) {
							SysSyle InfoSecurityUd = new SysSyle();
							InfoSecurityUd.setElementId("InfoSecurity");
							InfoSecurityUd.setElementType("1");
							InfoSecurityUd.setValue("js/sysui/images/infoSecurity/infoSecurity_2.png");
							InfoSecurityUd.setUserId(userid);
							InfoSecurityUd.setSyleType("1");
							sysSyleService.addEntity(InfoSecurityUd);
							 
						    SysSyle InfoSecurityLr = new SysSyle();
							InfoSecurityLr.setElementId("InfoSecurity");
							InfoSecurityLr.setElementType("1");
							InfoSecurityLr.setValue("js/sysui/images/infoSecurity/infoSecurity_2.png");
							InfoSecurityLr.setUserId(userid);
							InfoSecurityLr.setSyleType("2");
							sysSyleService.addEntity(InfoSecurityLr);
							
						}
						else if (allMenus.get(i).getAttributes().get("funcCode").equals("AuthorityMgr")) {
							SysSyle authorityMgrUd = new SysSyle();
							authorityMgrUd.setElementId("AuthorityMgr");
							authorityMgrUd.setElementType("1");
							authorityMgrUd.setValue("js/sysui/images/authorityMgr/authorityMgr_3.png");
							authorityMgrUd.setUserId(userid);
							authorityMgrUd.setSyleType("1");
							sysSyleService.addEntity(authorityMgrUd);
							 
							SysSyle authorityMgrLr = new SysSyle();
							authorityMgrLr.setElementId("AuthorityMgr");
							authorityMgrLr.setElementType("1");
							authorityMgrLr.setValue("js/sysui/images/authorityMgr/authorityMgr_3.png");
							authorityMgrLr.setUserId(userid);
							authorityMgrLr.setSyleType("2");
							sysSyleService.addEntity(authorityMgrLr);
						}
						
					}
				}
			}
			else {
				//根据图片選中更新圖標到表
				if ("2".equals(entity.getElementType())) {  //logo全表更新只有管理員可以更改logo
					SysSyle entityLogo = new SysSyle();
					entityLogo.setElementType("2");
					entityLogo.setValue(entity.getValue());
					sysSyleService.editEntityLogo(entityLogo);
				}
				else {
					entity.setId(id);
					sysSyleService.editEntity(entity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("分页信息失败",e);
		}
		return renderObject(entity);
		//return renderSuccess();
	}
	
	/**
	 * initMainScreen:初始化主屏选择情况
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @return 查询结果集
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject initMainScreen(HttpServletRequest request,SysSyle entity){
		List<SysSyle> entitys = null;
		SysSyle initEntity = null;
		long userid = Long.parseLong(request.getSession().getAttribute("userId").toString()); 
		entity.setUserId(userid);
		try {
			entitys = sysSyleService.getInitEntity(entity);
			if (entitys.size() == 0) 
				initEntity = new SysSyle();
			else 
				initEntity = entitys.get(0);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("初始化反显红框失败",e);
		}
		return renderObject(initEntity);
	}
	
	//查询用户是否logo样式方案，查询条件为syleType,userId,elementType
	@RequestMapping
	@ResponseBody
	public Long getUserValidate(SysSyle entity) {
		SysSyle sy = new SysSyle();
		sy.setUserId(entity.getUserId());
		sy.setSyleType(entity.getSyleType());
		sy.setElementType(entity.getElementType());
		sy.setElementId(entity.getElementId());
		try {
			List<SysSyle> list = sysSyleService.getPageList(sy);
			if (list.size() == 1) {
				return list.get(0).getId();
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("初始化用户信息失败",e);
		}
		return null;
	}	
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject editEntity(SysSyle entity){
	
		try {
			sysSyleService.editEntity(entity);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delBatchEntity:批量删除.
	 *
	 * @author zhangwei
	 * @param ids 需要删除的实体ID数组
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delBatchEntity(long[] ids){
	
		try {
			sysSyleService.delBatchEntity(ids);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * delEntity:删除实体信息.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject delEntity(long id){
	
		try {
			sysSyleService.delEntity(id);
		} catch (Exception e) {
			log.error("分页信息失败",e);
		}
		return renderSuccess();
	}
	
	/**
	 * initMainSlye:动态加载文件夹中的图标路径页面初始化.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 操作结果
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject initMainSlye(HttpServletRequest request, SysSyle entity){
		List<Map<String,Object>> mainList  = new ArrayList<Map<String,Object>>();
		try {
			//行业管理路径信息 industryMgr
			String industryMgrPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/industryMgr");
			List<String> industryMgrList  = getFile(request, industryMgrPath);
			Map<String,Object> industryMgrMap = new HashMap<String,Object>();
			industryMgrMap.put("hygl", industryMgrList);
			mainList.add(industryMgrMap);
			//信息安全的路径信息 infoSecurity
			String infoSecurityPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/infoSecurity");
			List<String> infoSecurityList = getFile(request, infoSecurityPath);
			Map<String,Object> infoSecurityMap = new HashMap<String,Object>();
			infoSecurityMap.put("xxaq", infoSecurityList);
			mainList.add(infoSecurityMap);
			//网络安全路径信息 nsNetSecurity
			String nsNetSecurityPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/nsNetSecurity");
			List<String> nsNetSecurityList = getFile(request, nsNetSecurityPath);
			Map<String,Object> nsNetSecurityMap = new HashMap<String,Object>();
			nsNetSecurityMap.put("wlaq", nsNetSecurityList);
			mainList.add(nsNetSecurityMap);
			//应急协查路径信息 emergencyCmd
			String emergencyCmdPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/emergencyCmd");
			List<String> emergencyCmdList  = getFile(request, emergencyCmdPath);
			Map<String,Object> emergencyCmdMap = new HashMap<String,Object>();
			emergencyCmdMap.put("yjxc", emergencyCmdList);
			mainList.add(emergencyCmdMap);
			//系统管理路径信息 authorityMgr
			String authorityMgrPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/authorityMgr");
			List<String> authorityMgrList = getFile(request, authorityMgrPath);
			Map<String,Object> authorityMgrMap = new HashMap<String,Object>();
			authorityMgrMap.put("xtgl", authorityMgrList);
			mainList.add(authorityMgrMap);
			//logo路径信息
			String logoPath = request.getSession().getServletContext().getRealPath("/js/sysui/images/logoimage");
			List<String> logoList  = getFile(request, logoPath);
			Map<String,Object> logoMap = new HashMap<String,Object>();
			logoMap.put("logo", logoList);
			mainList.add(logoMap);
			
			if ("1".equals(entity.getSyleType())) {
				//皮肤的路径信息 上下 ,文件夹的个数显现数量多少
				String skinUpAndDownPath = request.getSession().getServletContext().getRealPath("/js/sysui/css/ud");
				List<String> skinUpAndDownList  = getDirectory(request, skinUpAndDownPath,"1");
				Map<String,Object> skinUpAndDownMap = new HashMap<String,Object>();
				skinUpAndDownMap.put("skinUpAndDown", skinUpAndDownList);
				mainList.add(skinUpAndDownMap);
			}
			if ("2".equals(entity.getSyleType())) {
				//皮肤的路径信息 左右，文件夹的个数显现数量多少
				String skinLeftAndRightPath = request.getSession().getServletContext().getRealPath("/js/sysui/css/lr");
				List<String> skinLeftAndRightList  = getDirectory(request, skinLeftAndRightPath,"2");
				Map<String,Object> skinLeftAndRightMap = new HashMap<String,Object>();
				skinLeftAndRightMap.put("skinLeftAndRight", skinLeftAndRightList);
				mainList.add(skinLeftAndRightMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("动态加载文件夹中的图标路径页面初始化失败",e);
		}
		return renderObject(mainList);
	}
	
	//只读到当前目录文件,暂不递归
	public List<String> getFile(HttpServletRequest request,String path){ 
		//Map<String,String> pngMap = new HashMap<String,String>();
		List<String> pngList = new ArrayList<String>();
		String filePath = "";
        File file = new File(path);   
        File[] array = file.listFiles();
        if (array == null) 
        	return pngList;
        for (int i=0;i<array.length;i++) {   
            if(array[i].isFile()) {
                //System.out.println(array[i].getName());
            	filePath = array[i].getPath();
            	//读取多少个文件路径
            	pngList.add(filePath.replace(request.getSession().getServletContext().getRealPath("/"), ""));
            } /*else if(array[i].isDirectory()){   
            	pngList.add(getFile(request,array[i].getPath()).toString().replace(request.getSession().getServletContext().getRealPath("/"), ""));
            }*/
        }
		return pngList;   
    }
	
	//只读到当前目录,暂不递归
	public List<String> getDirectory(HttpServletRequest request,String path ,String flag){ 
		List<String> pngList = new ArrayList<String>();
		String filePath = "";
        File file = new File(path);   
        File[] array = file.listFiles();
        if (array == null) 
        	return pngList;
        for (int i=0;i<array.length;i++) {   
            if(array[i].isDirectory()) {
            	filePath = array[i].getPath();
            	//读取多少个文件路径
            	if ("1".equals(flag))
            	    pngList.add(filePath.substring(filePath.lastIndexOf(File.separator)+1));
            	else if ("2".equals(flag))
                    pngList.add(filePath.substring(filePath.lastIndexOf(File.separator)+1));
            	else 
            		pngList.add(filePath);
            }
        }
		return pngList;   
    }	
}
