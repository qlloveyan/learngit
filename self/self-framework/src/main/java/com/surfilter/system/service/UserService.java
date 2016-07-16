/**
 * Project Name:smcs<br>
 * File Name:UserService.java<br>
 * Package Name:com.smcs.framework.system.service<br>
 * Date:2013年09月18日  上午10:50:16<br>
 *
*/
package com.surfilter.system.service;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surfilter.framework.auth.PasswordEncoder;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.page.Page;
import com.surfilter.system.dao.RoleMapper;
import com.surfilter.system.dao.SysUnitMapper;
import com.surfilter.system.dao.UserMapper;
import com.surfilter.system.dao.UserRelUnitPositionDepartmentMapper;
import com.surfilter.system.model.Role;
import com.surfilter.system.model.SysUnit;
import com.surfilter.system.model.User;
import com.surfilter.system.model.UserRelUnitPositionDepartment;

/**
 * ClassName:UserService.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月18日  上午10:50:16<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
@Transactional
public class UserService {
	/**
	 *注入userRelUnitPositionDepartmentMapper
	 */
	@Autowired
	private UserRelUnitPositionDepartmentMapper userRelUnitPositionDepartmentMapper;
	/**
	 *注入userMapper.
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 注入roleMapper.
	 */
	@Autowired
	private RoleMapper roleMapper;
	/**
	 * 注入sysUnitMapper.
	 */
	@Autowired
	private SysUnitMapper sysUnitMapper;
	
	/**
	 * passwordEncoder: 注入密码机密类.
	 * @since JDK 1.6
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * getPageModel:分页查询.
	 *
	 * @author hongcheng
	 * @param entity 实体
	 * @param rowBounds 分页
	 * @return 分页查询结果
	 * @since JDK 1.6
	 */
	public Page<User> getPageModel(User entity,RowBounds rowBounds){
		Long[] idTemp = null;
		//设置用户单位类型
		if( entity.getParentId() != null && !"".equals(entity.getParentId()) ){
			SysUnit temp = new SysUnit();
			temp.setParentId(entity.getParentId());
			List<SysUnit> sysUnitList = sysUnitMapper.list(temp, new RowBounds());
			sysUnitList.add( sysUnitMapper.getEntityById( Long.parseLong( entity.getParentId() ) ) );
			if(sysUnitList.size() > 0){
				idTemp = new Long[sysUnitList.size()];
				int length = 0;
				for( SysUnit i : sysUnitList){
					idTemp[length] = i.getId();
					length++;
				}
			}else{
				idTemp = new Long[]{0l};
			}
			entity.setUnitIdTemp(idTemp);
		}
		//设置用户id
		if( entity.getRoleId() != null && !"".equals(entity.getRoleId()) ){
			Set<String> users = userMapper.getUserIdByUserRoles( Long.parseLong(entity.getRoleId()) );
			if(users.size() > 0){
				idTemp = new Long[users.size()];
				int length = 0;
				for( String i : users){
					idTemp[length] = Long.parseLong(i);
					length++;
				}
			}else{
				idTemp = new Long[]{0l};
			}
			entity.setIdTemp(idTemp);
		}
		Page<User> page = new Page<User>();
		long total = userMapper.count(entity);
		List<User> rows = userMapper.list(entity,rowBounds);
		
		
		for (User user : rows) {
			//获取用户的角色信息
			StringBuilder releNames = new StringBuilder();
			Set<String> roleList =  userMapper.getRoleNameByUserId(user.getId());
			for (String roleName : roleList) {
				releNames.append(roleName).append(";");
			}
			if(releNames.length() >0){
				releNames.deleteCharAt(releNames.length()-1);
			}
			user.setRoleName(releNames.toString());
		}
		
		
		page = new Page<User>(total, rows);
		return page;
	}
	/**
	 * getPageList:查询List.
	 *
	 * @author wangguohong
	 * @param entity 实体
	 * @return 查询List结果
	 * @since JDK 1.6
	 */
	public List<User> getPageList(User entity){
		List<User> rows = userMapper.list(entity);
		return rows;
	}
	
	
	public List<User> listAll(User entity){
		List<User> rows = userMapper.listAll(entity);
		return rows;
	}
	
	/**
	 * addEntity:新增实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @since JDK 1.6
	 */
	public void addEntity(User entity){
		//进行密码加密
		String pass = FileUtil.getResouseValue("defaultPass");
		entity.setUserPass(passwordEncoder.encode(pass));  // 暂时这样使用，待系统参数表完成后读取系统参数表
		userMapper.addEntity(entity);
		//设置部门信息
		UserRelUnitPositionDepartment ur = new UserRelUnitPositionDepartment();
		ur.setUnitId(entity.getUnitId());
		ur.setUserId(entity.getId());
		userRelUnitPositionDepartmentMapper.addEntity(ur);
	}
	/**
	 * editEntity:编辑实体.
	 *
	 * @author hongcheng
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public int editEntity(User entity){
		//密码加密
		if(entity.getUserPass()!=null){
			entity.setUserPass(passwordEncoder.encode(entity.getUserPass()));
		}
		int result = userMapper.editEntity(entity);
		//修改单位信息
		UserRelUnitPositionDepartment ur = userRelUnitPositionDepartmentMapper.getEntityByUserId(entity.getId());
		ur.setUnitId(entity.getUnitId());
		userRelUnitPositionDepartmentMapper.editEntity(ur);
		return result;
	}
	
	/**
	 * editEntity:编辑实体信息.
	 *
	 * @author tangbiao
	 * @param entity 实体信息
	 * @return 编辑结果
	 * @since JDK 1.6
	 */
	public void authRole(long userId, long[] roleId){
		userMapper.delUserRoleRef(userId);
		if(roleId != null){
			for (long r : roleId) {
				userMapper.authRole(userId, r);
			}
		}
	}
	
	/**
	 * getEntityById:通过实体ID查询实体信息.
	 *
	 * @author hongcheng
	 * @param id ID
	 * @return 实体信息
	 * @since JDK 1.6
	 */
	public User getEntityById(long id){
		User user = userMapper.getEntityById(id);
		if( user != null ){
			StringBuilder releNames = new StringBuilder();
			Set<String> roleList =  userMapper.getRoleNameByUserId(user.getId());
			for (String roleName : roleList) {
				releNames.append(roleName).append(";");
			}
			if(releNames.length() >0){
				releNames.deleteCharAt(releNames.length()-1);
			}
			user.setRoleName(releNames.toString());
		}
		return user;
	}
	
	public User getByCode(String code){
		List<User> users = userMapper.getEntityByCode(code,null);
		if( users != null && users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}
	public User caLogin(String code){
		List<User> users = userMapper.caLogin(code);
		if( users != null && users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * getEntityByCode:判断登录名称是否重复.
	 *
	 * @author tangbiao
	 * @param userCode 登录名称
	 * @return 存在登录名称返回 true，不存在则返回false
	 * @since JDK 1.6
	 */
	public boolean isExistsCode(String userCode,String userName){
		List<User> list = userMapper.getEntityByCode(userCode,userName);
		return !(null == list || list.isEmpty()); 
	}
	
	/**
	 * getEntityByCodeAndPasswd: 根据用户名和密码查询用户信息. <br/>
	 *
	 * @author Tuyan
	 * @param userCode	用户编码
	 * @param password	用户密码
	 * @return	用户信息
	 * @since JDK 1.6
	 */
	public User getEntityByCodeAndPasswd(String userCode, String password) {
		return userMapper.getEntityByCodeAndPasswd(userCode, password);
	}
	
	/**
	 * delEntity:通过实体ID删除实体信息，并删除用户和角色的关联关系.
	 *
	 * @author hongcheng
	 * @param id 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
	public int delEntity(long id){
		// 首先先删除用户和角色的关联关系
		userMapper.delUserRoleRef(id);
		//删除用户单位信息
		userRelUnitPositionDepartmentMapper.deleteByUserId(id);
		return userMapper.delEntity(id);
	}
	
	/**
	 * delBatchEntity:批量删除实体,并删除用户和角色的关联关系.
	 *
	 * @author hongcheng
	 * @param ids 实体ID
	 * @return 删除结果
	 * @since JDK 1.6
	 */
    public int delBatchEntity(long[] ids){
    	for (long id : ids) {
    		userMapper.delUserRoleRef(id);
    		//删除用户不单位信息
    		userRelUnitPositionDepartmentMapper.deleteByUserId(id);
		}
    	return userMapper.delBatchEntity(ids);
    }
    
    /**
	 * getSelectRoleByUserId:查询所有的角色信息，.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @return 用户对应的角色信息
	 * @since JDK 1.6
	 */
    public List<Role> getSelectRoleByUserId(long userId,long currentUserid){
    	// 1.查询出用户ID对应的所选的角色ID信息
    	List<String> selectedRoleIdlist = userMapper.getUserRolesByUserId(userId);
    	
    	// 2.查询出所有的角色信息
    	Role roleQuery = new Role();
    	if( userId != 0){//非超级管理员
    		roleQuery.setCreateUserId(currentUserid);
    	}
    	List<Role> roles = roleMapper.listNoPage(roleQuery);
    	
    	// 3.用description标记用户所选的角色ID，便于页面checkBox框是否选择渲染
    	if(roles != null){
    		for (Role role : roles) {
    			if(selectedRoleIdlist != null){
    				for (String str: selectedRoleIdlist) {
    	    			if(str.equals(role.getId().toString())){
    	    				role.setDescription("true");
    	    				break;
    	    			}else{
    	    				role.setDescription("false");
    	    			}
    	    		}
    			}else{
    				role.setDescription("false");
    			}
    		}
    	}
    	return roles;
    }

	/**
	 * reUserState:改变用户状态.
	 *
	 * @author tangbiao
	 * @param userId 用户ID
	 * @param userState 用户状态
	 * @return 
	 * @since JDK 1.6
	 */
	public void reUserState(long userId, long userState) {
		// 反转用户状态
		if(userState == 0){
			userState = 1;
		}else if(userState == 1){
			userState = 0;
		}
		userMapper.reUserState(userId, userState);
	}

	/**
	 * 
	 * checkOldPw:检测该用户的旧密码是否正确. <br/>
	 *
	 * @author tangbiao
	 * @param userId
	 * @param oldPw
	 * @return
	 * @since JDK 1.6
	 */
	public boolean checkOldPw(long userId, String oldPw) {
		boolean flag = false;
		User user = userMapper.getEntityById(userId);
		if(user != null && user.getUserPass().equals(passwordEncoder.encode(oldPw))){
			flag = true;
		}
		return flag;
	}
	
	public List<Role> getRoles(){
		return roleMapper.listNoPage(new Role());
	}
	
	
	public List<Role> getRoles(Role role){
		return roleMapper.listNoPage(role);
	}
	
	public List<Role> listjcjs(Role role){
		return roleMapper.listjcjs(role);
	}
	
	
	
	public List<SysUnit> getSysUnits(){
		SysUnit temp = new SysUnit();
		temp.setUnitLevel("0");
		return sysUnitMapper.list(temp,new RowBounds());
	}
	
}
