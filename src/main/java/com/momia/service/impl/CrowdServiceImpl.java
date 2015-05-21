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

import com.momia.entity.ArticleCrowd;
import com.momia.entity.Crowd;
import com.momia.entity.GoodsCrowd;
import com.momia.entity.TopicCrowd;
import com.momia.service.CrowdService;

/**
 * 年龄标签
 * @author duohongzhi
 *
 */
@Service
public class CrowdServiceImpl implements CrowdService{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

	public Crowd findCrowdById(int id) {
		String sql = "select * from t_crowd where id = ?";
		final Crowd crowd = new Crowd();
		final Object[] params = new Object[] {id};
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				crowdForm(rs, crowd);
			}
		});
		return crowd;
	}

	public List<Crowd> findCrowds() {
		List<Crowd> crowdlist = new ArrayList<Crowd>();
		String sql = "select id,name,status,updateTime from t_crowd";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Crowd crowd = new Crowd();
			crowd.setId(Integer.parseInt(list.get(i).get("id").toString()));
			crowd.setName(list.get(i).get("name").toString());
			crowd.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			crowd.setUpdateTime(list.get(i).get("updateTime").toString());
			crowd.setFlag(0);
			
			crowdlist.add(crowd);
		}
		return crowdlist;
	}
	
	public Crowd crowdForm(ResultSet rs,Crowd crowd){
		try {
			crowd.setId(rs.getInt("id"));
			crowd.setName(rs.getString("name"));
			crowd.setStatus(Integer.parseInt(rs.getString("status")));
			crowd.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return crowd;
	}
	
	/**
	 * topic中获取标识选中的适用人群数据
	 */
	public List<Crowd> findCrowds(List<Crowd> crowds,List<TopicCrowd> tcrowds) {
		List<Crowd> crowdlist = new ArrayList<Crowd>();
		if(crowds.size() > 0 && tcrowds.size() > 0){
			for (int i = 0; i < crowds.size(); i++) {
				int r = 0;
				for (int j = 0; j < tcrowds.size(); j++) {
					int id =  crowds.get(i).getId();
					int cid = tcrowds.get(j).getCid();
					
					
					if( id == cid){
						Crowd crowd = crowds.get(i);
						crowd.setFlag(1);
						crowdlist.add(crowd);
						r = 1;
						break;
					}
				}
				if(r == 0){
					crowdlist.add(crowds.get(i));
				}
			}
		}else{
			crowdlist.addAll(crowds);
		}
		return crowdlist;
	}
	
	/**
	 * article中获取标识选中的适用人群数据
	 */
	public List<Crowd> findArCrowds(List<Crowd> crowds,List<ArticleCrowd> arCrowds) {
		List<Crowd> crowdlist = new ArrayList<Crowd>();
		if(crowds.size() > 0 && arCrowds.size() > 0){
			for (int i = 0; i < crowds.size(); i++) {
				int r = 0;
				for (int j = 0; j < arCrowds.size(); j++) {
					int id =  crowds.get(i).getId();
					int cid = arCrowds.get(j).getCid();
					
					
					if( id == cid){
						Crowd crowd = crowds.get(i);
						crowd.setFlag(1);
						crowdlist.add(crowd);
						r = 1;
						break;
					}
				}
				if(r == 0){
					crowdlist.add(crowds.get(i));
				}
			}
		}else{
			crowdlist.addAll(crowds);
		}
		return crowdlist;
	}
	
	/**
	 * goods中获取标识选中的适用人群数据
	 */
	public List<Crowd> findGoodsCrowds(List<Crowd> crowds,List<GoodsCrowd> gCrowds) {
		List<Crowd> crowdlist = new ArrayList<Crowd>();
		if(crowds.size() > 0 && gCrowds.size() > 0){
			for (int i = 0; i < crowds.size(); i++) {
				int r = 0;
				for (int j = 0; j < gCrowds.size(); j++) {
					int id =  crowds.get(i).getId();
					int cid = gCrowds.get(j).getCid();
					
					
					if( id == cid){
						Crowd crowd = crowds.get(i);
						crowd.setFlag(1);
						crowdlist.add(crowd);
						r = 1;
						break;
					}
				}
				if(r == 0){
					crowdlist.add(crowds.get(i));
				}
			}
		}else{
			crowdlist.addAll(crowds);
		}
		return crowdlist;
	}

}
