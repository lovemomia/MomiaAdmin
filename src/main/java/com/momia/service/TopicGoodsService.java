package com.momia.service;

import java.util.List;

import com.momia.entity.TopicGoods;

public interface TopicGoodsService {

public List<TopicGoods> findTopicGoods(int tid);
	
	public int insert(TopicGoods topicGoods);
	
	public void insert(String[] ids,int tid, int flag);
	
	public int delete(int id, int flag);
	
	public int deletegoods(int tid, int gid);
}
