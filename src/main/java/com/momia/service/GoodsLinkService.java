package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.GoodsLink;

public interface GoodsLinkService {

	public List<GoodsLink> findGoodsLinkByGid(int gid);
	public int insert(GoodsLink goodsLink);
	public int update(GoodsLink goodsLink);
	public int delete(int id);
	public GoodsLink formGoodsLink(HttpServletRequest req,int gid,int id);
	//public List<GoodsLink> findGoodsLinkById(int id);
	
	public GoodsLink findGoodsLinkById(int id);
}
