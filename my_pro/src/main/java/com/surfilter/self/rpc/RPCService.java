/**
 * Project Name:my_pro
 * File Name:RPCService.java
 * Package Name:com.surfilter.self.rpc
 * Date:2016年5月7日下午7:54:30
 *
*/

package com.surfilter.self.rpc;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * ClassName:RPCService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年5月7日 下午7:54:30 <br/>
 * 
 * @author quanli
 * @version
 * @since JDK 1.6
 * @see
 */
public class RPCService {
	
	/**
	 * 
	 * catac:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @throws 
	 * @author quanli
	 * @since JDK 1.6
	 */
	public void catac(){
		
	}

	public static void main(String[] args) {
		
		try {
			Scanner scan = new Scanner(System.in);
			int index = scan.nextInt();
			try {
				System.out.println(1/index);
			} catch (Exception e) {
				throw new Exception("我输入的参数有问题");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Throwable t = e.getCause();
			t.printStackTrace();
		}
		Set<String> stringSet = new HashSet<String>();
		stringSet.add("11");
		ThreadLocal<String> tstr = new ThreadLocal<>();
		tstr.set("11");
	}

}
