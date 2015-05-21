package com.momia.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.momia.entity.Event;
import com.momia.service.EventService;

/**
 * 活动
 * @author duohongzhi
 *
 */
@Service
public class EventServiceImpl implements EventService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Event findEventId(int id) {
		String sql = "select * from t_event where id = ?";
		final Event entity = new Event();
		final Object[] params = new Object[] {id};
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				eventForm(rs, entity);
			}
		});
		return entity;
	}
	
	public List<Event> findEvents() {
		List<Event> ls = new ArrayList<Event>();
		String sql = "select * from t_event";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);	
		for (int i = 0; i < list.size(); i++) {
			Event entity = new Event();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTitle(list.get(i).get("title").toString());
			entity.setSubtitle(list.get(i).get("subtitle").toString());
			entity.setPicUrl(list.get(i).get("picUrl").toString());
			entity.setEventUrl(list.get(i).get("eventUrl").toString());
			entity.setBeginTime(list.get(i).get("beginTime").toString());
			entity.setEndTime(list.get(i).get("endTime").toString());
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
	public int delete(int id) {
		String sql = "delete from t_event where id = ?";
		Object[] params = new Object[] {id};
		int flag = jdbcTemplate.update(sql,params);	
		return flag;
	}
	
	public int insert(Event event) {
		String sql = "insert into t_event (title,subtitle,picUrl,eventUrl,beginTime,endTime,status,updateTime) values (?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {event.getTitle(), event.getSubtitle(), event.getPicUrl(), event.getEventUrl(),event.getBeginTime(), event.getEndTime(), event.getStatus(), event.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public int update(Event event) {
		String sql = "update t_event set title = ?, subtitle = ?, picUrl = ?, eventUrl = ?, beginTime = ?, endTime = ?, updateTime = ? where id = ?";
		Object[] params = new Object[] {event.getTitle(), event.getSubtitle(), event.getPicUrl(), event.getEventUrl(),event.getBeginTime(), event.getEndTime(), event.getUpdateTime(),event.getId()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public Event eventForm(ResultSet rs,Event event){
		try {
			event.setId(rs.getInt("id"));
			event.setTitle(rs.getString("title"));
			event.setSubtitle(rs.getString("subtitle"));
			event.setPicUrl(rs.getString("picUrl"));
			event.setEventUrl(rs.getString("eventUrl"));
			event.setBeginTime(rs.getString("beginTime"));
			event.setEndTime(rs.getString("endTime"));
			event.setStatus(Integer.parseInt(rs.getString("status")));
			event.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public Event formEvent(HttpServletRequest req,int falg){
		Event event = new Event();
		if(falg == 0){
			event.setId(Integer.parseInt(req.getParameter("id")));
		}
		event.setTitle(req.getParameter("title"));
		event.setSubtitle(req.getParameter("subtitle"));
		event.setPicUrl(req.getParameter("picUrl"));
		event.setEventUrl(req.getParameter("eventUrl"));
		String beginTime = req.getParameter("beginTime");
		if(beginTime.indexOf("/") > 0){
			beginTime = StrToDate(beginTime)+" 00:00:01";
		}
		String endTime = req.getParameter("endTime");
		if(endTime.indexOf("/") > 0){
			endTime = StrToDate(endTime)+" 23:59:59";
		}
		event.setBeginTime(beginTime);
		event.setEndTime(endTime);
		event.setStatus(1);
		event.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		
		return event;
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
