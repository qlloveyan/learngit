package com.ql.basepro.framework.model;

import java.io.Serializable;

public class FileHandle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件绝对路径
	 */
	private String resourcePath;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
}
