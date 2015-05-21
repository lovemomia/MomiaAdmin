package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.Article;
import com.momia.entity.TopicArticle;

public interface ArticleService {

	public List<Article> findArticles();
	
	public Article findArticleById(int id);
	
	public int update(Article article);
	
	public int insert(Article article);
	
	public int delete(int id);
	
	public Article formArticle(HttpServletRequest req,int falg);
	
	public List<Article> findArticles(List<Article> articles,List<TopicArticle> tArticles);
	
	public List<Article> findArticles(List<TopicArticle> tArticles);
	
	public String[] findSrc(String contentStr);

	
}
