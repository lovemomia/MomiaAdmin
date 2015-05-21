package com.momia.service;

import java.util.List;

import com.momia.entity.TopicCrowd;

public interface TopicCrowdService {

	public int insert(TopicCrowd topicCrowd);
	
	public void insert(String[] ids,int tid, int flag);
	
	public int update(TopicCrowd topicCrowd);
	
	public int delete(int id, int flag);
	
	public List<TopicCrowd> findTopicCrowds(int tid);
	
}
