/**
 * Project Name:my_pro
 * File Name:MyIterator.java
 * Package Name:com.surfilter.self.jse.designer.iterator
 * Date:2016年4月7日下午7:57:10
 *
*/

package com.surfilter.self.jse.designer.iterator;
/**
 * ClassName:MyIterator <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年4月7日 下午7:57:10 <br/>
 * @author   quanli
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class MyIterator<T> {

	private Integer index = 0;
	
	private MyCollection<T> coll;
	
	public MyIterator(MyCollection<T> coll) {
		super();
		this.index = 0;
		this.coll = coll;
	}

	public boolean hasNext(){
		if( index <= coll.getObj().length-1 && ( coll.getObj()[index] != null )){
			return true;
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T next() throws ClassCastException{
		return (T) coll.getObj()[index++];
	}
}

