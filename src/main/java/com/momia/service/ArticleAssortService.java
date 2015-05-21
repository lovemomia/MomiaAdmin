package com.momia.service;

import java.util.List;

import com.momia.entity.ArticleAssort;

public interface ArticleAssortService {

	public int insert(ArticleAssort articleAssort);
	
	public void insert(String[] ids, int aid, int flag);
	
	public int update(ArticleAssort articleAssort);
	
	public int delete(int id, int flag);
	
	public List<ArticleAssort> findArticleAssorts(int aid);
}
