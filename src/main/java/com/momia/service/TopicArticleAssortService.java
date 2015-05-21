package com.momia.service;

import java.util.List;

import com.momia.entity.TopicArticleAssort;

public interface TopicArticleAssortService {

	public int insert(TopicArticleAssort taassort);
	
	public void insert(String[] ids, int tid, int flag);
	
	public int update(TopicArticleAssort taassort);
	
	public int delete(int id, int flag);
	
	public List<TopicArticleAssort> findTopicArticleAssorts(int tid);
}
