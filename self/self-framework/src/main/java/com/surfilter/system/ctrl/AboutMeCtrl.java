/**
 * Project Name:smcs<br>
 * File Name:RoleCtrl.java<br>
 * Package Name:<br>
 * Date:2013年09月17日  下午08:34:00<br>
 *
*/
package com.surfilter.system.ctrl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.web.ctrl.BaseController;

/**
 * ClassName:RoleCtrl.java<br>
 * Function: TODO ADD FUNCTION.<br>
 * Reason:	 TODO ADD REASON.<br>
 * Date:     2013年09月17日  下午08:34:00<br>
 * 
 * @author   hongcheng
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping
public class AboutMeCtrl extends BaseController {

	
	@RequestMapping
	@ResponseBody
	public Map<String,String> getaboutMeMsg(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
		String version = FileUtil.getResouseValue("version","aboutme.properties");
		String buildid = FileUtil.getResouseValue("buildid","aboutme.properties");
		map.put("version", version);
		map.put("buildid", buildid);
		return map;
	}
	
	
}
