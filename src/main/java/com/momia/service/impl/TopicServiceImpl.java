package com.momia.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.momia.entity.Topic;
import com.momia.service.TopicService;

/**
 * 文章/商品专题
 * @author duohongzhi
 *
 */
@Service
public class TopicServiceImpl implements TopicService{

	@Resource
	private JdbcTemplate jdbcTemplate;	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Topic> findTopics() {
		String sql = "select * from t_topic ";
		List list = jdbcTemplate.queryForList(sql);	
		return list;
	}

	public Topic findTopicById(int id) {
		String sql = "select * from t_topic where id = ?";	
		final Topic topic = new Topic();
		final Object[] params = new Object[] {id};	
		// 调用jdbcTemplate的query方法
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				//向对象中添加各个属性值
				topicForm(rs, topic);
			}
		});
		return topic;
	}

	public int update(Topic topic) {
		String sql = "update t_topic set title = ?, abstracts = ?, picUrl = ?, types = ?, status = ?, updateTime = ?, addTime = ?, beginTime = ?, endTime = ? where id = ?";
		
		Object[] params = new Object[] {topic.getTitle(), topic.getAbstracts(),topic.getPicUrl(), topic.getTypes(), topic.getStatus(), topic.getUpdateTime(), topic.getAddTime(), topic.getBeginTime(),topic.getEndTime(),topic.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}

	public int insert(final Topic topic) {
		
		final String sql = "insert into t_topic (title, abstracts, picUrl, types, status, updateTime, addTime, beginTime, endTime) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int flag = jdbcTemplate.update( new PreparedStatementCreator(){
            public java.sql.PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
               
            	int i = 0;
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(++i, topic.getTitle());
                ps.setString(++i, topic.getAbstracts());
                ps.setString(++i, topic.getPicUrl());
                ps.setInt(++i, topic.getTypes());
                ps.setInt(++i, topic.getStatus());
                ps.setString(++i, topic.getUpdateTime());
                ps.setString(++i, topic.getAddTime());
                ps.setString(++i, topic.getBeginTime());
                ps.setString(++i, topic.getEndTime());
                
                return ps;
            }
        },keyHolder);
		if (flag > 0) {
			flag = keyHolder.getKey().intValue();
		}
		return flag;
	}

	public int delete(int id) {
		
		String sql = "update t_topic set status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {0, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) , id};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}
	
	public Topic topicForm(ResultSet rs,Topic topic){
		try {
			topic.setId(rs.getInt("id"));
			topic.setTitle(rs.getString("title"));
			topic.setAbstracts(rs.getString("abstracts"));
			topic.setPicUrl(rs.getString("picUrl"));
			topic.setTypes(Integer.parseInt(rs.getString("types")));
			topic.setBeginTime(rs.getString("beginTime"));
			topic.setEndTime(rs.getString("endTime"));
			topic.setStatus(Integer.parseInt(rs.getString("status")));
			topic.setAddTime(rs.getString("addTime"));
			topic.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topic;
	}
	

	public Topic formTopic(HttpServletRequest req,int falg,int types){
		Topic topic = new Topic();
		if(falg == 0){
			topic.setId(Integer.parseInt(req.getParameter("id")));
		}
		topic.setTitle(req.getParameter("title"));
		topic.setAbstracts(req.getParameter("abstracts"));
		topic.setPicUrl(req.getParameter("picUrl"));
		topic.setTypes(types);
		topic.setStatus(Integer.parseInt(req.getParameter("submitType")));
		String beginTime = req.getParameter("beginTime");
		if(beginTime.indexOf("/") > 0){
			beginTime = StrToDate(beginTime)+" 00:00:01";
		}
		String endTime = req.getParameter("endTime");
		if(endTime.indexOf("/") > 0){
			endTime = StrToDate(endTime)+" 23:59:59";
		}
		topic.setBeginTime(beginTime);
		topic.setEndTime(endTime);
		topic.setAddTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		topic.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		
		return topic;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Topic> findTopics(int types) {
		String sql = "select * from t_topic where types = ? and status != 0 ";
		Object[] params = new Object[] {types};	
		List list = jdbcTemplate.queryForList(sql,params);	
		return list;
	}

	public String StrToDate(String str) { 
		String returnStr = "";
		if(str.indexOf("/") > 0){
		  String [] dateStr = str.split("/");
		  returnStr = dateStr[2]+"-"+dateStr[0]+"-"+dateStr[1];
		}
		return returnStr;
	}
	
}
