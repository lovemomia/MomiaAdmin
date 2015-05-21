package com.momia.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.TopicArticle;
import com.momia.service.TopicArticleService;

/**
 * 专题文章关联
 * @author duohongzhi
 *
 */
@Service
public class TopicArticleServiceImpl implements TopicArticleService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<TopicArticle> findTopicArticles(int tid) {
		String sql = "select * from t_topic_article where tid = ? ";
		List<TopicArticle> ls = new ArrayList<TopicArticle>();
		Object[] params = new Object[] {tid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicArticle entity = new TopicArticle();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			entity.setArid(Integer.parseInt(list.get(i).get("arid").toString()));
			entity.setOrdinal(Integer.parseInt(list.get(i).get("ordinal").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}

	public int insert(TopicArticle topicArticle) {
		String sql = "insert into t_topic_article (arid, tid, ordinal, status, updateTime) values (?, ?, ?, ?, ?)";
		Object[] params = new Object[] {topicArticle.getArid(), topicArticle.getTid(), topicArticle.getOrdinal(),topicArticle.getStatus(),topicArticle.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int update(TopicArticle topicArticle) {
		String sql = "update t_topic_article set ordinal = ?, status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {topicArticle.getOrdinal(), topicArticle.getStatus(), topicArticle.getUpdateTime(),topicArticle.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}
	
	public void insert(String[] ids, int tid, int flag) {
		if(flag == 0){
			delete(tid,1);
		}
		if(ids == null){}else{
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					List<TopicArticle> ls = findTopicArticlelist(tid,ids[i]);
					if(ls.size() > 0){
						//待处理排序更新
					}else{
						insert(formTopicArticle(ids[i],tid,i));
					}
					
				}
			}
		}
		
		
	}

	public List<TopicArticle> findTopicArticlelist(int tid,String arid){
		String sql = "select * from t_topic_article where tid = ? and arid = ? ";
		List<TopicArticle> ls = new ArrayList<TopicArticle>();
		Object[] params = new Object[] {tid,arid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicArticle entity = new TopicArticle();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			entity.setArid(Integer.parseInt(list.get(i).get("arid").toString()));
			entity.setOrdinal(Integer.parseInt(list.get(i).get("ordinal").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
	public TopicArticle topicArticleForm(ResultSet rs,TopicArticle entity){
		try {
			entity.setId(rs.getInt("id"));
			entity.setArid(rs.getInt("arid"));
			entity.setTid(rs.getInt("tid"));
			entity.setOrdinal(rs.getInt("ordinal"));
			entity.setStatus(1);
			entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public TopicArticle formTopicArticle(String arid,int tid,int stri){
		TopicArticle entity = new TopicArticle();
		entity.setTid(tid);
		entity.setArid(Integer.parseInt(arid));
		entity.setOrdinal(stri+1);
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_topic_article where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_topic_article where tid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}
	
	public int deletearticle(int tid, int arid) {
		String sql = "delete from t_topic_article where tid = ? and arid = ? ";
		Object[] params = new Object[] {tid, arid};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}
	
}
