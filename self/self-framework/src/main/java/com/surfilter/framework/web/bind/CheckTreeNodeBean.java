/**
 * 带选择框的树的节点对象
 */
package com.surfilter.framework.web.bind;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CheckTreeNodeBean implements Serializable{

	private String id;
	private String code;
	private String text;
	private String iconCls;
	private Boolean leaf;
	private boolean expanded;
	private boolean checked;
	private List<CheckTreeNodeBean> children;

	public CheckTreeNodeBean() {
	}

	public CheckTreeNodeBean(String text, String id, Boolean leaf,
			boolean expanded) {
		super();
		this.text = text;
		this.id = id;
		this.leaf = leaf;
		this.expanded = expanded;
	}

	public CheckTreeNodeBean(String id, String text, Boolean leaf) {
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

	public List<CheckTreeNodeBean> getChildren() {
		return children;
	}

	public void setChildren(List<CheckTreeNodeBean> children) {
		this.children = children;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
