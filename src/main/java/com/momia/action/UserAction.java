package com.momia.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Resource
	private UserService userService;
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @param req
	 * @return
	 */
	@RequestMapping("/loginpage")
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest req){
		
		Map<String, Object> context = new HashMap<String, Object>();  
		String returnstr = "";
		boolean is = userService.isUserByNameAndPassword(username, password);
		if(is == true){
			context.put("user", userService.findUserByNameAndPassword(username, password));
			returnstr = "index";
		}else {
			context.put("message", LoginUntil.loginOut);
			returnstr = "login";
		}
		return new ModelAndView(returnstr, context);
	}
	
	/**
	 * 跳转登录页面
	 * @param req
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView loginpage(HttpServletRequest req){
		Map<String, String> context = new HashMap<String, String>();
		context.put("message", LoginUntil.loginPage);  
		return new ModelAndView("login",context);
	}
	
	/**
	 * 跳转首页
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/spage",method = RequestMethod.GET)
	public ModelAndView spage(@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(id));  
		req.getSession().setMaxInactiveInterval(60);
		req.getSession().setAttribute("user", userService.findUserById(id));
		return new ModelAndView("index",context);
	}
	
	/**
	 *跳转用户管理并获取用户列表
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ModelAndView mangerinfo(@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(id));  
		context.put("entitys", userService.findUsers()); 
		req.getSession().setMaxInactiveInterval(60);
		req.getSession().setAttribute("user", userService.findUserById(id));
		return new ModelAndView("user",context);
	}
	
	/**
	 * 用户添加修改操作跳转
	 * @param id
	 * @param flag
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/operation",method = RequestMethod.GET)
	public ModelAndView operation(@RequestParam("id") int oid,@RequestParam("flag") String flag,@RequestParam("uid") int id,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			returnString = "useradd";
			context.put("usertype", 0);
		}else{
			returnString = "userupdate";
			context.put("model", userService.findUserById(id)); 
		}
		context.put("user", userService.findUserById(oid));  

		req.getSession().setMaxInactiveInterval(20);
		req.getSession().setAttribute("user", userService.findUserById(id));
		return new ModelAndView(returnString,context);
	}
	
	/**
	 * 添加用户
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping("/adduser")
	public ModelAndView add(@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		
		int intadd = userService.addUser(userService.formUser(req, 1));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "user";
			context.put("user", userService.findUserById(id));  
			context.put("entitys", userService.findUsers()); 
		}else{
			returnString = "useradd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	/**
	 * 修改
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateuser")
	public ModelAndView update(@RequestParam("id") int oid,@RequestParam("uid") int id,HttpServletRequest req){
		String returnString = "";
		
		int intadd = userService.updateUser(userService.formUser(req, 0));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "user";
			context.put("user", userService.findUserById(oid));  
			context.put("entitys", userService.findUsers()); 
		}else{
			returnString = "userupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping("/deluser")
	public ModelAndView delete(@RequestParam("id") int oid,@RequestParam("uid") int id,HttpServletRequest req){
		int intdel = userService.deleteUserById(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(oid));  
		context.put("entitys", userService.findUsers()); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("user",context);
	}
}
