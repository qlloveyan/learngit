/**
 * Project Name:smcs<br>
 * File Name:SynchSysMapper.java<br>
 * Package Name:com.surfilter.system.dao<br>
 * Date:2016年07月11日  下午03:25:44<br>
 *
*/
package com.surfilter.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.surfilter.system.model.AccessLog;
import com.surfilter.system.model.SysUnit;

/**
 * ClassName:SynchSysMapper.java<br>
 *  
 *  
 * Date:     2016年07月11日  下午03:25:44<br>
 * 
 * @author   quanli
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public interface SynchSysMapper {
    //新增用户单位信息
	public void addUnit(SysUnit sysunit);

	//批量插入用户操作日志信息
	public void addBatch(@Param("accessLogs")List<AccessLog> accessLogs);

}
