package com.momia.service;

import java.util.List;

import com.momia.entity.FeedBack;

public interface FeedBackService {

	public FeedBack findFeedBackById(int id);
	public List<FeedBack> findFeedBacks();
	
}
