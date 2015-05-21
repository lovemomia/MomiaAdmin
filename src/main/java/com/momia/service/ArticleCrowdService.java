package com.momia.service;

import java.util.List;

import com.momia.entity.ArticleCrowd;

public interface ArticleCrowdService {

	public int insert(ArticleCrowd articleCrowd);
	
	public void insert(String[] ids,int arid, int flag);
	
	public int update(ArticleCrowd articleCrowd);
	
	public int delete(int id, int flag);
	
	public List<ArticleCrowd> findArticleCrowds(int arid);
	
}
