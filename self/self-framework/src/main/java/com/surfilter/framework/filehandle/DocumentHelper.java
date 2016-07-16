/**
 * Project Name:cannikin
 * File Name:DocumentHelper.java
 * Package Name:com.smcs.core.xml
 * Date:2013-5-30下午2:58:41
 * Copyright (c) 2013, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
*/

package com.surfilter.framework.filehandle;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * ClassName:DocumentHelper,用于解析mapper xml文件,将文件转为对象. <br/>
 * Date:     2013-5-30 下午2:58:41 <br/>
 * @author   Tkiyer
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DocumentHelper {

	/**
	 * Creates a new instance of DocumentHelper.
	 *
	 */
	private DocumentHelper() {
		super();
	}

	/**
	 * getAttributeValue:获得XML节点中属性的值. <br/>
	 *
	 * @author Tkiyer
	 * @param attrName	属性名称
	 * @param node	节点
	 * @return	属性值
	 * @since JDK 1.6
	 */
	public static String getAttributeValue(String attrName, Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return ((Element) node).getAttribute(attrName);
		}
		return "";
	}
	
	/**
	 * isTag:对比节点是不是要找的节点. <br/>
	 *
	 * @author Tkiyer
	 * @param node	对比节点
	 * @param tagName	节点标签名称
	 * @return true or false
	 * @since JDK 1.6
	 */
	public static boolean isTag(Node node, String tagName) {
		if (null == node || null == tagName || "".equals(tagName)) {
			return false;
		}
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return tagName.equals(node.getNodeName());
		}
		return false;
	}
}

