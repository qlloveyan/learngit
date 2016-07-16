package com.surfilter.framework.web.converter;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * ClassName: DateConverter <br/>
 * date: 2013-5-14 下午3:33:07 <br/>
 *
 * @author Alex
 * @version 
 * @since JDK 1.6
 */
public class DateConverter implements WebBindingInitializer {

	/**
	 * @see org.springframework.web.bind.support.WebBindingInitializer#initBinder(org.springframework.web.bind.WebDataBinder, org.springframework.web.context.request.WebRequest)
	 */
	public void initBinder(final WebDataBinder binder, final WebRequest request) {

		binder.registerCustomEditor(Date.class, new MultipleDateEditor(
				"yyyy-MM-dd HH:mm:ss", new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd HH:mm", "yyyy-MM-dd" }, true));

		//加入String类型的参数转换器，如果String的值为空，那么将值转换为null
		binder.registerCustomEditor(String.class, new StringParameterEditor());
	}

}
