package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.Goods;
import com.momia.entity.TopicGoods;

public interface GoodsService {

	public List<Goods> findGoods();
	
	public Goods findGoodsById(int id);
	
	public int update(Goods goods);
	
	public int insert(Goods goods);
	
	public int delete(int id);
	
	public Goods formGoods(HttpServletRequest req,int falg);
	
	public List<Goods> findgoods(List<TopicGoods> tGoods);
	
	public List<Goods> findgoods(List<Goods> lGoods, List<TopicGoods> tGoods);
}
