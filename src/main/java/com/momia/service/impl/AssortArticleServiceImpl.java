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

import com.momia.entity.ArticleAssort;
import com.momia.entity.AssortArticle;
import com.momia.entity.TopicArticleAssort;
import com.momia.service.AssortArticleService;
/**
 * 文章分类表
 * @author duohongzhi
 *
 */
@Service
public class AssortArticleServiceImpl implements AssortArticleService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<AssortArticle> findAssortments() {
		String sql = "select id,name,parentid,status,updateTime from t_article_assortment ";
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);	
		for (int i = 0; i < list.size(); i++) {
			AssortArticle entity = new AssortArticle();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setName(list.get(i).get("name").toString());
			entity.setParentid(Integer.parseInt(list.get(i).get("parentid").toString()));
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			entity.setFlag(0);
			entity.setLsArticles(new ArrayList<AssortArticle>());
			ls.add(entity);
		}
		return ls;
	}

	public AssortArticle findAssortmentById(int id) {
		String sql = "select * from t_article_assortment where id = ?";	
		final AssortArticle assort = new AssortArticle();
		final Object[] params = new Object[] {id};	
		// 调用jdbcTemplate的query方法
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				assortArticleForm(rs, assort);
			}
		});
		return assort;
	}

	public int update(AssortArticle assort) {
		String sql = "update t_article_assortment set name = ?, parentid = ?, status = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {assort.getName(), assort.getParentid(), assort.getStatus(), assort.getUpdateTime(), assort.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}

	public int insert(AssortArticle assort) {
		String sql = "insert into t_article_assortment (name, parentid, status, updateTime) values (?, ?, ?, ?)";
		Object[] params = new Object[] {assort.getName(), assort.getParentid(), assort.getStatus(), assort.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	public int delete(int id) {
		String sql = "delete from t_article_assortment where id = ?";
		Object[] params = new Object[] {id};					
		int flag = jdbcTemplate.update(sql,params);	
		return flag;
	}
	
	public AssortArticle assortArticleForm(ResultSet rs,AssortArticle assort){
		try {
			assort.setId(rs.getInt("id"));
			assort.setName(rs.getString("name"));
			assort.setParentid(Integer.parseInt(rs.getString("parentid")));
			assort.setStatus(Integer.parseInt(rs.getString("status")));
			assort.setUpdateTime(rs.getString("updateTime"));
			assort.setLsArticles(new ArrayList<AssortArticle>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assort;
	}	

	public AssortArticle formAssortArticle(HttpServletRequest req,int falg){
		AssortArticle assort = new AssortArticle();
		if(falg == 0){
			assort.setId(Integer.parseInt(req.getParameter("id")));
		}
		assort.setName(req.getParameter("name"));
		assort.setParentid(Integer.parseInt(req.getParameter("parentid")));
		assort.setStatus(1);
		assort.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		assort.setLsArticles(new ArrayList<AssortArticle>());
		return assort;
	}
	
	/**
	 * 文章专题的分类List信息
	 */
	public List<AssortArticle> findAssortArticles(List<AssortArticle> entitys1,List<TopicArticleAssort> entitys2) {
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		if(entitys1.size() > 0 ){
			ls.addAll(findLevel(entitys1));
		}
		if(ls.size() > 0){
			for (int i = 0; i < ls.size(); i++) {
				List<AssortArticle> sss = new ArrayList<AssortArticle>();
				sss = findForTopic(ls.get(i).getLsArticles(),entitys2);
				ls.get(i).setLsArticles(sss);
			}
		}
		
		return ls;
	}
	
	/**
	 * 查找专题二级被选中项
	 * @param entitys1
	 * @param entitys2
	 * @return
	 */
	public List<AssortArticle> findForTopic(List<AssortArticle> entitys1,List<TopicArticleAssort> entitys2){
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		if(entitys1.size()>0){
			for (int i = 0; i < entitys1.size(); i++) {
				int aid = entitys1.get(i).getId();
				AssortArticle entity = new AssortArticle();
				entity = entitys1.get(i);
				for (int j = 0; j < entitys2.size(); j++) {
					int bid = entitys2.get(j).getAssid();
					if(aid == bid){
						entity.setFlag(1);
					}else{
						continue;
					}
				}
				ls.add(entity);
			}
		}
		return ls;
	}
	
	/**
	 * 文章的分类信息
	 */
	public List<AssortArticle> findAssArticles(List<AssortArticle> entitys1,List<ArticleAssort> entitys2) {
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		if(entitys1.size() > 0 ){
			ls.addAll(findLevel(entitys1));
		}
		if(ls.size() > 0){
			for (int i = 0; i < ls.size(); i++) {
				List<AssortArticle> sss = new ArrayList<AssortArticle>();
				sss = findFor(ls.get(i).getLsArticles(),entitys2);
				ls.get(i).setLsArticles(sss);
			}
		}
		
		return ls;
	}
	
	/**
	 * 查找文章二级被选中项
	 * @param entitys1
	 * @param entitys2
	 * @return
	 */
	public List<AssortArticle> findFor(List<AssortArticle> entitys1,List<ArticleAssort> entitys2){
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		if(entitys1.size()>0){
			for (int i = 0; i < entitys1.size(); i++) {
				int aid = entitys1.get(i).getId();
				AssortArticle entity = new AssortArticle();
				entity = entitys1.get(i);
				for (int j = 0; j < entitys2.size(); j++) {
					int bid = entitys2.get(j).getAssid();
					if(aid == bid){
						entity.setFlag(1);
					}else{
						continue;
					}
				}
				ls.add(entity);
			}
		}
		return ls;
	}
	
	/**
	 * 组装一级和二级
	 * @param list
	 * @return
	 */
	public List<AssortArticle> findLevel(List<AssortArticle> list){
		List<AssortArticle> pls = new ArrayList<AssortArticle>();
		List<AssortArticle> ls = new ArrayList<AssortArticle>();
		if(list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getParentid() == 0){
					pls.add(list.get(i));
				}else{
					ls.add(list.get(i));
				}
			}
		}
		
		if(pls.size() > 0 && ls.size() > 0){
			for (int i = 0; i < pls.size(); i++) {
				int aid = pls.get(i).getId();
				List<AssortArticle> mapls = new ArrayList<AssortArticle>();
				for (int j = 0; j < ls.size(); j++) {
					int bid = ls.get(j).getParentid();
					if(aid == bid){
						mapls.add(ls.get(j));
					}else{
						continue;
					}
				}
				pls.get(i).setLsArticles(mapls);
			}
		}
		
		return pls;
		
	}
}
