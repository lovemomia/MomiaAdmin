package com.momia.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.momia.entity.Article;
import com.momia.entity.TopicArticle;
import com.momia.service.ArticleService;
/**
 * 文章
 * @author duohongzhi
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Article> findArticles() {
		String sql = "select id,title,abstracts,picUrl,content,status,addTime,authorId,updateTime from t_article where status != 0 ";
		List<Article> ls = new ArrayList<Article>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Article entity = new Article();
			entity.setId(Integer.parseInt(list.get(i).get("id").toString()));
			entity.setTitle(list.get(i).get("title").toString());
			entity.setAbstracts(list.get(i).get("abstracts").toString());
			entity.setPicUrl(list.get(i).get("picUrl").toString());
			entity.setContent(list.get(i).get("content").toString());
			entity.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			entity.setAddTime(list.get(i).get("addTime").toString());
			entity.setAuthorId(Integer.parseInt(list.get(i).get("authorId").toString()));
			entity.setUpdateTime(list.get(i).get("updateTime").toString());
			ls.add(entity);
		}
		return ls;
	}
	
	public Article findArticleById(int id) {
		String sql = "select id,title,abstracts,picUrl,content,status,addTime,authorId,updateTime from t_article where id = ?";	
		final Article article = new Article();
		final Object[] params = new Object[] {id};	
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				articleForm(rs, article);
			}
		});
		return article;
	}
	
	public int update(Article article) {
		String sql = "update t_article set title = ?, abstracts = ?, picUrl = ?, content = ?, status = ?, addTime = ?, authorId = ?, updateTime = ? where id = ?";
		
		Object[] params = new Object[] {article.getTitle(), article.getAbstracts(),article.getPicUrl(),article.getContent(),article.getStatus(),article.getAddTime(),article.getAuthorId(),article.getUpdateTime(),article.getId()};
		int flag = jdbcTemplate.update(sql,params);		
		return flag;
	}

	public int insert(final Article article) {
		final String sql = "insert into t_article (title, abstracts, picUrl, content, status, addTime, authorId, updateTime) values (?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int flag = jdbcTemplate.update( new PreparedStatementCreator(){
            public java.sql.PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
               
            	int i = 0;
                java.sql.PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(++i, article.getTitle());
                ps.setString(++i, article.getAbstracts());
                ps.setString(++i, article.getPicUrl());
                ps.setString(++i, article.getContent());
                ps.setInt(++i, article.getStatus());
                ps.setString(++i, article.getAddTime());
                ps.setInt(++i, article.getAuthorId());
                ps.setString(++i, article.getUpdateTime());
                
                return ps;
            }
        },keyHolder);
		if (flag > 0) {
			flag = keyHolder.getKey().intValue();
		}
		return flag;
	}

	public int delete(int id) {
		String sql = "update t_article set status = ?, updateTime = ? where id = ?";
		Object[] params = new Object[] {0, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) , id};					
		int flag = jdbcTemplate.update(sql,params);	
		return flag;
	}
	
	public Article articleForm(ResultSet rs,Article article){
		try {
			//向对象中添加各个属性值
			article.setId(rs.getInt("id"));
			article.setTitle(rs.getString("title"));
			article.setAbstracts(rs.getString("abstracts"));
			article.setPicUrl(rs.getString("picUrl"));
			article.setContent(rs.getString("content"));
			article.setStatus(rs.getInt("status"));
			article.setAddTime(rs.getString("addTime"));
			article.setAuthorId(rs.getInt("authorId"));
			article.setUpdateTime(rs.getString("updateTime"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	public Article formArticle(HttpServletRequest req,int falg){
		Article entity = new Article();
		if(falg == 0){
			entity.setId(Integer.parseInt(req.getParameter("id")));
		}
		entity.setTitle(req.getParameter("title"));
		entity.setAbstracts(req.getParameter("abstracts"));
		entity.setPicUrl(req.getParameter("picUrl"));
		String content = req.getParameter("content");
		String contentStr = repStr(req,findSrc(content),content);
		entity.setContent(contentStr);
		//entity.setContent(req.getParameter("content"));
		entity.setStatus(1);
		entity.setAddTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		int authorid = Integer.parseInt(req.getParameter("authorId"));
		if(authorid == 0){
			entity.setAuthorId(Integer.parseInt(req.getParameter("uid")));
		}else{
			entity.setAuthorId(authorid);
		}
		
		entity.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		
		return entity;
	}

	public String repStr(HttpServletRequest req,String[] tr,String content){
		StringBuffer conpath = req.getRequestURL();
		if(conpath != null && !StringUtils.isEmpty(conpath.toString())){
			String urlStr = "";//conpath.substring(0,conpath.indexOf("/MomiaAdmin"));
			if(urlStr != null && !StringUtils.isEmpty(urlStr)){
				try {
					for (int i = 0; i < tr.length; i++) {
						String tri = tr[i];
						if(tri == null){
							continue;
						}else{
							if(tri != null && !StringUtils.isEmpty(tri)){
								
								Map<String, String> map = getPicWidthAndHeight(urlStr+tr[i]);
								String stra = tr[i]+"\"";
								String strb = tr[i]+"\" width=\""+map.get("width")+"px\" height=\""+map.get("height")+"px\"";
								String strc = tr[i]+"\" width=";
								if(content.indexOf(strc) > 0){
									continue;
								}else{
									content = content.replaceAll(stra, strb);
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	public Map<String, String> getPicWidthAndHeight(String imgUrl) {
		Map<String, String> reMap = new HashMap<String, String>();
		URL url = null;
		InputStream is = null;
		BufferedImage img = null;
		
		try {
			url = new URL(imgUrl);
			is = url.openStream();
			img = ImageIO.read(is);
			reMap.put("width", ""+img.getWidth());
			reMap.put("height", ""+img.getHeight());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return reMap;
	}
	
	public List<Article> findArticles(List<Article> articles,
			List<TopicArticle> tArticles) {
		List<Article> ls = new ArrayList<Article>();
		if(articles.size() > 0 && tArticles.size() > 0){
			for (int i = 0; i < articles.size(); i++) {
				int r = 0;
				for (int j = 0; j < tArticles.size(); j++) {
					int id =  articles.get(i).getId();
					int arid = tArticles.get(j).getArid();
					if( id == arid){
						r = 1;
						break;
					}
				}
				if(r == 0){
					ls.add(articles.get(i));
				}
			}
		}
		if(tArticles.size() == 0){
			ls.addAll(articles);
		}
		return ls;
	}

	public List<Article> findArticles(List<TopicArticle> tArticles) {
		List<Article> ls = new ArrayList<Article>();
		
		if(tArticles.size() > 0){
			for (int i = 0; i < tArticles.size(); i++) {
				ls.add(findArticleById(tArticles.get(i).getArid()));
			}
		}
		return ls;
	}
	
	public String[] findSrc(String contentStr){
		Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
		Matcher m = p.matcher(contentStr);
		String[] arr = new String[10];
		int i = 0;
		while(m.find()) {
		arr[i] = m.group(1);
		i++;
		}
		return arr;
	}

}
