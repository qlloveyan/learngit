/**
 * Project Name:my_pro
 * File Name:TestMain.java
 * Package Name:com.surfilter.self.jse.designer.iterator
 * Date:2016年4月7日下午7:33:52
 *
*/

package com.surfilter.self.jse.designer.iterator;
/**
 * ClassName:TestMain <br/>
 * Function: 迭代子模式. <br/>
 * Date:     2016年4月7日 下午7:33:52 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestMain {

	/**
	 * 迭代子模式：迭代器中持有原类的引用
	 */
	public static void main(String[] args) {
		MyCollection<User> users = new MyCollection<User>();
		User u1 = new User("ql");
		users.add(u1);
		User u2 = new User("zqh");
		users.add(u2);
		User u3 = new User("wxj");
		users.add(u3);
		
		MyIterator<User> uIterator = users.iterator();
		while (uIterator.hasNext()) {
			System.out.println(uIterator.next().toString());
		}
	}
}

