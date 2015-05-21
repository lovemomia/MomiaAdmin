package com.momia.service;

import com.momia.entity.ArticleImg;

public interface ArticleImgService {

	public int insert(ArticleImg articleImg);
	
	public void insert(String [] picStr,int arid, int flag);
	
	public int delete(int arid);
	
}
