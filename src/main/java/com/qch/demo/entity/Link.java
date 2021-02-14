package com.qch.demo.entity;

/**
 * 友情链接实体
 * @author user
 *
 */
public class Link {

	private Integer id; // 编号
	
	private String name; // 名称
	
	private String url; // 地址
	
	private Integer sort; // 排序

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
