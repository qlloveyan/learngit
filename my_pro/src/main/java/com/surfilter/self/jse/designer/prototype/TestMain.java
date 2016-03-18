/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.designer.prototype
 * Date:2016年3月18日下午8:17:21
 *
*/

package com.surfilter.self.jse.designer.prototype;

import java.io.IOException;
import java.io.Serializable;

/**
 * ClassName:TestMain <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月18日 下午8:17:21 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 2715862917882563769L;

	public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
		//定义原始对象
		PrototypeFactory sc = new PrototypeFactory(3,new TestMain());
		System.out.println("对象\t\t地址\t\tnum\t\t地址\t\tstrList\t\t地址");
		System.out.println("原始\t\t"+sc.hashCode()+"\t"+sc.getNum()+"\t\tstrList"+sc.getStrList().hashCode());
		System.out.println("原始\t\t"+sc+"\t"+sc.getNum()+"\t\tstrList"+sc.getStrList());
		PrototypeFactory sc1 = sc.shalldowClone();
		System.out.println("原始\t\t"+sc1.hashCode()+"\t"+sc1.getNum()+"\t\tstrList"+sc1.getStrList().hashCode());
		System.out.println("原始\t\t"+sc1+"\t"+sc1.getNum()+"\t\tstrList"+sc1.getStrList());
		PrototypeFactory sc2 = sc.deapClone();
		System.out.println("原始\t\t"+sc2.hashCode()+"\t"+sc2.getNum()+"\t\tstrList"+sc2.getStrList().hashCode());
		System.out.println("原始\t\t"+sc2+"\t"+sc2.getNum()+"\t\tstrList"+sc2.getStrList());
		
	}
}

