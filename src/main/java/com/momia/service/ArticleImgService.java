package com.momia.service;

import com.momia.entity.ArticleImg;

import java.util.List;

public interface ArticleImgService {

	public int insert(ArticleImg articleImg);

	public void insert(List<String> picStr,int arid, int flag);
	
	public int delete(int arid);
	
}
