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

import com.momia.entity.TopicGoods;
import com.momia.service.TopicGoodsService;

/**
 * 专题商品关联
 * @author duohongzhi
 *
 */
@Service
public class TopicGoodsServiceImpl implements TopicGoodsService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<TopicGoods> findTopicGoods(int tid) {
		String sql = "select * from t_topic_goods where tid = ? ";
		List<TopicGoods> ls = new ArrayList<TopicGoods>();
		Object[] params = new Object[] {tid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicGoods entity = new TopicGoods();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			entity.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
			entity.setOrdinal(Integer.parseInt(list.get(i).get("ordinal").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}

	public int insert(TopicGoods topicGoods) {
		String sql = "insert into t_topic_goods (gid, tid, ordinal, status, updateTime) values (?, ?, ?, ?, ?)";
		Object[] params = new Object[] {topicGoods.getGid(), topicGoods.getTid(), topicGoods.getOrdinal(),topicGoods.getStatus(),topicGoods.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public int update(TopicGoods topicGoods) {
		String sql = "update t_topic_goods set ordinal = ?, status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {topicGoods.getOrdinal(), topicGoods.getStatus(), topicGoods.getUpdateTime(),topicGoods.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}

	public void insert(String[] ids, int tid, int flag) {
		if(flag == 0){
			delete(tid,1);
		}
		if(ids.length > 0){
			for (int i = 0; i < ids.length; i++) {
				List<TopicGoods> ls = findTopicGoodslist(tid,ids[i]);
				if(ls.size() > 0){
					//待处理排序更新
				}else{
					insert(formTopicGood(ids[i],tid,i));
				}
				
			}
		}
	}

	public List<TopicGoods> findTopicGoodslist(int tid,String gid){
		String sql = "select * from t_topic_goods where tid = ? and gid = ? ";
		List<TopicGoods> ls = new ArrayList<TopicGoods>();
		Object[] params = new Object[] {tid,gid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicGoods entity = new TopicGoods();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			entity.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
			entity.setOrdinal(Integer.parseInt(list.get(i).get("ordinal").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
	public TopicGoods topicGoodsForm(ResultSet rs,TopicGoods entity){
		try {
			entity.setId(rs.getInt("id"));
			entity.setGid(rs.getInt("gid"));
			entity.setTid(rs.getInt("tid"));
			entity.setOrdinal(rs.getInt("ordinal"));
			entity.setStatus(1);
			entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public TopicGoods formTopicGood(String gid,int tid,int stri){
		TopicGoods entity = new TopicGoods();
		entity.setTid(tid);
		entity.setGid(Integer.parseInt(gid));
		entity.setOrdinal(stri+1);
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_topic_goods where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_topic_goods where tid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public int deletegoods(int tid, int gid) {
		String sql = "delete from t_topic_goods where tid = ? and gid = ? ";
		Object[] params = new Object[] {tid, gid};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

}
