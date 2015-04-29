package com.ql.util.test.execl;

import org.junit.Test;

import com.ql.util.execl.ExeclUtil;

public class TestExecl {

	@Test
	public void test(){
		String filePath = "C:\\Users\\lenovo\\Desktop\\smcs_zj.xls";
		try {
			String[][] data = ExeclUtil.getData(filePath, 0);
			for( int i = 0 ; i < data.length ; i++ ){
				for( int j = 0 ; j < data[i].length ; j++ ){
					System.out.print(data[i][j] + "\t\t");
				}
				System.out.println();
			}
			
			System.out.println("写文件……");
			ExeclUtil.writeExcel(data, "C:\\Users\\lenovo\\Desktop", "test.xls");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
