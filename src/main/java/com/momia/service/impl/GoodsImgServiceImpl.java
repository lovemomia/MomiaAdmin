package com.momia.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.momia.entity.GoodsImg;
import com.momia.service.GoodsImgService;

/**
 * 商品图片
 * @author duohongzhi
 *
 */
@Service
public class GoodsImgServiceImpl implements GoodsImgService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(GoodsImg goodsImg) {
		String sql = "insert into t_goods_img (picUrl, gid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {goodsImg.getPicUrl(), goodsImg.getGid(),goodsImg.getStatus(),goodsImg.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public void insert(String picStr,int gid, int flag) {
		String [] pics = picStr.split("###");
		if(flag == 0){
			delete(gid);
		}
		if(pics.length > 0){
			for (int i = 0; i < pics.length; i++) {
				insert(formGoodsImg(pics[i],gid));
			}
		}
	}
	
	public int delete(int gid) {
		String sql = "delete from t_goods_img where gid = ? ";
		Object[] params = new Object[] {gid};					
		int flag = jdbcTemplate.update(sql,params);	
		return flag;
	}
	
	public GoodsImg formGoodsImg(String picUrl,int gid){
		GoodsImg entity = new GoodsImg();
		entity.setPicUrl(picUrl);
		entity.setGid(gid);
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
}
