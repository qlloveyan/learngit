/**
 * Project Name:smcs<br>
 * File Name:RoleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.surfilter.framework.search.KeywordModel;
import com.surfilter.framework.web.bind.ExtJsObject;
import com.surfilter.framework.web.ctrl.BaseController;

/**
 * 用于跳转页面
 * ClassName: RedirectCtrl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2013-10-22 下午6:42:44 <br/>
 *
 * @author wangguohong
 * @version 
 * @since JDK 1.6
 */
@Controller
@RequestMapping
public class RedirectCtrl extends BaseController {

	/**
	 * 
	 * redirectHomePage:(这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author wangguohong
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	public ModelAndView redirectHomePage(HttpServletResponse re,String path){
		ModelAndView mv = new ModelAndView();
		if(path.toLowerCase().startsWith("http://") || path.toLowerCase().startsWith("https://")){
			try {
				re.sendRedirect(path);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			return null;
		}else{
			
			mv.setViewName(path);
		}
		return mv;
	}
	
	/**
	 * 
	 * getkeyWordMenu:(获取菜单关键词). <br/>
	 *
	 * @author wangguohong
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping
	@ResponseBody
	public ExtJsObject getkeyWordMenu(HttpServletRequest request){
		List<KeywordModel> listkeymenu = (List<KeywordModel>)request.getSession().getAttribute("menuKeyword");
		return renderObject(listkeymenu);
	}
	
	@RequestMapping
	@ResponseBody
	public ExtJsObject redirect(){
		return new ExtJsObject(false, "权限错误");
	}
}
