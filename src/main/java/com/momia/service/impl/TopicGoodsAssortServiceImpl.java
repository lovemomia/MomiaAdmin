package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.TopicGoodsAssort;
import com.momia.service.TopicGoodsAssortService;
/**
 * 商品专题分类关联
 * @author duohongzhi
 *
 */
@Service
public class TopicGoodsAssortServiceImpl implements TopicGoodsAssortService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insert(TopicGoodsAssort tgassort) {
		String sql = "insert into t_topic_goods_assort (tid, assid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {tgassort.getTid(), tgassort.getAssid(),tgassort.getStatus(),tgassort.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public void insert(String[] ids, int tid) {
		for (int i = 0; i < ids.length; i++) {
			insert(formTopicGoodsAssort(ids[i],tid));
		}
	}
	
	public void insert(String[] ids, int tid, int flag) {
		if(flag == 0){
			delete(tid,1);
		}
		if(ids == null){}else{
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					insert(formTopicGoodsAssort(ids[i],tid));
				}
			}
		}
		
	}

	public int update(TopicGoodsAssort tgassort) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id,int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_topic_goods_assort where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_topic_goods_assort where tid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}
	
	public TopicGoodsAssort formTopicGoodsAssort(String assid,int tid){
		TopicGoodsAssort topic = new TopicGoodsAssort();
		topic.setTid(tid);
		topic.setAssid(Integer.parseInt(assid));
		topic.setStatus(1);
		topic.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return topic;
	}
	
	public List<TopicGoodsAssort> findTopicGoodsAssorts(int tid) {
		List<TopicGoodsAssort> ls = new ArrayList<TopicGoodsAssort>();
		String sql = "select * from t_topic_goods_assort where tid = ? ";
		Object[] params = new Object[] {tid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicGoodsAssort entity = new TopicGoodsAssort();
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
