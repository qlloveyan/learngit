/**
 * Project Name:my_pro
 * File Name:TestClone.java
 * Package Name:com.surfilter.self.jse.base
 * Date:2016年4月25日下午7:25:43
 *
*/

package com.surfilter.self.jse.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ClassName:TestClone <br/>
 * Function: 测试类的浅拷贝和深拷贝. <br/>
 * Date:     2016年4月25日 下午7:25:43 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestClone implements Cloneable,Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -3412367344394664874L;

	private int age;
	
	private String name;
	
	private TestClone teacher;
	
	public TestClone(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public TestClone(int age, String name,TestClone teacher) {
		super();
		this.age = age;
		this.name = name;
		this.teacher = teacher;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestClone getTeacher() {
		return teacher;
	}

	public void setTeacher(TestClone teacher) {
		this.teacher = teacher;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public TestClone deepClone() throws Exception{
		//写入流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(this);
		
		//写出流
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return (TestClone) ois.readObject();
	}
	
	public static void main(String[] args) throws Exception {
		TestClone tc = new TestClone(48,"老师");
		TestClone tc1 = new TestClone(24,"quanli",tc);
		TestClone tc2 = (TestClone) tc1.clone();
		tc2.teacher.name = "老师1";
		System.out.println(tc2.teacher.name+":" + tc2.teacher.age);
		System.out.println(tc1.teacher.name+":" + tc1.teacher.age);
		TestClone tc3 = (TestClone) tc1.deepClone();
		tc3.teacher.name = "老师2";
		System.out.println(tc3.teacher.name+":" + tc3.teacher.age);
		System.out.println(tc1.teacher.name+":" + tc1.teacher.age);
	}
}

