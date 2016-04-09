/**
 * Project Name:my_pro
 * File Name:MyCollection.java
 * Package Name:com.surfilter.self.jse.designer.iterator
 * Date:2016年4月7日下午7:56:30
 *
*/

package com.surfilter.self.jse.designer.iterator;
/**
 * ClassName:MyCollection <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午7:56:30 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MyCollection<T> {
	
	private final Integer ORIGIN_NUM = 1;
	
	private Integer index = 0;
	
	private Object[] obj = null;

	public MyCollection() {
		this.obj = new Object[ORIGIN_NUM];
	}
	
	public Object[] getObj() {
		return obj;
	}
	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public MyIterator<T> iterator(){
		return new MyIterator<T>(this);
	}
	
	public void add(T t){
//		if( index == obj.length - 1 && ( obj[index] != null )){//表示数组最后一个值有元素
		if( index == obj.length){//表示数组最后一个值有元素
			Object[] temp = new Object[obj.length*2];
			System.arraycopy(obj, 0, temp, 0, obj.length);
			obj = temp;
		}
		obj[index] = t;
		index++;
	}
	
}

