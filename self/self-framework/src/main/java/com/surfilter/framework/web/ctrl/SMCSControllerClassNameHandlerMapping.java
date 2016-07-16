package com.surfilter.framework.web.ctrl;

import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.mvc.support.AbstractControllerUrlHandlerMapping;

import com.surfilter.framework.utils.StringUtils;

/**
 * ClassName: SMCSControllerClassNameHandlerMapping <br/>
 * date: 2013-5-14 上午10:28:25 <br/>
 *
 * @author Alex
 * @version 
 * @since JDK 1.6
 */
public class SMCSControllerClassNameHandlerMapping extends
		AbstractControllerUrlHandlerMapping {
	
	/**
	 * Creates a new instance of SMCSControllerClassNameHandlerMapping.
	 *
	 */
	public SMCSControllerClassNameHandlerMapping() {
		super();
	}

	/**
	 * Common suffix at the end of controller implementation classes.
	 * Removed when generating the URL path.
	 */
	private static final String CONTROLLER_SUFFIX = "Controller";
	
	/**
	 * PACKAGE_SUFFIX:Common suffix at the end of controller implementation classes's package.
	 * @since JDK 1.6
	 */
	private static final String PACKAGE_SUFFIX = "controller";
	
	/**
	 * FRAMEWORK_PACKAGE_SUFFIX:framework package prefix.
	 * @since JDK 1.6
	 */
	private static final String FRAMEWORK_PACKAGE_PREFIX = "com.cannikin";
	
	/**
	 * caseSensitive:case sensitive.
	 * @since JDK 1.6
	 */
	private boolean caseSensitive = false;
	
	/**
	 * frameworkPackageSuffixs: framework package prefix.
	 * @since JDK 1.6
	 */
	private String[] frameworkPackagePrefixs = new String[]{ FRAMEWORK_PACKAGE_PREFIX };
	
	/**
	 * actionClassSuffixs:action class suffix.
	 * @since JDK 1.6
	 */
	private String[] actionClassSuffixs = new String[]{ CONTROLLER_SUFFIX };
	
	/**
	 * actionPackageSuffixs:action package suffix.
	 * @since JDK 1.6
	 */
	private String[] actionPackageSuffixs = new String[]{ PACKAGE_SUFFIX };
	
	/**
	 * pathPrefix:url path prefix.
	 * @since JDK 1.6
	 */
	private String pathPrefix;
	
	/**
	 * basePackage:class base package.
	 * @since JDK 1.6
	 */
	private String basePackage;

	/**
	 * getFrameworkPackagePrefix:Get framework package prefix. <br/>
	 *
	 * @author Tkiyer
	 * @return	framework package prefix
	 * @since JDK 1.6
	 */
	public String[] getFrameworkPackagePrefixs() {
		return frameworkPackagePrefixs;
	}

	/**
	 * setFrameworkPackagePrefix:Set framework package prefix. <br/>
	 *
	 * @author Tkiyer
	 * @param frameworkPackagePrefixs	framework package prefixs
	 * @since JDK 1.6
	 */
	public void setFrameworkPackagePrefixs(String[] frameworkPackagePrefixs) {
		this.frameworkPackagePrefixs = frameworkPackagePrefixs;
		for (int i = 0; i < this.frameworkPackagePrefixs.length; i++) {
			String frameworkPackagePrefix = frameworkPackagePrefixs[i];
			if (StringUtils.hasLength(frameworkPackagePrefix) && !frameworkPackagePrefix.endsWith(".")) {
				frameworkPackagePrefix = frameworkPackagePrefix + ".";
			}
			frameworkPackagePrefixs[i] = frameworkPackagePrefix;
		}
	}

	/**
	 * setActionClassSuffixs:set action class suffix. <br/>
	 *
	 * @author Tkiyer
	 * @param actionClassSuffixs	action class suffix
	 * @since JDK 1.6
	 */
	public void setActionClassSuffixs(String[] actionClassSuffixs) {
		this.actionClassSuffixs = actionClassSuffixs;
	}

	/**
	 * getActionClassSuffixs:Get action class suffix array. <br/>
	 *
	 * @author Tkiyer
	 * @return	action class suffix array
	 * @since JDK 1.6
	 */
	public String[] getActionClassSuffixs() {
		return actionClassSuffixs;
	}

	/**
	 * setActionPackageSuffixs:set action package suffix array. <br/>
	 *
	 * @author Tkiyer
	 * @param actionPackageSuffixs	action package suffix array
	 * @since JDK 1.6
	 */
	public void setActionPackageSuffixs(String[] actionPackageSuffixs) {
		for (int i = 0; i < actionPackageSuffixs.length; i++) {
			String actionPackageSuffix = actionPackageSuffixs[i];
			if (StringUtils.hasLength(actionPackageSuffix) && !actionPackageSuffix.startsWith(".")) {
				actionPackageSuffixs[i] = "." + actionPackageSuffix;
			}
		}
		this.actionPackageSuffixs = actionPackageSuffixs;
	}

	/**
	 * getActionPackageSuffixs: Get action package suffix array. <br/>
	 *
	 * @author Tkiyer
	 * @return	action package suffix array
	 * @since JDK 1.6
	 */
	public String[] getActionPackageSuffixs() {
		return actionPackageSuffixs;
	}

	/**
	 * Set whether to apply case sensitivity to the generated paths,
	 * e.g. turning the class name "BuyForm" into "buyForm".
	 * <p>Default is "false", using pure lower case paths,
	 * e.g. turning the class name "BuyForm" into "buyform".
	 * 
	 * @param caseSensitive case sensitive
	 */
	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	/**
	 * 获取URL是否区分大小写.
	 *
	 * @return  the caseSensitive
	 * @since   JDK 1.6
	 */
	public final boolean isCaseSensitive() {
		return caseSensitive;
	}

	/**
	 * Specify a prefix to prepend to the path generated from the controller name.
	 * <p>Default is a plain slash ("/"). A path like "/mymodule" can be specified
	 * in order to have controller path mappings prefixed with that path, e.g.
	 * "/mymodule/buyform" instead of "/buyform" for the class name "BuyForm".
	 * 
	 * @param prefixPath url prefix path
	 */
	public void setPathPrefix(String prefixPath) {
		this.pathPrefix = prefixPath;
		if (StringUtils.hasLength(this.pathPrefix)) {
			if (!this.pathPrefix.startsWith("/")) {
				this.pathPrefix = "/" + this.pathPrefix;
			}
			if (this.pathPrefix.endsWith("/")) {
				this.pathPrefix = this.pathPrefix.substring(0, this.pathPrefix.length() - 1);
			}
		}
	}

	/**
	 * Set the base package to be used for generating path mappings,
	 * including all subpackages underneath this packages as path elements.
	 * <p>Default is <code>null</code>, using the short class name for the
	 * generated path, with the controller's package not represented in the path.
	 * Specify a base package like "com.mycompany.myapp" to include subpackages
	 * within that base package as path elements, e.g. generating the path
	 * "/mymodule/buyform" for the class name "com.mycompany.myapp.mymodule.BuyForm".
	 * Subpackage hierarchies are represented as individual path elements,
	 * e.g. "/mymodule/mysubmodule/buyform" for the class name
	 * "com.mycompany.myapp.mymodule.mysubmodule.BuyForm".
	 * 
	 * @param basePackage controller base package
	 */
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
		if (StringUtils.hasLength(this.basePackage) && !this.basePackage.endsWith(".")) {
			this.basePackage = this.basePackage + ".";
		}
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected String[] buildUrlsForHandler(String beanName, Class beanClass) {
		return generatePathMappings(beanClass);
	}

	/**
	 * Generate the actual URL paths for the given controller class.
	 * <p>Subclasses may choose to customize the paths that are generated
	 * by overriding this method.
	 * @param beanClass the controller bean class to generate a mapping for
	 * @return the URL path mappings for the given controller
	 */
	@SuppressWarnings("rawtypes")
	protected String[] generatePathMappings(Class beanClass) {
		StringBuilder pathMapping = buildPathPrefix(beanClass);
		String className = ClassUtils.getShortName(beanClass);
		String path = getEndWithSuffixPath(className, actionClassSuffixs);
		if (path.length() > 0) {
			if (this.caseSensitive) {
				pathMapping.append(path.substring(0, 1).toLowerCase()).append(path.substring(1));
			}
			else {
				pathMapping.append(path.toLowerCase());
			}
		}
		if (isMultiActionControllerType(beanClass)) {
			return new String[] {pathMapping.toString(), pathMapping.toString() + "/*"};
		}
		else {
			return new String[] {pathMapping.toString() + "*"};
		}
	}
	
	/**
	 * getEndWithSuffixPath:根据类后缀数组筛选出path的结果. <br/>
	 *
	 * @author Tkiyer
	 * @param className	class name
	 * @param suffixs	suffix array
	 * @return	path
	 * @since JDK 1.6
	 */
	private String getEndWithSuffixPath(String className, String[] suffixs) {
		String path = "";
		for (String suffix : suffixs) {
			if (className.endsWith(suffix)) {
				path = className.substring(0, className.lastIndexOf(suffix));
				break;
			}
		}
		return StringUtils.isEmpty(path) ? className : path;
	}

	/**
	 * Build a path prefix for the given controller bean class.
	 * @param beanClass the controller bean class to generate a mapping for
	 * @return the path prefix, potentially including subpackage names as path elements
	 */
	@SuppressWarnings("rawtypes")
	private StringBuilder buildPathPrefix(Class beanClass) {
		StringBuilder pathMapping = new StringBuilder();
		if (this.pathPrefix != null) {
			pathMapping.append(this.pathPrefix);
			pathMapping.append("/");
		} else {
			pathMapping.append("/");
		}
		if (this.basePackage != null) {
			String packageName = ClassUtils.getPackageName(beanClass);
			if (packageName.startsWith(this.basePackage)) {
				String subPackage = packageName.substring(this.basePackage.length()).replace('.', '/');
				pathMapping.append(this.caseSensitive ? subPackage : subPackage.toLowerCase());
				pathMapping.append("/");
			}
		} else {
			//在这里加入自己的URL映射逻辑规范
			pathMapping.append(StringUtils.buildPathByBeanClass(beanClass, frameworkPackagePrefixs, actionPackageSuffixs, caseSensitive));
		}
		return pathMapping;
	}
}
