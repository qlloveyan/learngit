/**
 * Project Name:my_pro
 * File Name:TestException.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年4月6日下午7:55:57
 *
*/

package com.surfilter.self.jse.base;
/**
 * ClassName:TestException <br/>
 * Function: 异常学习. <br/>
 * Date:     2016年4月6日 下午7:55:57 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestException {

	public static void main(String[] args) {
		TestException te = new TestException();
		try {
			throw(te.new MyException("继承Exception类"));
			
		} catch (MyException e) {
			e.printStackTrace();
		}
		try {
			throw(te.new MyExceptionTwo("继承Throwable类"));
		} catch (MyExceptionTwo e) {
			e.printStackTrace();
		}
	}
	
	//1、继承Exception类
	class MyException extends Exception{

		/**
		 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
		 * @since JDK 1.6
		 */
		private static final long serialVersionUID = -2248386558994970203L;
		
		public MyException(String msg){
			super(msg);
		}
	}
	
	//2、继承Throwable接口
	class MyExceptionTwo extends Throwable{
		
		/**
		 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
		 * @since JDK 1.6
		 */
		private static final long serialVersionUID = 2891481041130557028L;

		public MyExceptionTwo(String msg){
			super(msg);
		}
	}
}

