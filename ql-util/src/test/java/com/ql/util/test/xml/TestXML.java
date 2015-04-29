package com.ql.util.test.xml;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ql.util.file.FileUtil;
import com.ql.util.xml.XMLUtil;

public class TestXML {
	private static Map<String,Class<?>> map;
	
	static {
		if(map==null){
			map=new HashMap<String,Class<?>>();
			map.put("unit", Unit.class);
			map.put("user", User.class);
		}
	}

	@Test
	public void test(){
		try {
//			String xml = FileUtil.readFile( "E:\\workspace-maven\\ql-util\\src\\test\\java\\com\\ql\\util\\test\\xml\\User.xml" );
//			User u = (User) XMLUtil.xml2Bean(xml, map);
//			System.out.println( u.getName() );
			
			String xml = FileUtil.readFile( "E:\\workspace-maven\\ql-util\\src\\test\\java\\com\\ql\\util\\test\\xml\\Unit.xml","UTF-8" );
			Unit u = (Unit) XMLUtil.xml2Bean(xml, map);
			System.out.println( u.getName() + " --> " + u.getUsers().get(1).getName());
			
			System.out.println( XMLUtil.bean2xml(u, map) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
