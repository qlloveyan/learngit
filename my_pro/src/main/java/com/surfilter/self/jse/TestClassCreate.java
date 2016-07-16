/**
 * Project Name:my_pro
 * File Name:TestClassCreate.java
 * Package Name:com.surfilter.self.jse
 * Date:2016年7月14日上午10:39:04
 *
*/

package com.surfilter.self.jse;
/**
 * ClassName:TestClassCreate <br/>
 * Function: 测试类的创建. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年7月14日 上午10:39:04 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestClassCreate {
	
	/**
	 * 在有些类中,可能存在多个属性,这样在构建这个对象的时候,满足多参数的构造函数就要写很多遍,因此为了避免这种情况,我们可以
	 * 采用如下的方式处理
	 */
	public static void main(String[] args) {
		TestClassCreate tcc = new TestClassCreate();
		//1、创建只有姓名的用户
		User u1 = tcc.new User("ql");
		System.out.println(u1.getName());
		/**
		 * 2、如果此时你想创建一个有姓名有年龄的用户,可能你需要去修改构造器,不推荐,比较不支持扩展和修改限制
		 * 	 解决办法,在set中添加对象返回,参考User类中的setAge方法
		 */
		User u2 = tcc.new User("zqh").setAge(26);//此时这种链式结构,可以很好解决这种问题
		System.out.println(u2.getName()+";"+u2.getAge());
	}
	
	class User{
		private String name;
		private Integer age;
		private Double total;
		private String sex = "男";//在有些时候,我们可以对相关属性做默认处理,兼顾未包含该参数的构造器
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public User setAge(Integer age) {
			this.age = age;
			return this;
		}
		public Double getTotal() {
			return total;
		}
		public void setTotal(Double total) {
			this.total = total;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		//在这里,一个人的名字是必须有的,所有可以先只写一个参数的构造器
		public User(String name) {
			super();
			this.name = name;
		}
		
	}
}

