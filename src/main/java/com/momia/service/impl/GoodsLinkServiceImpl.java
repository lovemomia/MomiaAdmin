package com.momia.service.impl;

import java.math.BigDecimal;
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

import com.momia.entity.GoodsLink;
import com.momia.service.GoodsLinkService;

/**
 * 商品链接
 * @author duohongzhi
 *
 */
@Service
public class GoodsLinkServiceImpl implements GoodsLinkService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int insert(GoodsLink goodsLink) {
		String sql = "insert into t_goods_link (gid, goodUrl, origin, abstracts, price, status, updateTime) values (?, ?, ?, ?, ?, ?, ?)";
		//System.out.println(articleImg.getPicUrl());
		Object[] params = new Object[] {goodsLink.getGid(), goodsLink.getGoodUrl(), goodsLink.getOrigin(), goodsLink.getAbstracts(), goodsLink.getPrice(), goodsLink.getStatus(), goodsLink.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public int update(GoodsLink goodsLink) {
		String sql = "update t_goods_link set goodUrl = ?, origin = ?, abstracts = ?, price = ?, updateTime = ? where id = ? ";
		Object[] params = new Object[] {goodsLink.getGoodUrl(), goodsLink.getOrigin(), goodsLink.getAbstracts(), goodsLink.getPrice(), goodsLink.getUpdateTime(), goodsLink.getId()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}
	
	public int delete(int id) {
		String sql = "delete from t_goods_link where id = ? ";
		Object[] params = new Object[] {id};
		int flag = jdbcTemplate.update(sql,params);			
		return flag;
	}
	
	public GoodsLink formGoodsLink(HttpServletRequest req,int gid,int id){
		GoodsLink entity = new GoodsLink();
		entity.setGid(gid);
		entity.setId(id);
		entity.setGoodUrl(req.getParameter("goodUrl"));
		entity.setAbstracts(req.getParameter("abstracts"));
		entity.setOrigin(Integer.parseInt(req.getParameter("origin")));
		entity.setPrice(new BigDecimal(req.getParameter("price")));
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		return entity;
	}
	
	public List<GoodsLink> findGoodsLinkByGid(int gid) {
		String sql = "select * from t_goods_link where gid = ?";	
		List<GoodsLink> ls = new ArrayList<GoodsLink>();
		Object[] params = new Object[] {gid};	
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
		for (int i = 0; i < list.size(); i++) {
			GoodsLink entity = new GoodsLink();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
			entity.setGoodUrl(list.get(i).get("goodUrl").toString());
			entity.setOrigin(Integer.parseInt(list.get(i).get("origin").toString()));
			entity.setAbstracts(list.get(i).get("abstracts").toString());
			entity.setPrice(new BigDecimal(list.get(i).get("price").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
//	public List<GoodsLink> findGoodsLinkById(int id) {
//		String sql = "select * from t_goods_link where id = ?";	
//		List<GoodsLink> ls = new ArrayList<GoodsLink>();
//		Object[] params = new Object[] {id};	
//		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,params);	
//		for (int i = 0; i < list.size(); i++) {
//			GoodsLink entity = new GoodsLink();
//			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
//			entity.setGid(Integer.parseInt(list.get(i).get("gid").toString()));
//			entity.setGoodUrl(list.get(i).get("goodUrl").toString());
//			entity.setOrigin(Integer.parseInt(list.get(i).get("origin").toString()));
//			entity.setAbstracts(list.get(i).get("abstracts").toString());
//			entity.setPrice(new BigDecimal(list.get(i).get("price").toString()));
//			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
//			entity.setUpdateTime(list.get(i).get("updateTime").toString());
//			ls.add(entity);
//		}
//		return ls;
//	}
	
	public GoodsLink findGoodsLinkById(int id) {
		String sql = "select * from t_goods_link where id = ?";	
		final GoodsLink entity = new GoodsLink();
		final Object[] params = new Object[] {id};	
		// 调用jdbcTemplate的query方法
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				//向对象中添加各个属性值
				goodsLinkForm(rs, entity);
			}
		});
		return entity;
	}
	
	public GoodsLink goodsLinkForm(ResultSet rs,GoodsLink goodsLink){
		try {
			goodsLink.setId(rs.getInt("id"));
			goodsLink.setGid(Integer.parseInt(rs.getString("gid")));
			goodsLink.setGoodUrl(rs.getString("goodUrl"));
			goodsLink.setOrigin(Integer.parseInt(rs.getString("origin")));
			goodsLink.setAbstracts(rs.getString("abstracts"));
			goodsLink.setPrice(new BigDecimal(rs.getString("price")));
			goodsLink.setStatus(Integer.parseInt(rs.getString("status")));
			goodsLink.setUpdateTime(rs.getString("updateTime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goodsLink;
	}
	
}
