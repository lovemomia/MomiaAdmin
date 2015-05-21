package com.momia.service;

import java.util.List;

import com.momia.entity.GoodsCrowd;

public interface GoodsCrowdService {

	public int insert(GoodsCrowd goodsCrowd);
	
	public void insert(String[] ids,int tid, int flag);
	
	public int update(GoodsCrowd goodsCrowd);
	
	public int delete(int id, int flag);
	
	public List<GoodsCrowd> findGoodsCrowds(int tid);
	
}
