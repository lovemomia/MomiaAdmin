package com.momia.service;

import java.util.List;

import com.momia.entity.TopicArticle;

public interface TopicArticleService {

	public List<TopicArticle> findTopicArticles(int tid);
	
	public int insert(TopicArticle topicArticle);
	
	public void insert(String[] ids,int tid, int flag);
	
	public int delete(int id, int flag);
	
	public int deletearticle(int tid, int arid);
	
}
