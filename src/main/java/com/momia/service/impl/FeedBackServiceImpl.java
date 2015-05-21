package com.momia.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import com.momia.entity.FeedBack;
import com.momia.service.FeedBackService;

/**
 * 活动
 * @author duohongzhi
 *
 */
@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FeedBack findFeedBackById(int id) {
		String sql = "select * from t_feedback where id = ? ";
		final FeedBack entity = new FeedBack();
		final Object[] params = new Object[] {id};
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				feedBackForm(rs, entity);
			}
		});
		return entity;
	}

	public List<FeedBack> findFeedBacks() {
		List<FeedBack> ls = new ArrayList<FeedBack>();
		String sql = "select * from t_feedback ORDER BY addTime desc ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);	
		for (int i = 0; i < list.size(); i++) {
			FeedBack entity = new FeedBack();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setContent(list.get(i).get("content").toString());
			entity.setUid(Integer.parseInt(list.get(i).get("uid").toString()));
			entity.setEmail(list.get(i).get("email").toString());
			entity.setAddTime(list.get(i).get("addTime").toString());
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
	public FeedBack feedBackForm(ResultSet rs,FeedBack feedBack){
		try {
			feedBack.setId(rs.getInt("id"));
			feedBack.setContent(rs.getString("content"));
			feedBack.setUid(Integer.parseInt(rs.getString("uid")));
			feedBack.setEmail(rs.getString("email"));
			feedBack.setAddTime(rs.getString("addTime"));
			feedBack.setStatus(Integer.parseInt(rs.getString("status")));
			feedBack.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feedBack;
	}
	
}
