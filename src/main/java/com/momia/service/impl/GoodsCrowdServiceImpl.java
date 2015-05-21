package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.GoodsCrowd;
import com.momia.service.GoodsCrowdService;

/**
 * 适用人群关联
 * @author duohongzhi
 *
 */
@Service
public class GoodsCrowdServiceImpl implements  GoodsCrowdService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(GoodsCrowd goodsCrowd) {
		String sql = "insert into t_goods_crowd (gid, cid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {goodsCrowd.getGid(), goodsCrowd.getCid(),goodsCrowd.getStatus(),goodsCrowd.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int update(GoodsCrowd goodsCrowd) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id, int flag) {
		String sql = "";
		if(flag == 0){
			sql = "delete from t_goods_crowd where id = ?";
		}
		if(flag == 1){
			sql = "delete from t_goods_crowd where gid = ?";
		}
		Object[] params = new Object[] {id};					
		int reInt = jdbcTemplate.update(sql,params);			
		return reInt;
	}

	public void insert(String[] ids,int gid, int flag) {
		if(flag == 0){
			delete(gid,1);
		}
		if(ids == null){
		}else{
			if(ids.length > 0 ){
				for (int i = 0; i < ids.length; i++) {
					insert(formGoodsCrowd(ids[i],gid));
				}
			}
		}
	}
	
	public GoodsCrowd formGoodsCrowd(String cid,int gid){
		GoodsCrowd goodsCrowd = new GoodsCrowd();
		goodsCrowd.setGid(gid);
		goodsCrowd.setCid(Integer.parseInt(cid));
		goodsCrowd.setStatus(1);
		goodsCrowd.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return goodsCrowd;
	}

	public List<GoodsCrowd> findGoodsCrowds(int gid) {
		String sql = "select * from t_goods_crowd where gid = ? ";
		List<GoodsCrowd> goodsCrowds = new ArrayList<GoodsCrowd>();
		Object[] params = new Object[] {gid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			GoodsCrowd goodsCrowd = new GoodsCrowd();
			goodsCrowd.setId(Integer.parseInt(list.get(i).get("id").toString()));
			goodsCrowd.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
			goodsCrowd.setCid(Integer.parseInt(list.get(i).get("cid").toString()));
			goodsCrowd.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			goodsCrowd.setUpdateTime(list.get(i).get("updateTime").toString());
			goodsCrowds.add(goodsCrowd);
		}
		return goodsCrowds;
	}

}
