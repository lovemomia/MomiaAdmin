package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.TopicArticleAssort;
import com.momia.service.TopicArticleAssortService;
/**
 * 文章专题分类关联
 * @author duohongzhi
 *
 */
@Service
public class TopicArticleAssortServiceImpl implements TopicArticleAssortService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(TopicArticleAssort taassort) {
		String sql = "insert into t_topic_article_assort (tid, assid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {taassort.getTid(), taassort.getAssid(),taassort.getStatus(),taassort.getUpdateTime()};
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
					insert(formTopicArticleAssort(ids[i],tid));
				}
			}
		}
		
	}

	public int update(TopicArticleAssort taassort) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id,int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_topic_article_assort where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_topic_article_assort where tid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}
	
	public TopicArticleAssort formTopicArticleAssort(String assid,int tid){
		TopicArticleAssort topic = new TopicArticleAssort();
		topic.setTid(tid);
		topic.setAssid(Integer.parseInt(assid));
		topic.setStatus(1);
		topic.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return topic;
	}

	public List<TopicArticleAssort> findTopicArticleAssorts(int tid) {
		List<TopicArticleAssort> ls = new ArrayList<TopicArticleAssort>();
		String sql = "select * from t_topic_article_assort where tid = ? ";
		Object[] params = new Object[] {tid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicArticleAssort entity = new TopicArticleAssort();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			entity.setAssid(Integer.parseInt(list.get(i).get("assid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
}
