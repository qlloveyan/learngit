/**
 * Project Name:smcs<br>
 * File Name:SysSyleService.java<br>
 * Package Name:ccom.surfilter.system.service<br>
 * Date:2015年09月24日  上午10:44:06<br>
 *
*/
package com.surfilter.system.service;
import com.surfilter.framework.page.Page;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.surfilter.system.model.SysSyle;
import com.surfilter.system.model.User;
import com.surfilter.system.SystemConstants;
import com.surfilter.system.ctrl.SysSyleCtrl;
import com.surfilter.system.dao.SysSyleMapper;

/**
 * ClassName:SysSyleService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2015年09月24日  上午10:44:06<br>0
 * 
 * @author   zhangwei
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class SysSyleService {
	/**
	 * DEFAULTUSERID: 切换皮肤初始化id.
	 * @since JDK 1.6
	 */
	public static final long DEFAULTUSERID = 0;
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(SysSyleService.class);
	/**
	 *注入sysSyleMapper
	 */
	@Autowired
	private SysSyleMapper sysSyleMapper;
	/**
	 * userService: 用户服务类.
	 * @since JDK 1.6
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author zhangwei
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<SysSyle> getPageModel(SysSyle entity,RowBounds rowBounds){
		Page<SysSyle> page = new Page<SysSyle>();
		long total = sysSyleMapper.count(entity);
		List<SysSyle> rows = sysSyleMapper.list(entity,rowBounds);
		page = new Page<SysSyle>(total, rows);
		return page;
	}
	
	/**
	 * @author 
	 * @param entity 实体
	 * @return 结果集
	 * @since JDK 1.6
	 */
	public Long getCount(SysSyle entity){
		Long total = sysSyleMapper.count(entity);
		return total;
	}
	
	/**
	 * getPageList:查询List.
	 *
	 * @author zhangwei
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<SysSyle> getPageList(SysSyle entity){
		List<SysSyle> rows = sysSyleMapper.list(entity);
		return rows;
	}
	
	/**
	 * getInitEntity:查询List.
	 *
	 * @author zhangwei
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<SysSyle> getInitEntity(SysSyle entity){
		List<SysSyle> rows = sysSyleMapper.list(entity);
		return rows;
	}
	
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(SysSyle entity){
		sysSyleMapper.addEntity(entity);
	}
	
	/**
	 * addEntity:用户初始化样式实体信息.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addLoginEntity(User user){
		List<String> listMenu = new ArrayList<String>(); //添加4个菜单元素菜单
		listMenu.add("IndustryMgr"); //行业管理 3
		listMenu.add("NS_NetSecurity");//网络安全 132167
		listMenu.add("InfoSecurity");//信息安全 2
		listMenu.add("EmergencyCmd");//应急协查 5
		listMenu.add("AuthorityMgr");//系统管理 6 
		listMenu.add("logo");//logo
		listMenu.add("skin");//皮肤
		listMenu.add("activate");//激活布局
		try {
			if ((!getUserValidate(user)) && (!getInitValidate(DEFAULTUSERID))) { //用户没有对应的user则初始化数据,并且没有初始化数据
		        for (String a : listMenu) {
		            if ("IndustryMgr".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId("IndustryMgr");
		        	    sy.setElementType("1");
		        	    sy.setValue("js/sysui/images/industryMgr/industryMgr_2.png"); 
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            if ("NS_NetSecurity".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId("NS_NetSecurity");
		        	    sy.setElementType("1");
		        	    sy.setValue("js/sysui/images/nsNetSecurity/nsNetSecurity_2.png");
		        	    sy.setUserId(DEFAULTUSERID);
		             	sy.setSyleType("2");
		             	this.addEntity(sy);
		            }
		            if ("InfoSecurity".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId("InfoSecurity");
		        	    sy.setElementType("1");
		        	    sy.setValue("js/sysui/images/infoSecurity/infoSecurity_2.png");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            if ("EmergencyCmd".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId("EmergencyCmd");
		        	    sy.setElementType("1");
		        	    sy.setValue("js/sysui/images/emergencyCmd/emergencyCmd_3.png");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            if ("AuthorityMgr".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId("AuthorityMgr");
		        	    sy.setElementType("1");
		        	    sy.setValue("js/sysui/images/authorityMgr/authorityMgr_3.png");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            //logo
		            if ("logo".equals(a)) {  //如果是超级管理员初始化logo
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId(null);        //菜单
		        	    sy.setElementType("2");       //logo
		        	    sy.setValue("js/sysui/images/logoimage/top-left_prdt.png");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            //皮肤
		            if ("skin".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId(null);   //菜单
		        	    sy.setElementType("3");  //背景图片
		        	    sy.setValue("deepblue");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setSyleType("2");
		        	    this.addEntity(sy);
		            }
		            //value 被激活的方式1:上下 2：左右
		            if ("activate".equals(a)) {
		        	    SysSyle sy = new SysSyle();
		        	    sy.setElementId(null);   //菜单
		        	    sy.setElementType("4");
		        	    sy.setUserId(DEFAULTUSERID);
		        	    sy.setValue("2");  //默认取左右布局
		        	    sy.setSyleType(null);
		        	    this.addEntity(sy);
		            }
		         }   
		     } else {
                 //有对应的user样式版本，反显示
		    	 //System.out.print("有对应的user反显样式成功");
		     }
		 } catch (Exception e) {
			 e.printStackTrace();
			log.error("插入数据失败",e);
		 }
	}
	//查询是否有初始化方案
	public boolean getInitValidate(long userid) {
		SysSyle entity = new SysSyle();
		entity.setUserId(userid);
		try {
			long count = this.getCount(entity);
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("初始化方案失败", e);
		}
		return false;
	}
	
	public boolean getUserValidate(User user) {
		SysSyle entity = new SysSyle();
		entity.setUserId(user.getId());
		try {
			long count = this.getCount(entity);
			if (count > 0) {
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("初始化用户信息失败",e);
		}
		return false;
	}	

	
	/**
	 * editEntity:编辑实体.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(SysSyle entity){
		return sysSyleMapper.editEntity(entity);
	}
	
	/**
	 * editEntityLogo:编辑实体.
	 *
	 * @author zhangwei
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntityLogo(SysSyle entity){
		return sysSyleMapper.editEntityLogo(entity);
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author zhangwei
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public SysSyle getEntityById(long id){
		return sysSyleMapper.getEntityById(id);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息.
	 *
	 * @author zhangwei
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		
		return sysSyleMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体.
	 *
	 * @author zhangwei
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	return sysSyleMapper.delBatchEntity(ids);
    }
    
    /**
     * 
    * @Title: getPicbyUserid 
    * @Description: TODO(获取图标) 
    * @param userid
    * @param funcid
    * @return String    返回类型 
    * @throws 
    * @author wangguohong
     */
    public String getPicbyUserid(long userid,String funcode){
    	String result = "";
    	SysSyle entity = new SysSyle();
    	entity.setUserId(userid);
    	entity.setElementId(funcode);
    	entity.setElementType("1"); //1表示菜单图标
    	entity.setSyleType(getlayoutStle(userid));
    	List<SysSyle> list = sysSyleMapper.list(entity);
    	if(list!=null && list.size()>0){
    		result = list.get(0).getValue();
    	}else{
    		entity.setUserId(DEFAULTUSERID);
    		list = sysSyleMapper.list(entity);
    		if(list!=null && list.size()>0){
        		result = list.get(0).getValue();
        	}
    	}
    	return result;
    }
    
    /**
     * 
    * @Title: getlayoutStle 
    * @Description: TODO(获取用户布局方式) 
    * @param userid
    * @return String    返回类型 
    * @throws 
    * @author wangguohong
     */
    public String getlayoutStle(long userid){
    	String result = "";
    	SysSyle entity = new SysSyle();
    	entity.setUserId(userid);
    	entity.setElementType("4"); //4表示用户布局方式
    	List<SysSyle> list = sysSyleMapper.list(entity);
    	
    	
    	if(list!=null && list.size()>0){
    		result = list.get(0).getValue();
    	}else{
    		entity.setUserId(DEFAULTUSERID);
    		list = sysSyleMapper.list(entity);
    		if(list!=null && list.size()>0){
        		result = list.get(0).getValue();
        	}
    	}
    	
    	return result;
    }
    
    /**
     * 
    * @Title: getskin 
    * @Description: TODO(获取用户布局方式) 
    * @param userid
    * @return String    返回类型 
    * @throws 
    * @author wangguohong
     */
    public String getskin(long userid,String layout){
    	String result = "";
    	SysSyle entity = new SysSyle();
    	entity.setUserId(userid);
    	entity.setSyleType(layout);
    	entity.setElementType("3"); //3皮肤
    	List<SysSyle> list = sysSyleMapper.list(entity);
    	if(list!=null && list.size()>0){
    		result = list.get(0).getValue();
    	}else{
    		entity.setUserId(DEFAULTUSERID);
    		entity.setSyleType("2");
    		list = sysSyleMapper.list(entity);
    		if(list!=null && list.size()>0){
        		result = list.get(0).getValue();
        	}
    	}
    	return result;
    }
    /**
     * 
    * @Title: getLogo 
    * @Description: TODO(获取logo) 
    * @param userid
    * @return String    返回类型 
    * @throws 
    * @author wangguohong
     */
    public String getLogo(long userid,String layout){
    	String result = "";
    	SysSyle entity = new SysSyle();
    	entity.setUserId(userid);
    	entity.setSyleType(layout);
    	entity.setElementType("2"); //2表示logo
    	List<SysSyle> list = sysSyleMapper.list(entity);
    	if(list!=null && list.size()>0){
    		result = list.get(0).getValue();
    	}else{
    		//获取超级管理员的userid
    		User usertemp = new User();
    		entity.setUserId(null);
    		usertemp.setUserType("0");
    		List<User> listuser = userService.listAll(usertemp);
    		if(listuser!=null && listuser.size()>0){
    			User user = listuser.get(0);
    			entity.setUserId(user.getId());
    			list = sysSyleMapper.list(entity);
    			if(list!=null && list.size()>0){
    				result = list.get(0).getValue();
    			}else{
        			entity.setUserId(DEFAULTUSERID);
        			list = sysSyleMapper.list(entity);
        			if(list == null || list.size()<=0){
        				entity.setUserId(DEFAULTUSERID);
        				if(layout.equals("1")){
        					layout ="2";
        				}else{
        					layout ="1";
        				}
        				entity.setSyleType(layout);
            			list = sysSyleMapper.list(entity);
            			result = list.get(0).getValue();
        			}else{
        				
        				result = list.get(0).getValue();
        			}
        			
        		}
    		}
    	}
    	return result;
    }
}
