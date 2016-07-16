/**
 * Project Name:lichen
 * File Name:KeywordModel.java
 * Package Name:com.surfilter.framework.search
 * Date:2014-2-14上午10:37:09
 *
*/

package com.surfilter.framework.search;

import java.io.Serializable;

/**
 * 关键词模型
 * ClassName:KeywordModel <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-2-14 上午10:37:09 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class KeywordModel implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TYPE_MENU:TODO(菜单类型).
	 * @since JDK 1.6
	 */
	public static final int TYPE_MENU = 1;
	/**
	 * TYPE_DOMAIN:TODO(网站类型).
	 * @since JDK 1.6
	 */
	public static final int TYPE_DOMAIN = 2;
	/**
	 * simplePinyin:TODO(简写拼音).
	 * @since JDK 1.6
	 */
	private String simplePinyin;
	
	/**
	 * fullPinyin:TODO(全写拼音).
	 * @since JDK 1.6
	 */
	private String fullPinyin;
	
	/**
	 * word:TODO(词).
	 * @since JDK 1.6
	 */
	private String word;
	/**
	 * 请求地址
	 */
	private String url;
	/**
	 * 词类型（1，菜单）
	 */
	private int type;
	
	/**
	 * frameName:TODO(打开窗口名称).
	 * @since JDK 1.6
	 */
	private String frameName;
	
	/**
	 * 菜单id
	 */
	private Long id;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * frameName.
	 *
	 * @return  the frameName
	 * @since   JDK 1.6
	 */
	public String getFrameName() {
		return frameName;
	}
	/**
	 * frameName.
	 *
	 * @param   frameName    the frameName to set
	 * @since   JDK 1.6
	 */
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}
	/**
	 * simplePinyin.
	 *
	 * @return  the simplePinyin
	 * @since   JDK 1.6
	 */
	public String getSimplePinyin() {
		return simplePinyin;
	}
	/**
	 * simplePinyin.
	 *
	 * @param   simplePinyin    the simplePinyin to set
	 * @since   JDK 1.6
	 */
	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}
	/**
	 * fullPinyin.
	 *
	 * @return  the fullPinyin
	 * @since   JDK 1.6
	 */
	public String getFullPinyin() {
		return fullPinyin;
	}
	/**
	 * fullPinyin.
	 *
	 * @param   fullPinyin    the fullPinyin to set
	 * @since   JDK 1.6
	 */
	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}
	/**
	 * word.
	 *
	 * @return  the word
	 * @since   JDK 1.6
	 */
	public String getWord() {
		return word;
	}
	/**
	 * word.
	 *
	 * @param   word    the word to set
	 * @since   JDK 1.6
	 */
	public void setWord(String word) {
		this.word = word;
	}
	/**
	 * url.
	 *
	 * @return  the url
	 * @since   JDK 1.6
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url.
	 *
	 * @param   url    the url to set
	 * @since   JDK 1.6
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * type.
	 *
	 * @return  the type
	 * @since   JDK 1.6
	 */
	public int getType() {
		return type;
	}
	/**
	 * type.
	 *
	 * @param   type    the type to set
	 * @since   JDK 1.6
	 */
	public void setType(int type) {
		this.type = type;
	}
	
}

