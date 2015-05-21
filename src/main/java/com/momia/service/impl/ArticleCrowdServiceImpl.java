package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.ArticleCrowd;
import com.momia.service.ArticleCrowdService;

/**
 * 适用人群关联
 * @author duohongzhi
 *
 */
@Service
public class ArticleCrowdServiceImpl implements ArticleCrowdService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(ArticleCrowd articleCrowd) {
		String sql = "insert into t_article_crowd (arid, cid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {articleCrowd.getArid(), articleCrowd.getCid(),articleCrowd.getStatus(),articleCrowd.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int update(ArticleCrowd articleCrowd) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_article_crowd where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_article_crowd where arid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public void insert(String[] ids,int arid, int flag) {
		if(flag == 0){
			delete(arid,1);
		}
		if(ids == null){
		}else{
			if(ids.length > 0 ){
				for (int i = 0; i < ids.length; i++) {
					insert(formArticleCrowd(ids[i],arid));
				}
			}
		}
	}
	
	public ArticleCrowd formArticleCrowd(String cid,int arid){
		ArticleCrowd entity = new ArticleCrowd();
		entity.setArid(arid);
		entity.setCid(Integer.parseInt(cid));
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}

	public List<ArticleCrowd> findArticleCrowds(int arid) {
		String sql = "select * from t_article_crowd where arid = ? ";
		List<ArticleCrowd> arCrowds = new ArrayList<ArticleCrowd>();
		Object[] params = new Object[] {arid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			ArticleCrowd articleCrowd = new ArticleCrowd();
			articleCrowd.setId(Integer.parseInt(list.get(i).get("id").toString()));
			articleCrowd.setArid(Integer.parseInt(list.get(i).get("arid").toString()));
			articleCrowd.setCid(Integer.parseInt(list.get(i).get("cid").toString()));
			articleCrowd.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			articleCrowd.setUpdateTime(list.get(i).get("updateTime").toString());
			arCrowds.add(articleCrowd);
		}
		return arCrowds;
	}

}
