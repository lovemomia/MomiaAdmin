package com.momia.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.momia.entity.Goods;
import com.momia.entity.TopicGoods;
import com.momia.service.GoodsService;

/**
 * 商品推荐
 * @author duohongzhi
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService{

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Goods> findGoods() {
		String sql = "select id,title,abstracts,addTime,uid,status,updateTime from t_goods where status != 0 ";
		List<Goods> ls = new ArrayList<Goods>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Goods entity = new Goods();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTitle(list.get(i).get("title").toString());
			entity.setAbstracts(list.get(i).get("abstracts").toString());
			entity.setAddTime(list.get(i).get("addTime").toString());
			entity.setUid(Integer.parseInt(list.get(i).get("uid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}

	public Goods findGoodsById(int id) {
		String sql = "select id,title,abstracts,addTime,uid,status,updateTime from t_goods where id = ?";	
		final Goods entity = new Goods();
		final Object[] params = new Object[] {id};	
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				goodsForm(rs, entity);
			}
		});
		return entity;
	}
	
	public Goods goodsForm(ResultSet rs,Goods goods){
		try {
			//向对象中添加各个属性值
			goods.setId(rs.getInt("id"));
			goods.setTitle(rs.getString("title"));
			goods.setAbstracts(rs.getString("abstracts"));
			goods.setAddTime(rs.getString("addTime"));
			goods.setUid(rs.getInt("uid"));
			goods.setStatus(rs.getInt("status"));
			goods.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}

	public int update(Goods goods) {
		String sql = "update t_goods set title = ?, abstracts = ?, addTime = ?, uid = ?, status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {goods.getTitle(), goods.getAbstracts(),goods.getAddTime(), goods.getUid(), goods.getStatus(),goods.getUpdateTime(),goods.getId()};
		int flag = jdbcTemplate.update(sql,params);	
		return flag;
	}

	public int insert(final Goods goods) {
		final String sql = "insert into t_goods (title, abstracts, addTime, uid, status, updateTime) values (?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int flag = jdbcTemplate.update( new PreparedStatementCreator(){
            public java.sql.PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
               
            	int i = 0;
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(++i, goods.getTitle());
                ps.setString(++i, goods.getAbstracts());
                ps.setString(++i, goods.getAddTime());
                ps.setInt(++i, goods.getUid());
                ps.setInt(++i, goods.getStatus());
                ps.setString(++i, goods.getUpdateTime());
                
                return ps;
            }
        },keyHolder);
		if (flag > 0) {
			flag = keyHolder.getKey().intValue();
		}
		return flag;
	}

	public int delete(int id) {
		String sql = "update t_goods set status = ?, updateTime = ? where id = ?";
		Object[] params = new Object[] {0, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) , id};					
		int flag = jdbcTemplate.update(sql,params);			
		return flag;
	}

	public Goods formGoods(HttpServletRequest req, int falg) {
		Goods entity = new Goods();
		if(falg == 0){
			entity.setId(Integer.parseInt(req.getParameter("id")));
		}
		entity.setTitle(req.getParameter("title"));
		entity.setAbstracts(req.getParameter("abstracts"));
		entity.setAddTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		entity.setUid(Integer.parseInt(req.getParameter("uid")));
		entity.setStatus(1);
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		
		return entity;
	}
	
	public List<Goods> findgoods(List<Goods> lGoods,
			List<TopicGoods> tGoods) {
		List<Goods> ls = new ArrayList<Goods>();
		if(lGoods.size() > 0 && tGoods.size() > 0){
			for (int i = 0; i < lGoods.size(); i++) {
				int r = 0;
				for (int j = 0; j < tGoods.size(); j++) {
					int id =  lGoods.get(i).getId();
					int gid = tGoods.get(j).getGid();
					if( id == gid){
						r = 1;
						break;
					}
				}
				if(r == 0){
					ls.add(lGoods.get(i));
				}
			}
		}
		if(tGoods.size() == 0){
			ls.addAll(lGoods);
		}
		return ls;
	}
	
	public List<Goods> findgoods(List<TopicGoods> tGoods) {
		List<Goods> ls = new ArrayList<Goods>();
		
		if(tGoods.size() > 0){
			for (int i = 0; i < tGoods.size(); i++) {
				ls.add(findGoodsById(tGoods.get(i).getGid()));
			}
		}
		return ls;
	}
	
}
