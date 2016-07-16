/**
 * Project Name:lichen
 * File Name:RequestURLFilter.java
 * Package Name:com.surfilter.system.auth.filters
 * Date:2014年11月18日下午6:33:05
 *
*/

package com.surfilter.system.auth.filters;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.surfilter.framework.auth.AuthorizationException;
import com.surfilter.framework.utils.DateUtils;
import com.surfilter.framework.web.auth.WebAuthorizationFilter;
import com.surfilter.framework.web.auth.WebUnauthorizedToken;
import com.surfilter.system.model.AccessLog;
import com.surfilter.system.model.CtrlMethod;
import com.surfilter.system.model.User;
import com.surfilter.system.service.AccessLogService;
import com.surfilter.system.service.CtrlMethodService;
import com.surfilter.system.service.SysArgsService;

/**
 * ClassName:RequestURLFilter <br/>
 * Function: TODO 请求地址日志记录过滤器. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014年11月18日 下午6:33:05 <br/>
 * @author   lenovo
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class RequestURLFilter extends WebAuthorizationFilter {
	
	/**
	 *注入ctrlMethodService
	 */
	@Autowired
	private CtrlMethodService ctrlMethodService;
	/**
	 *注入AccessLogService
	 */
	@Autowired
	private AccessLogService accessLogService;
	
	/**
	 * 注入sysArgsService用于查询相关的配置.
	 */
	@Autowired
	private SysArgsService sysArgsService;
	
	//模块记录字典
	public static Map<String,String> modelxx = null;
	//获取日志展示时间限制参数
	public static Integer logSaveDate = 0;
	//临时变量，用于判断变量值是否修改
	String packageNameTemp ="";
	String moduelNameTemp = "";
	
	public void initData(){
//		if( logSaveDate == null ){//为空设置默认保存90天
			String temp = sysArgsService.getArgByKey("LOG_SAVE_DATE").getValue();
			if( temp != null && Integer.parseInt(temp) != logSaveDate){
				logSaveDate = Integer.parseInt(temp);
			}else{
				logSaveDate = 90;
			}
//		}
//		if( modelxx == null ){
			
			String packageName = sysArgsService.getArgByKey("PACKAGE_NAME").getValue();
			String moduelName = sysArgsService.getArgByKey("MODUEL_NAME").getValue();
			if( packageName != null && moduelName != null){
				if( "".equals(packageNameTemp) ){
					packageNameTemp = packageName;
				}
				if( "".equals(moduelNameTemp) ){
					moduelNameTemp = moduelName;
				}
				if( modelxx == null || ( !packageName.equals(packageName) || !moduelNameTemp.equals(moduelName) )){
					modelxx = new HashMap<String, String>();
					String[] ps = packageName.split(",");
					String[] ms = moduelName.split(",");
					if( ps.length == ms.length ){
						for(int i = 0 ; i < ps.length ; i++){
							modelxx.put(ps[i], ms[i]);
						}
					}else{
						log.error("系统参数模块说明以及包名说明没有一一对应,请检查!");
					}
				}
			}
//		}
	}
	
	/**
	 * 日志.
	 */
	private static Logger log = Logger.getLogger(RequestURLFilter.class);

	private String userName;
	private String ip;
	
	@Override
	protected void doInternalFilter(WebUnauthorizedToken token) throws AuthorizationException {
		try {
			//清除过期不保存的数据
			initData();
			clearDate();
			
			HttpServletRequest request = token.getRequest();
			String url = token.getURI();
			if( url.endsWith(".do") 
				&& !url.equals("/system/msgRel/getMsgRelByUser.do") //获取用户信息
				&& !url.equals("system/ukey/checkUkeyStatus.do") //UKey检测功能
				&& !url.equals("/system/redirect/redirectHomePage.do")){
				
				ip = getIpAddr(request);
				
				User user = (User) request.getSession().getAttribute("user");
				if( user != null ){
					userName = user.getUserName();
					if( userName == null || "".equals(userName) ){
						userName = user.getUserCode();
					}
				}
				
				//是方法请求,执行日志记录操作
				writeLog(userName,ip,url);
			}
		} catch (Exception e) {
			System.out.println("请求链接地址日志记录出错!");
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * writeLog:访问后台控制器方法日志记录方法. <br/>
	 * @author ql
	 * @param userName 访问用户
	 * @param ip 客户端IP
	 * @param url 请求链接
	 * @since JDK 1.6
	 */
	public void writeLog(String userName,String ip,String url) throws Exception{
		StringBuffer logMsg = new StringBuffer();
		String[] addr = url.split("/");
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("module", addr[1]);
		String methodName = addr[2];
		condition.put("className", methodName.substring(0, 1).toUpperCase() + methodName.substring(1, methodName.length()) + "Ctrl");
		condition.put("methodName", addr[3].split("\\.")[0]);
		List<CtrlMethod> entity = ctrlMethodService.getEntity(condition);
		
		if( entity != null && entity.size() > 0){
			//获取方法相应的说明,拼装日志
			logMsg.append("用户：[");
			logMsg.append(userName);
			logMsg.append("], 主机IP：[");
			logMsg.append(ip);
			logMsg.append("] 访问了：[" );
			logMsg.append( entity.get(0).getFuncModule() +" - " );
			logMsg.append(  entity.get(0).getFiMenu() +" - " );
			logMsg.append(  entity.get(0).getSeMenu() +" ]," );
			logMsg.append(  "进行了[" + entity.get(0).getMethodType() +"] 操作,操作说明["+ entity.get(0).getMothodExplain()+"]。");
//			logMsg.append("需要传递的参数为：[" + entity.get(0).getParamName() + " --> " + entity.get(0).getParamExplain() + "]");
			
			
			AccessLog accessLog = new AccessLog();
			accessLog.setLogMsg(logMsg.toString());
			accessLog.setIp(ip);
			accessLog.setUserName(userName);
			accessLog.setTime( new Date() );
			accessLog.setFuncModule( entity.get(0).getFuncModule() );
			accessLog.setFiMenu( entity.get(0).getFiMenu() );
			accessLog.setSeMenu( entity.get(0).getSeMenu() );
			accessLog.setMethodType( entity.get(0).getMethodType() );
			
			accessLogService.addEntity(accessLog);
		}else{
			log.debug("访问地址为：" + url+" 的请求未能在SY0044表中查找到相应记录,请及时添加相应记录映射!");
		}
	}
	
	/**
     * 从request对象中获取客户端真实的ip地址
     * @param request request对象
     * @return 客户端的IP地址
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception{
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    public void clearDate() throws Exception{
		//获取当前时间
		Date startTime = DateUtils.stringToShortDate( DateUtils.getBeforeDay( DateUtils.getStandardDateTime() , logSaveDate) );
		accessLogService.clearData(startTime);
	}
}

