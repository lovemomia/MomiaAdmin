package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.GoodsAssort;
import com.momia.service.GoodsAssortService;

/**
 * 商品分类关联
 * @author duohongzhi
 *
 */
@Service
public class GoodsAssortServiceImpl implements GoodsAssortService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(GoodsAssort  goodsAssort) {
		String sql = "insert into t_goods_assort (gid, assid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {goodsAssort.getGid(), goodsAssort.getAssid(),goodsAssort.getStatus(),goodsAssort.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public void insert(String[] ids, int gid, int flag) {
		if(flag == 0){
			delete(gid,1);
		}
		if(ids.length > 0){
			for (int i = 0; i < ids.length; i++) {
				insert(formGoodsAssort(ids[i],gid));
			}
		}
		
	}

	public GoodsAssort formGoodsAssort(String assid,int gid){
		GoodsAssort entity = new GoodsAssort();
		entity.setGid(gid);
		entity.setAssid(Integer.parseInt(assid));
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
	public int update(GoodsAssort goodsAssort) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_goods_assort where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_goods_assort where gid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public List<GoodsAssort> findGoodsAssorts(int gid) {
		List<GoodsAssort> ls = new ArrayList<GoodsAssort>();
		String sql = "select * from t_goods_assort where gid = ? ";
		Object[] params = new Object[] {gid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			GoodsAssort entity = new GoodsAssort();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
			entity.setAssid(Integer.parseInt(list.get(i).get("assid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
}
