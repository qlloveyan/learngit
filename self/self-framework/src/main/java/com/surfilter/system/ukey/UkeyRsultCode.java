/**
 * Project Name:lichen
 * File Name:UkeyRsultCode.java
 * Package Name:com.surfilter.system.ukey
 * Date:2014-1-2下午2:40:50
 *
*/

package com.surfilter.system.ukey;
/**
 * 认证返回结果码
 * ClassName:UkeyRsultCode <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-1-2 下午2:40:50 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public enum UkeyRsultCode {
	SUCCESS("认证通过", 1),
	FAIL("认证失败", 2);
	
   
    private String name;  
	private int index;
	public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
    // 构造方法  
    private UkeyRsultCode(String name, int index) {  
        this.name = name;  
        this.index= index;
    }  
    
    public static String getName(int index) {  
        for (UkeyRsultCode c : UkeyRsultCode.values()) {  
            if (c.getIndex() == index) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    //重写toString 
    @Override  
    public String toString() {  
        return this.name;  
    }  
}

