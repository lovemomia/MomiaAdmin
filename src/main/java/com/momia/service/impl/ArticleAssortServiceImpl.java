package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.ArticleAssort;
import com.momia.service.ArticleAssortService;

/**
 * 文章分类关联
 * @author duohongzhi
 *
 */
@Service
public class ArticleAssortServiceImpl implements ArticleAssortService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(ArticleAssort articleAssort) {
		String sql = "insert into t_article_assort (arid, assid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {articleAssort.getArid(), articleAssort.getAssid(),articleAssort.getStatus(),articleAssort.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public void insert(String[] ids, int aid, int flag) {
		if(flag == 0){
			delete(aid,1);
		}
		if(ids == null){}else{
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					insert(formArticleAssort(ids[i],aid));
				}
			}	
		}
		
		
	}

	public ArticleAssort formArticleAssort(String assid,int aid){
		ArticleAssort entity = new ArticleAssort();
		entity.setArid(aid);
		entity.setAssid(Integer.parseInt(assid));
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
	public int update(ArticleAssort articleAssort) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_article_assort where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_article_assort where arid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public List<ArticleAssort> findArticleAssorts(int aid) {
		List<ArticleAssort> ls = new ArrayList<ArticleAssort>();
		String sql = "select * from t_article_assort where arid = ? ";
		Object[] params = new Object[] {aid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			ArticleAssort entity = new ArticleAssort();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setArid(Integer.parseInt(list.get(i).get("arid").toString()));
			entity.setAssid(Integer.parseInt(list.get(i).get("assid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
}
