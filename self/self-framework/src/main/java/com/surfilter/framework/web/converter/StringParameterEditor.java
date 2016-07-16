/**
 * Project Name:smcs
 * File Name:StringParameterEditor.java
 * Package Name:com.smcs.core.web.converter
 * Date:2013年9月24日上午10:06:11
 *
*/

package com.surfilter.framework.web.converter;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

/**
 * ClassName:StringParameterEditor. <br/>
 * String类型参数转换，如果是空字符串的参数，那么转换为null.
 * Date:     2013年9月24日 上午10:06:11 <br/>
 * @author   Tuyan
 * @version  1.0.0
 * @since    JDK 1.6
 * @see 	 PropertyEditorSupport
 */
public class StringParameterEditor extends PropertyEditorSupport {

	/**
	 * Creates a new instance of StringParameterEditor.
	 *
	 */
	public StringParameterEditor() {
		super();
	}

	@Override
	public String getAsText() {
		return (String) getValue();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(StringUtils.trimWhitespace(text));
	}
	
}

