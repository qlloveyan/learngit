package com.surfilter.self.jse.designer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
* @ClassName: CompositeFactory  
* @Description: 组合模式
* @author quanli 
* @date 2016年3月20日 下午4:42:26  
*
 */
public class CompositeFactory {

	//组合模式,常用于处理树形结构
	public static void main(String[] args) {
		CompositeFactory cf = new CompositeFactory();
		Menu son = cf.new Menu();
		son.setName("son");
		
		
		Menu parent = cf.new Menu();
		parent.setName("parent");
		List<Menu> sons = new ArrayList<Menu>();
		sons.add(son);
		parent.setChildren(sons);
		
	}
	
	class Menu{
		private String name;
		private List<Menu> children;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Menu> getChildren() {
			return children;
		}
		public void setChildren(List<Menu> children) {
			this.children = children;
		}
	}
}
