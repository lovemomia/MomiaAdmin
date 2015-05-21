package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.Topic;

public interface TopicService {

	public List<Topic> findTopics();
	
	public List<Topic> findTopics(int types);
	
	public Topic findTopicById(int id);
	
	public int update(Topic topic);
	
	public int insert(Topic topic);
	
	public int delete(int id);
	
	public Topic formTopic(HttpServletRequest req,int falg,int types);
	
	
}
