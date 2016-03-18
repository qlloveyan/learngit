/**
 * Project Name:my_pro
 * File Name:PrototypeFactory.java
 * Package Name:com.surfilter.self.jse.designer
 * Date:2016年3月18日下午7:58:31
 *
*/

package com.surfilter.self.jse.designer.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ClassName:PrototypeFactory <br/>
 * Function: 原型模式,用于对象的共享 1、浅拷贝 2、深拷贝 Reason: TODO ADD REASON. <br/>
 * Date: 2016年3月18日 下午7:58:31 <br/>
 * 
 * @author quanli
 * @version
 * @since JDK 1.6
 * @see
 */
public class PrototypeFactory implements Cloneable, Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = -4855899539677036606L;

	private Integer num;
	private TestMain strList;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public TestMain getStrList() {
		return strList;
	}

	public void setStrList(TestMain strList) {
		this.strList = strList;
	}

	public PrototypeFactory(int num, TestMain strList) {
			super();
			this.num = num;
			this.strList = strList;
		}

	// 浅拷贝
	public PrototypeFactory shalldowClone() throws CloneNotSupportedException {
		return (PrototypeFactory) this.clone();
	}

	// 深拷贝,采用序列化 二进制的方式拷贝
	public PrototypeFactory deapClone() throws IOException, ClassNotFoundException {

		// 将当前对象写入二进制流
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		// 读取二进制流生成新对象
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return (PrototypeFactory) ois.readObject();
	}
}