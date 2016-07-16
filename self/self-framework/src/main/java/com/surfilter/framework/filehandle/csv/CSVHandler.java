/**
 * Project Name:lichen
 * File Name:CSVHandler.java
 * Package Name:com.surfilter.framework.filehandle.csv
 * Date:2014-2-10下午4:05:39
 *
 */

package com.surfilter.framework.filehandle.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.surfilter.framework.FrameworkGlobal;
import com.surfilter.framework.filehandle.FileUtil;
import com.surfilter.framework.filehandle.excel.ExcelUtil;
import com.surfilter.framework.filehandle.excel.ShowTypeName;
import com.surfilter.framework.utils.DateUtils;
import com.surfilter.system.model.Msg;
import com.surfilter.system.model.MsgRel;
import com.surfilter.system.model.User;
import com.surfilter.system.service.MsgRelService;
import com.surfilter.system.service.MsgService;

/**
 * ClassName:CSVHandler cvs文件处理<br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-2-10 下午4:05:39 <br/>
 * 
 * @author wangguohong
 * @version
 * @since JDK 1.6
 * @see
 */
public class CSVHandler<T> {
	public static final String FILE_CSV_SUFFIX = ".csv";
	/**
	 * writeCsv:(写入csv文件). <br/>
	 * 
	 * @author wangguohong
	 * @param model
	 * @return
	 * @since JDK 1.6
	 */
	public synchronized String writeCsv(Map<String, Object> model,MsgService msgService,MsgRelService msgRelService,User user) {
		OutputStreamWriter os = null;
		BufferedWriter bw = null;
		try {
			int batchnum = Integer.parseInt(model.get(FrameworkGlobal.BATCHNUM).toString());
			
			List<T> listvalue = (List<T>) model.get(ExcelUtil.EXCEL_PRO_VALUES);
			List<String> titles = (List<String>) model.get(ExcelUtil.EXCEL_PRO_TITLES);
			//String csvFilePath = "D:/Alarm20101125.csv";
			String filepath = model.get(FrameworkGlobal.FULL_EXCEL_PATH).toString()+FILE_CSV_SUFFIX;
			File ff = new File(filepath);
			if(!ff.exists()){
			
			}
//			CsvWriter wr = new CsvWriter(filepath, ',',
//					Charset.forName("gbk"));
			//写入表头
			String[] head = new String[titles.size()];
			for(int i=0; i<titles.size(); i++){ 
				head[i] = titles.get(i);
			}
			os = new OutputStreamWriter(new FileOutputStream(ff,true),"GBK");
			bw = new BufferedWriter(os);
			if(batchnum==1){
				//wr.writeRecord(head);
				bw.write(CSVHandler.toArrString(head));
				bw.write("\r\n");
			}
			//写入内容
			List<String> properties = (List<String>) model.get(ExcelUtil.EXCEL_PRO_PROERTIES); //获取需要导出的字段属性
			String columnName = null; //属性名称
			Object value = null; //属性值
			for (int i=0; i<listvalue.size(); i++) {
				String[] contents = new String[properties.size()];
				T t = (T) listvalue.get(i);
				for (int j = 0; j < properties.size(); j++) {
					columnName = properties.get(j);
					if(columnName==null || columnName.equals("")){
						continue;
					}
					//判断如果属性有注解则用注解显示的名称作为属性名称
					Field field= null;
					//获取所有分类包含本身
					List<Class> listClasses = new ArrayList<Class>();
					listClasses = FileUtil.getClasses(t.getClass(), listClasses);
					//循环所有父类以及子类
					for(Class s : listClasses){
						Field[] fields = s.getDeclaredFields();
						for(Field f : fields){
							if(f.getName().equals(columnName)){
								field = f;
								break;
							}
						}
					}
					
					if(field!=null && null != field.getAnnotation(ShowTypeName.class)){
						columnName = field.getAnnotation(ShowTypeName.class).showName();
					}
					value = PropertyUtils.getProperty(t, columnName);
					if(value==null){
						value = "";
					}else{
						value = DateUtils.toMString(value);
					}
					contents[j] = value.toString();
				}
				//wr.writeRecord(contents);
				bw.append(CSVHandler.toArrString(contents));
				bw.write("\r\n");
				//bw.write(CSVHandler.toArrString(contents));
				//bw.write("\r\n");
			}
			bw.flush();
			bw.close();
			//wr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				
				
				boolean batchEnd = Boolean.parseBoolean(model.get(FrameworkGlobal.BATCHEND).toString());
				if(batchEnd){
					//把下载信息写入消息库
					Msg msg = new Msg();
					msg.setMsgType("1");
					msg.setBusinessType("1");
					String fileName = model.get(FrameworkGlobal.EXCEL_NAME).toString()+FILE_CSV_SUFFIX;
					msg.setMsgTitle(fileName);
					String filepathtemp = model.get(FrameworkGlobal.FULL_EXCEL_PATH).toString()+FILE_CSV_SUFFIX;
					msg.setMsgUrl(filepathtemp);
					msg.setMsgLevel(1l);
					msg.setCreateUser(user.getId().toString());
					msg.setCreateTime(new Date());
					msgService.addEntity(msg);
					
					//写入消息关系
					MsgRel msgRel = new MsgRel();
					Msg msgtemp = msgService.getMsgByTitle(fileName);
					if(msgtemp!=null){
						
						msgRel.setMsgId(msgtemp.getId());
						msgRel.setRecevieUserId(user.getId());
						msgRel.setSendUserId(user.getId());
						msgRel.setState(0l);
						msgRel.setOperateTime(new Date());
						msgRelService.addEntity(msgRel);
					}
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return null;
	}
	
	/**
	 * 
	 * toArrString:(数组转化成字符串). <br/>
	 *
	 * @author wangguohong
	 * @param arr
	 * @return
	 * @since JDK 1.6
	 */
	public static String toArrString(String[] arr){
		String temp = "";
		for(String s : arr){
			temp += "\""+s+"\""+",";
		}
		temp = temp.substring(0, temp.length()-1);
		return temp;
	}
	
	
	public static void main(String[] args) {
		String[] arr = new String[]{};
		System.out.println(CSVHandler.toArrString(arr));
	}
	
}
