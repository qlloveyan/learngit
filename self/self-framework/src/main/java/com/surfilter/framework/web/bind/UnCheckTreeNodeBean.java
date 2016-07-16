package com.surfilter.framework.web.bind;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class UnCheckTreeNodeBean implements Serializable{

	private String id;
	private String code;
	private String text;
	private String iconCls;
	private Boolean leaf;
	private String state;
	private boolean expanded;
	private List<UnCheckTreeNodeBean> children;
	private Map<String,String> attributes;

	
	/**
	 * state.
	 *
	 * @return  the state
	 * @since   JDK 1.6
	 */
	public String getState() {
		return state;
	}



	/**
	 * state.
	 *
	 * @param   state    the state to set
	 * @since   JDK 1.6
	 */
	public void setState(String state) {
		this.state = state;
	}



	public UnCheckTreeNodeBean() {
	}

	

	public Map getAttributes() {
		return attributes;
	}



	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}



	public UnCheckTreeNodeBean(String text, String id, Boolean leaf,
			boolean expanded) {
		super();
		this.text = text;
		this.id = id;
		this.leaf = leaf;
		this.expanded = expanded;
	}

	public UnCheckTreeNodeBean(String id, String text, Boolean leaf) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<UnCheckTreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<UnCheckTreeNodeBean> children) {
		this.children = children;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
