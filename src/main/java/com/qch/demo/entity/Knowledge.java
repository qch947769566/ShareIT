package com.qch.demo.entity;

import java.util.Date;

/**
 * 知识案例类
 * @author 94776
 *
 */
public class Knowledge {
	
	private String id; //编号
	
	private String title; //标题
	
	private String subhead; //简介
	
	private String content; //内容
	
	private String type; //类型，knowledge为知识，experience为经验案例
	
	private String publisher; //发布者
	
	private String modifier; //编辑者（最后编辑的人）
	
	private String state; //状态，如通过、待审核、被退回等
	
	private Date publishDate; //发布日期
	
	private Date modifyDate; //修改日期
	
	private Integer hot; //置顶
	
	private Integer readCount; //阅览量
	
	private Integer praise; //点赞量
	
	private Integer comment; //评论量
	
	private Integer used; //删除后为0，上线为1

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubhead() {
		return subhead;
	}

	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getPraise() {
		return praise;
	}

	public void setPraise(Integer praise) {
		this.praise = praise;
	}

	public Integer getComment() {
		return comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}
	
}
