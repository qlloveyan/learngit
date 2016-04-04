/**
 * Project Name:shutdown
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年2月18日下午7:59:24
 *
*/

package com.surfilter.self.jse;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.WeakHashMap;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年2月18日 下午7:59:24 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	public static void main(String[] args) {
		HashMap hm = new HashMap();
		HashSet hs = new HashSet();
		Hashtable ht = new Hashtable();
		
		WeakHashMap<String,String> whm = new WeakHashMap<String,String>();
		
		List list = new ArrayList();
		AbstractList al = new AbstractList<String>() {

			@Override
			public String get(int index) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		ArrayList all = new ArrayList();
		LinkedList ll = new LinkedList();
//		ht.put("trest", null);
		
		//字符串反转
		StringBuffer sb = new StringBuffer();
		sb.append("wertr");
		System.out.println(sb.reverse().toString());
		
		//数组反转采用apache commons-lang中的ArrayUtils类的reverse方法
		System.out.println("===========================================");
		//HashMap 和 LinkedHashMap 遍历
		HashMap<String, String> strHashMap = new HashMap<String, String>();//插入后采用无序输出
		LinkedHashMap<String, String> strLinkedMap = new LinkedHashMap<String, String>();//插入后采用原顺序输出
		
		for(int i = 0 ; i < 20 ; i++){
			String key = "key"+i;
			String value = "value"+i;
			strHashMap.put(key, value);
			strLinkedMap.put(key, value);
		}
		
		for(String key : strHashMap.keySet() ){
			System.out.println("key:"+key+";value:"+strHashMap.get(key));
		}
		System.out.println("===========================================");
		
		for(String key : strLinkedMap.keySet()){
			System.out.println("key:"+key+";value:"+strLinkedMap.get(key));
		}
		System.out.println("===========================================");
		TestMain tm = new TestMain();
		TreeMap<Student, Student> tMap = new TreeMap<Student, Student>();
		Student s1 = tm.new Student("ql",24);
		Student s2 = tm.new Student("zqh",26);
		Student s3 = tm.new Student("wxj",25);
		Student s4 = tm.new Student("fg",28);
		Student s5 = tm.new Student("zw",18);
		
		tMap.put(s1, s1);//采用TreeMap做特定排序遍历时,对应的类需要实现Compareable接口自定义规则
		tMap.put(s2, s2);
		tMap.put(s3,s3);
		tMap.put(s4,s4);
		tMap.put(s5,s5);
		
		//使用
		SortedMap<Student, Student> result = tMap.headMap(s3);
		for(Iterator iter = result.keySet().iterator() ; iter.hasNext() ; ){
			Student key = (Student)iter.next();
			System.out.println(key.toString());
		}
		
		//集合与数组间的转化
		String[] strArray = new String[10];
		Arrays.asList(strArray);
		
		/**
		 * 查找数组中是否包含某个元素
		 * 
		 * 1、数组转集合,然后采用contains查找
		 * 2、采用apache commons-lang 中的ArrayUtils类contains方法
		 */
		
	}
	
	//学生类
	class Student implements Comparable<Student>{
		private String name;
		private Integer age;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
		public Student(String name, Integer age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		@Override
		public int compareTo(Student student) {
			if( this.age > student.getAge() ){
				return 1;
			}else{
				return -1;
			}
		}
		
		@Override
		public String toString() {
			return "name:"+name+";age:"+age;
		}
		
	}
}

