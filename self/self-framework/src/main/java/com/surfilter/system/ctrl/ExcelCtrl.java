/**
 * Project Name:smcs
 * File Name:ExcelCtrl.java
 * Package Name:com.surfilter.system.ctrl
 * Date:2013-10-25下午6:29:13
 *
*/

package com.surfilter.system.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.web.ctrl.BaseController;
import com.surfilter.system.model.User;
import com.surfilter.system.service.ExcelService;


/**
 * excel 导入导出功能
 * ClassName: ExcelCtrl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2013-10-25 下午6:29:38 <br/>
 *
 * @author wangguohong
 * @version 
 * @since JDK 1.6
 */
@Controller
@RequestMapping
public class ExcelCtrl extends BaseController{
	/**
	 * 注入ExcelService
	 */
	@Autowired
	private ExcelService<User> excelService;
	
	/**
	 * 
	 * exportExcel:(导出excel). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @param response
	 * @param Class 类名
	 * @param headers 导出数据属性集合
	 * @since JDK 1.6
	 */
	@RequestMapping
	public ModelAndView exportExcel(HttpServletRequest request,HttpServletResponse response,String Class,String ...headers){
		
		String[] titles = new String[3];
		titles[0]="编号";
		titles[1]="用户名";
		titles[2]="地址";
		List<User> userList = new ArrayList<User>();
		for(int i=0;i<10;i++){
			User user = new User();
			user.setId(Long.parseLong(String.valueOf(i)));
			user.setUserName("name"+i);
			user.setAddr("dizhi"+i);
			userList.add(user);
		}
		String[] properties = new String[3];
		properties[0] = "id";
		properties[1] = "userName";
		properties[2] = "addr";
	//	ModelAndView mv = excelService.exportExcel(userList, properties, titles);
		
		return null;
	}
	
}

