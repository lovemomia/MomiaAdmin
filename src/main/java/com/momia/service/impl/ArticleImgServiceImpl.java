package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.ArticleImg;
import com.momia.service.ArticleImgService;

/**
 * 文章图片
 * @author duohongzhi
 *
 */
@Service
public class ArticleImgServiceImpl implements ArticleImgService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(ArticleImg articleImg) {
		String sql = "insert into t_article_img (name, picUrl, arid, status, updateTime) values (?, ?, ?, ?, ?)";
		//System.out.println(articleImg.getPicUrl());
		Object[] params = new Object[] {articleImg.getName(), articleImg.getPicUrl(), articleImg.getArid(),articleImg.getStatus(),articleImg.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public void insert(String [] picStr,int arid, int flag) {
		if(flag == 0){
			delete(arid);
			if(picStr.length > 0){
				for (int i = 0; i < picStr.length; i++) {
					if(picStr[i] != null){
						if(picStr[i] != null){
							insert(formArticleImg(picStr[i],arid));
						}
					}
				}
			}
		}else if(flag == 1 && picStr.length > 0){
			for (int i = 0; i < picStr.length; i++) {
				if(picStr[i] != null){
					insert(formArticleImg(picStr[i],arid));
				}
			}
		}else{
			if(picStr.length > 0 ){
				for (int i = 0; i < picStr.length; i++) {
					if(picStr[i] != null){
						insert(formArticleImg(picStr[i],arid));
					}
				}
			}
		}
		
	}
	
	public int update(ArticleImg articleImg) {
		String sql = "update t_article_img set picUrl = ?, status = ?, updateTime = ? where arid = ? ";
		
		Object[] params = new Object[] {articleImg.getPicUrl(), articleImg.getStatus(),articleImg.getUpdateTime(),articleImg.getArid()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}
	
	public int delete(int arid) {
		String sql = "delete from t_article_img where arid = ? ";
		Object[] params = new Object[] {arid};					
		int flag = jdbcTemplate.update(sql,params);			
		return flag;
	}
	
	public ArticleImg formArticleImg(String picUrl,int arid){
		ArticleImg entity = new ArticleImg();
		entity.setPicUrl(picUrl);
		entity.setName("图片名称");
		entity.setArid(arid);
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
}
