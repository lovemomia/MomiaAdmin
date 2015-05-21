package com.momia.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.momia.entity.User;

public interface UserService {

	//根据用户名和密码查找用户
	public User findUserByNameAndPassword(String username,String password);
	//根据用户名和密码验证用户是否存在
	public Boolean isUserByNameAndPassword(String username,String password);
	//根据用户id查询用户信息
	public User findUserById(int id);
	
	public List<User> findUsers();
	
	public int addUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUserById(int id);
	
	public User formUser(HttpServletRequest req,int falg);
	
	public List<User> findUsers(List<User> users ,int id);
	
}
