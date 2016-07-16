package com.surfilter.smcs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AboutMe {
	public static void main(String[] args) throws IOException {
		if(args==null){
			System.out.println("參數為空！");
		}else{
			String version = "";
			String buildid ="";
			if(args!=null && args.length>0){
				version = args[0];
				buildid = args[1];
			}
			System.out.println(version);
			System.out.println(buildid);
			String aboutmepath=Class.class.getClass().getResource("/").getPath()+"aboutme.properties";
			System.out.println("关于版本说明文件路径："+aboutmepath);
			StringBuffer br = new StringBuffer();
			
//			version=v2.0.0.1
//			buildid=1231241234
//			desc=描述
			br.append("version="+version+"\n");
			br.append("buildid="+buildid+"\n");
			
			FileOutputStream fos = new FileOutputStream(aboutmepath);
			OutputStreamWriter out = new OutputStreamWriter(fos,"UTF-8"); 
			out.write(br.toString());
			out.flush();
			out.close();
			fos.flush();
			fos.close();
		}
	}
}
