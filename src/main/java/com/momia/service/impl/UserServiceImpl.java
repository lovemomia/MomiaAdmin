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

import com.momia.entity.User;
import com.momia.service.UserService;

/**
 * 用户
 * @author duohongzhi
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据用户id查询用户单个对象
	 */
	public User findUserById(int id) {
		String sql = "select * from t_admin where id = ?";
		final User user = new User();
		final Object[] params = new Object[] {id};
		//调用jdbcTemplate的query方法
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				//向user对象中添加各个属性值
				userForm(rs, user);
			}
		});
		return user;
	}
	
	/**
	 * 查询所有用户集合
	 */
	public List<User> findUsers() {
		String sql = "select * from t_admin";
		List<User> ls = new ArrayList<User>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			User user = new User();
			user.setId(Integer.parseInt(list.get(i).get("id").toString()));
			user.setUsername(list.get(i).get("username").toString());
			user.setPassword(list.get(i).get("password").toString());
			user.setWechatNo(list.get(i).get("wechatNo").toString());
			user.setAbstracts(list.get(i).get("abstracts").toString());
			user.setAddTime(list.get(i).get("addTime").toString());
			user.setStatus(Integer.parseInt(list.get(i).get("status").toString()));
			user.setUpdateTime(list.get(i).get("updateTime").toString());
			user.setFlag(0);
			
			ls.add(user);
		}
		return ls;
	}

	/**
	 * 根据用户Id删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteUserById(int id) {
		String sql = "delete from t_admin where id = ?";
		Object[] params = new Object[] {id};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user) {
		String sql = "insert into t_admin (username, password, abstracts, wechatNo, status, addTime, updateTime) values (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {user.getUsername(), user.getPassword(), user.getAbstracts(), user.getWechatNo(), user.getStatus(), user.getAddTime(), user.getUpdateTime()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	/**
	 * 使用Id更新用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		String sql = "update t_admin set username = ?, password = ?, abstracts = ?, wechatNo = ?, status = ?, addTime = ?, updateTime = ? where id = ?";
		//存放SQL语句中的参数
		Object[] params = new Object[] {user.getUsername(), user.getPassword(), user.getAbstracts(), user.getWechatNo(), user.getStatus(), user.getAddTime(), user.getUpdateTime(),user.getId()};
		int flag = jdbcTemplate.update(sql,params);
		return flag;
	}

	/**
	 * 根据用户名和密码查询用户单个对象
	 */
	public User findUserByNameAndPassword(String username, String password) {
		String sql = "select id, username, password, abstracts, wechatNo, status, addTime, updateTime from t_admin where username = ? and password = ?";
		
		final User user = new User();
		final Object[] params = new Object[] {username,password};
		jdbcTemplate.query(sql,params, new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				//向user对象中添加各个属性值
				userForm(rs, user);
			}
		});
		return user;
	}
	
	/**
	 * 根据用户名和密码验证用户信息
	 */
	@SuppressWarnings("deprecation")
	public Boolean isUserByNameAndPassword(String username, String password) {
		boolean istrue = false;
		String sql = "select count(1) as c from t_admin where username = ? and password = ?";
		Object[] params = new Object[] {username,password};
		if(jdbcTemplate.queryForInt(sql, params) > 0){
			istrue = true;
		}
		return istrue;
	}
	
	public User userForm(ResultSet rs,User user){
		try {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setAbstracts(rs.getString("abstracts"));
			user.setWechatNo(rs.getString("wechatNo"));
			user.setStatus(Integer.parseInt(rs.getString("status")));
			user.setAddTime(rs.getString("addTime"));
			user.setUpdateTime(rs.getString("updateTime"));
			user.setFlag(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

	public User formUser(HttpServletRequest req,int falg){
		User user = new User();
		if(falg == 0){
			user.setId(Integer.parseInt(req.getParameter("uid")));
		}
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setAbstracts(req.getParameter("abstracts"));
		user.setWechatNo(req.getParameter("wechatNo"));
		user.setStatus(1);
		user.setAddTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		user.setUpdateTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		
		return user;
	}
	
	public List<User> findUsers(List<User> users ,int id){
		List<User> ls = new ArrayList<User>();
		if(users.size() > 0){
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				if(user.getId() == id){
					user.setFlag(1);	
				}
				ls.add(user);
			}
		}
		
		return ls;
	}
	
	
}
