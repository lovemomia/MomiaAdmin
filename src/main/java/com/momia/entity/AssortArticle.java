package com.momia.entity;

import java.util.List;

public class AssortArticle {

	private int id;
	
	private String name;
	
	private int parentid;
	
	private int status;
	
	private String updateTime;
	
	private int flag;
	
	private List<AssortArticle> lsArticles;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<AssortArticle> getLsArticles() {
		return lsArticles;
	}

	public void setLsArticles(List<AssortArticle> lsArticles) {
		this.lsArticles = lsArticles;
	}

}
