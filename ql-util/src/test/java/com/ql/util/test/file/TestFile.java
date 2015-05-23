package com.ql.util.test.file;

import org.junit.Test;

import com.ql.util.file.FileUtil;

public class TestFile {

	@Test
	public void test(){
		try {
			System.out.println("文件内容：\n" + FileUtil.readFile("C:\\Users\\lenovo\\Desktop\\新建文本文档.txt","GBK"));
			
//			String str = "我就呵呵了\nadadawdda的\n\n\n\n\n\nawdawdaw的a大\n啊\n啊\nd啊a我dada 大    </br>\n";
//			FileUtil.createFile(str, "D:\\test", "我的测试文件夹.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
