package com.taotao.common.pojo;
/**
* easyUI 树形控件节点属性
* @ClassName: EUTreeNode
* @Description: TODO
* @author Allen
* @date 2017年9月16日 上午11:14:50
*
 */
public class EUTreeNode {

	private Long id;
	private String text;
	private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
