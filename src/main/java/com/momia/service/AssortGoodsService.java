package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.AssortGoods;
import com.momia.entity.GoodsAssort;
//import com.momia.entity.TopicGoodsAssort;

public interface AssortGoodsService {

	public List<AssortGoods> findAssortments();
		
	public AssortGoods findAssortmentById(int id);
	
	public int update(AssortGoods assort);
	
	public int insert(AssortGoods assort);
	
	public int delete(int id);
	
	public AssortGoods formAssortGoods(HttpServletRequest req,int falg);
	
	//public List<AssortGoods> findAssortgoods(List<AssortGoods> entitys1,List<TopicGoodsAssort> entitys2);
	
	//public List<AssortGoods> findLevel(List<AssortGoods> list);
	
	public List<AssortGoods> findAssortGoods(List<AssortGoods> assortGoods,List<GoodsAssort> gAssort);
}
