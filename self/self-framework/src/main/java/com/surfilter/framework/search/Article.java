/**
 * Project Name:lichen
 * File Name:Article.java
 * Package Name:com.surfilter.framework.search
 * Date:2014-3-3下午2:41:00
 *
*/

package com.surfilter.framework.search;


/**
 * ClassName:Article <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2014-3-3 下午2:41:00 <br/>
 * @author   wangguohong
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Article {
	private int id;
	private String title;
	private String content;
	public Article() {
	}
	
	/**
	 * Creates a new instance of Article.
	 *
	 * @param id
	 * @param title
	 * @param content
	 */
	
	public Article(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	/**
	 * id.
	 *
	 * @return  the id
	 * @since   JDK 1.6
	 */
	public int getId() {
		return id;
	}
	/**
	 * id.
	 *
	 * @param   id    the id to set
	 * @since   JDK 1.6
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * title.
	 *
	 * @return  the title
	 * @since   JDK 1.6
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * title.
	 *
	 * @param   title    the title to set
	 * @since   JDK 1.6
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * content.
	 *
	 * @return  the content
	 * @since   JDK 1.6
	 */
	public String getContent() {
		return content;
	}
	/**
	 * content.
	 *
	 * @param   content    the content to set
	 * @since   JDK 1.6
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
}

