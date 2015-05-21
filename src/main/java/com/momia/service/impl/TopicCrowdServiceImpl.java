package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.TopicCrowd;
import com.momia.service.TopicCrowdService;

/**
 * 年龄标签关联
 * @author duohongzhi
 *
 */
@Service
public class TopicCrowdServiceImpl implements TopicCrowdService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(TopicCrowd topicCrowd) {
		String sql = "insert into t_topic_crowd (tid, cid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {topicCrowd.getTid(), topicCrowd.getCid(),topicCrowd.getStatus(),topicCrowd.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int update(TopicCrowd topicCrowd) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_topic_crowd where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_topic_crowd where tid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public void insert(String[] ids,int tid, int flag) {
		if(flag == 0){
			delete(tid,1);
		}
		if(ids == null){
		}else{
			if(ids.length > 0 ){
				for (int i = 0; i < ids.length; i++) {
					insert(formTopicCrowd(ids[i],tid));
				}
			}
		}
	}
	
	public TopicCrowd formTopicCrowd(String cid,int tid){
		TopicCrowd topicCrowd = new TopicCrowd();
		topicCrowd.setTid(tid);
		topicCrowd.setCid(Integer.parseInt(cid));
		topicCrowd.setStatus(1);
		topicCrowd.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return topicCrowd;
	}

	public List<TopicCrowd> findTopicCrowds(int tid) {
		String sql = "select * from t_topic_crowd where tid = ? ";
		List<TopicCrowd> topCrowds = new ArrayList<TopicCrowd>();
		Object[] params = new Object[] {tid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			TopicCrowd topicCrowd = new TopicCrowd();
			topicCrowd.setId(Integer.parseInt(list.get(i).get("id").toString()));
			topicCrowd.setTid(Integer.parseInt(list.get(i).get("tid").toString()));
			topicCrowd.setCid(Integer.parseInt(list.get(i).get("cid").toString()));
			topicCrowd.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			topicCrowd.setUpdateTime(list.get(i).get("updateTime").toString());
			topCrowds.add(topicCrowd);
		}
		return topCrowds;
	}

}
