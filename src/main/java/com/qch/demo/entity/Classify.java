package com.qch.demo.entity;

/**
 * 分类实体
 * @author 94776
 *
 */
public class Classify {
	
	private String id; //编号
	
	private String name; //名字
	
	private String parentId; //父编号
	
	private String route; //路径
	
	private Integer order; //顺序

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
}
