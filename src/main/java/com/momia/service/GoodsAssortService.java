package com.momia.service;

import java.util.List;

import com.momia.entity.GoodsAssort;

public interface GoodsAssortService {

	public int insert(GoodsAssort  goodsAssort);
	
	public void insert(String[] ids, int gid, int flag);
	
	public int delete(int id, int flag);
	
	public int update(GoodsAssort goodsAssort);
	
	public List<GoodsAssort> findGoodsAssorts(int gid);
}
