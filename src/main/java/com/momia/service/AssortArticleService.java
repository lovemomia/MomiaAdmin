package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.ArticleAssort;
import com.momia.entity.AssortArticle;
import com.momia.entity.TopicArticleAssort;

public interface AssortArticleService {

	public List<AssortArticle> findAssortments();
		
	public AssortArticle findAssortmentById(int id);
	
	public int update(AssortArticle assort);
	
	public int insert(AssortArticle assort);
	
	public int delete(int id);
	
	public AssortArticle formAssortArticle(HttpServletRequest req,int falg);
	
	public List<AssortArticle> findAssortArticles(List<AssortArticle> entitys1,List<TopicArticleAssort> entitys2);
	
	public List<AssortArticle> findAssArticles(List<AssortArticle> entitys1,List<ArticleAssort> entitys2);
	
	public List<AssortArticle> findLevel(List<AssortArticle> list);
}
