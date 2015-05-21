package com.momia.service;

import java.util.List;

import com.momia.entity.TopicGoodsAssort;

public interface TopicGoodsAssortService {

	public int insert(TopicGoodsAssort tgassort);
	
	public void insert(String[] ids, int tid, int flag);
	
	public int update(TopicGoodsAssort tgassort);
	
	public int delete(int id,int flag);
	
	public List<TopicGoodsAssort> findTopicGoodsAssorts(int tid);
}
