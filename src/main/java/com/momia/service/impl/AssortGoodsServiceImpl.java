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

import com.momia.entity.AssortGoods;
//import com.momia.entity.Crowd;
import com.momia.entity.GoodsAssort;
//import com.momia.entity.GoodsCrowd;
//import com.momia.entity.TopicGoodsAssort;
import com.momia.service.AssortGoodsService;

/**
 * 商品分类
 * @author duohongzhi
 *
 */

@Service
public class AssortGoodsServiceImpl implements AssortGoodsService{
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<AssortGoods> findAssortments() {
		String sql = "select id,name,parentid,status,updateTime from t_goods_assortment ";
		List<AssortGoods> ls = new ArrayList<AssortGoods>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);	
		for (int i = 0; i < list.size(); i++) {
			AssortGoods entity = new AssortGoods();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setName(list.get(i).get("name").toString());
			entity.setParentid(Integer.parseInt(list.get(i).get("parentid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			entity.setListGoods(new ArrayList<AssortGoods>());
			entity.setFlag(0);
			
			ls.add(entity);
		}
		return ls;
	}

	public AssortGoods findAssortmentById(int id) {
		String sql = "select * from t_goods_assortment where id = ?";	
		final AssortGoods assort = new AssortGoods();
		final Object[] params = new Object[] {id};	
		// 调用jdbcTemplate的query方法
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				assortGoodsForm(rs, assort);
			}
		});
		return assort;
	}

	public int update(AssortGoods assort) {
		String sql = "update t_goods_assortment set name = ?, parentid = ?, status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {assort.getName(), assort.getParentid(), assort.getStatus(), assort.getUpdateTime(), assort.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}

	public int insert(AssortGoods assort) {
		String sql = "insert into t_goods_assortment (name, parentid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {assort.getName(), assort.getParentid(), assort.getStatus(), assort.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int delete(int id) {
		String sql = "delete from t_goods_assortment where id = ?";
		Object[] params = new Object[] {id};					
		int flag = jdbcTemplate.update(sql,params);			
		return flag;
	}
	
	public AssortGoods assortGoodsForm(ResultSet rs,AssortGoods assort){
		try {
			assort.setId(rs.getInt("id"));
			assort.setName(rs.getString("name"));
			assort.setParentid(Integer.parseInt(rs.getString("parentid")));
			assort.setStatus(Integer.parseInt(rs.getString("status")));
			assort.setUpdateTime(rs.getString("updateTime"));
			assort.setListGoods(new ArrayList<AssortGoods>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assort;
	}
	

	public AssortGoods formAssortGoods(HttpServletRequest req,int falg){
		AssortGoods assort = new AssortGoods();
		if(falg == 0){
			assort.setId(Integer.parseInt(req.getParameter("id")));
		}
		assort.setName(req.getParameter("name"));
		assort.setParentid(Integer.parseInt(req.getParameter("parentid")));
		assort.setStatus(1);
		assort.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		assort.setListGoods(new ArrayList<AssortGoods>());
		
		return assort;
	}
	
	/**
	 * goods中获取标识选中的分类数据
	 */
	public List<AssortGoods> findAssortGoods(List<AssortGoods> assortGoods,List<GoodsAssort> gAssort) {
		List<AssortGoods> list = new ArrayList<AssortGoods>();
		if(assortGoods.size() > 0 && gAssort.size() > 0){
			for (int i = 0; i < assortGoods.size(); i++) {
				int r = 0;
				for (int j = 0; j < gAssort.size(); j++) {
					int id =  assortGoods.get(i).getId();
					int cid = gAssort.get(j).getAssid();
					
					
					if( id == cid){
						AssortGoods entity = assortGoods.get(i);
						entity.setFlag(1);
						list.add(entity);
						r = 1;
						break;
					}
				}
				if(r == 0){
					list.add(assortGoods.get(i));
				}
			}
		}else{
			list.addAll(assortGoods);
		}
		return list;
	}
	
	/**
	 * 商品专题分类
	 */
//	public List<AssortGoods> findAssortgoods(List<AssortGoods> entitys1,List<TopicGoodsAssort> entitys2) {
//		List<AssortGoods> ls = new ArrayList<AssortGoods>();
//		if(entitys1.size() > 0 ){
//			ls.addAll(findLevel(entitys1));
//		}
//		if(ls.size() > 0){
//			for (int i = 0; i < ls.size(); i++) {
//				List<AssortGoods> sss = new ArrayList<AssortGoods>();
//				sss = findForTopic(ls.get(i).getListGoods(),entitys2);
//				ls.get(i).setListGoods(sss);
//			}
//		}
//		
//		return ls;
//	}
	
	/**
	 * 查找专题二级被选中项
	 * @param entitys1
	 * @param entitys2
	 * @return
	 */
//	public List<AssortGoods> findForTopic(List<AssortGoods> entitys1,List<TopicGoodsAssort> entitys2){
//		List<AssortGoods> ls = new ArrayList<AssortGoods>();
//		if(entitys1.size()>0){
//			for (int i = 0; i < entitys1.size(); i++) {
//				int aid = entitys1.get(i).getId();
//				AssortGoods entity = new AssortGoods();
//				entity = entitys1.get(i);
//				for (int j = 0; j < entitys2.size(); j++) {
//					int bid = entitys2.get(j).getAssid();
//					if(aid == bid){
//						entity.setFlag(1);
//					}else{
//						continue;
//					}
//				}
//				ls.add(entity);
//			}
//		}
//		return ls;
//	}
	
	/**
	 * 组装一级和二级
	 * @param list
	 * @return
	 */
//	public List<AssortGoods> findLevel(List<AssortGoods> list){
//		List<AssortGoods> pls = new ArrayList<AssortGoods>();
//		List<AssortGoods> ls = new ArrayList<AssortGoods>();
//		
//		if(list.size() > 0){
//			for (int i = 0; i < list.size(); i++) {
//				if(list.get(i).getParentid() == 0){
//					pls.add(list.get(i));
//				}else{
//					ls.add(list.get(i));
//				}
//			}
//		}
//		
//		if(pls.size() > 0 && ls.size() > 0){
//			for (int i = 0; i < pls.size(); i++) {
//				int aid = pls.get(i).getId();
//				List<AssortGoods> mapls = new ArrayList<AssortGoods>();
//				for (int j = 0; j < ls.size(); j++) {
//					int bid = ls.get(j).getParentid();
//					if(aid == bid){
//						mapls.add(ls.get(j));
//					}else{
//						continue;
//					}
//				}
//				pls.get(i).setListGoods(mapls);
//			}
//		}
//		
//		return pls;
//		
//	}
	
}
