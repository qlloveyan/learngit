package com.surfilter.extjsexp.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		List<String> ls = new ArrayList<String>();
		ls.add("1");
		ls.add("2");
		ls.add("3");
		ls.add("4");
		ls.add("5");
		
		String[] ss = (String[]) ls.toArray();
		System.out.println(ss[1]);
	}
}
