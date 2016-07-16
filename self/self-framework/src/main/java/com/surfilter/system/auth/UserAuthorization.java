/**
 * Project Name:smcs
 * File Name:UserAuthorization.java
 * Package Name:com.smcs.framework.system.auth
 * Date:2013年9月23日上午9:41:14
 *
*/

package com.surfilter.system.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.surfilter.framework.auth.Authorization;
import com.surfilter.framework.web.bind.UnCheckTreeNodeBean;
import com.surfilter.system.model.FuncModule;
import com.surfilter.system.model.User;

/**
 * ClassName:UserAuthorization. <br/>
 * 用户授权信息.
 * Date:     2013年9月23日 上午9:41:14 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 Authorization
 */
@SuppressWarnings("serial")
public class UserAuthorization implements Authorization {
	
	/**
	 * user: 用户信息.
	 * @since JDK 1.6
	 */
	private User user;
	
	/**
	 * permissions: 用户权限.
	 * @since JDK 1.6
	 */
	private List<String> permissions = new ArrayList<String>();
	
	/**
	 * permissions: 用户页面元素权限.
	 * @since JDK 1.6
	 */
	private List<String> elementPermissions = new ArrayList<String>();
	
	
	/**
	 * permissionsMap: 用户权限map.
	 * @since JDK 1.6
	 */
	private Map<String,List<String>> permissionsMap = new HashMap<String,List<String>>();
	
	/**
	 * elementPermissionsMap: 用户页面元素权限map.
	 * @since JDK 1.6
	 */
	private Map<String,List<String>> elementPermissionsMap = new HashMap<String,List<String>>();
	
	/**
	 * userMenus: 用户菜单.
	 * @since JDK 1.6
	 */
	private List<FuncModule> userMenus = new ArrayList<FuncModule>();
	
	/**
	 * uiMenus: 界面显示的菜单.
	 * @since JDK 1.6
	 */
	private UnCheckTreeNodeBean uiMenus;
	
	
	/**
	 * 是否为超级管理员
	 */
	private boolean isSuperAdmin = false;
	

	/**
	 * Creates a new instance of UserAuthorization.
	 *
	 * @param user	用户
	 */
	public UserAuthorization(User user) {
		this.user = user;
	}
	
	@Override
	public User getSubject() {
		return this.user;
	}

	

	@Override
	public List<String> getPermissions() {
		return this.permissions;
	}

	@Override
	public void addPermission(String permission) {
		this.permissions.add(permission);
	}

	
	
	@Override
	public void addPermissions(List<String> permissions) {
		this.permissions.addAll(permissions);
	}
	
	@Override
	public void addPermissions(Map<String,List<String>> permissionMap) {
		this.permissionsMap = permissionMap;
	}
	

	/**
	 * userMenus.
	 *
	 * @return  the userMenus
	 * @since   JDK 1.6
	 */
	public List<FuncModule> getUserMenus() {
		return userMenus;
	}

	/**
	 * userMenus.
	 *
	 * @param   userMenus    the userMenus to set
	 * @since   JDK 1.6
	 */
	public void setUserMenus(List<FuncModule> userMenus) {
		this.userMenus = userMenus;
	}
	
	/**
	 * getUIMenus: 获取用户界面显示菜单. <br/>
	 *
	 * @author Tuyan
	 * @return	用户界面显示菜单
	 * @since JDK 1.6
	 */
	public UnCheckTreeNodeBean getUIMenus()	 {
		return this.uiMenus;
	}
	
	/**
	 * setUIMenus: 设置用户界面显示菜单. <br/>
	 *
	 * @author Tuyan
	 * @param uiMenus	用户界面显示菜单
	 * @since JDK 1.6
	 */
	public void setUIMenus(UnCheckTreeNodeBean uiMenus) {
		this.uiMenus = uiMenus;
	}

	@Override
	public void addElementPermissions(List<String> permission) {
		this.elementPermissions.addAll(permission);
	}

	
	@Override
	public void addElementPermissions(Map<String,List<String>> permission) {
		this.elementPermissionsMap = permission;
	}
	@Override
	public List<String> getElementPermissions() {
		return this.elementPermissions;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	@Override
	public boolean hasNoPermission(String permission) {
		PathMatcher pathMatcher = new AntPathMatcher();
		List<String> permissions  = this.permissions;
		for (String s : permissions) {
			if(pathMatcher.match(s, permission)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean hasPermission(String permission) {
		
		PathMatcher pathMatcher = new AntPathMatcher();
		List<String> permissions  = this.permissions;
		for (String s : permissions) {
			if(pathMatcher.match(s, permission)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Map<String, List<String>> getPermissionsMap() {
		// TODO Auto-generated method stub
		return this.permissionsMap;
	}
	
}

