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

import com.momia.service.AssortArticleService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/assortarticle")
public class AssortArticleAction {

	@Resource
	private UserService userService;
	
	@Resource
	private AssortArticleService assortArticleService;
	
	/**
	 * 跳转分类管理并获取列表
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("assorts", assortArticleService.findAssortments()); 
		return new ModelAndView("assortarticle",context);
	}
	
	/**
	 * 分类添加修改操作跳转
	 * @param id
	 * @param flag
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/operation",method = RequestMethod.GET)
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			returnString = "assortarticleadd";
		}else{
			returnString = "assortarticleupdate";
			context.put("model", assortArticleService.findAssortmentById(id)); 
		}
		//context.put("assort", assortArticleService.findAssortments()); 
		context.put("user", userService.findUserById(uid));  
		
		return new ModelAndView(returnString,context);
	}
	
	/**
	 * 添加
	 * @param id
	 * @param req
	 * @return
	 */
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("uid") int uid,HttpServletRequest req){
		String returnString = "";
		int intadd = assortArticleService.insert(assortArticleService.formAssortArticle(req, 1));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "assortarticle";
			context.put("user", userService.findUserById(uid));  
			context.put("assorts", assortArticleService.findAssortments()); 
		}else{
			returnString = "assortarticleadd";
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
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		int intadd = assortArticleService.update(assortArticleService.formAssortArticle(req, 0));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "assortarticle";
			context.put("user", userService.findUserById(uid));  
			context.put("assorts", assortArticleService.findAssortments()); 
		}else{
			returnString = "assortarticleupdate";
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
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = assortArticleService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("assorts", assortArticleService.findAssortments()); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("assortarticle",context);
	}
	
}
