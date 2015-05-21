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

//import com.momia.service.ArticleAssortService;
import com.momia.service.ArticleCrowdService;
import com.momia.service.ArticleImgService;
import com.momia.service.ArticleService;
import com.momia.service.AssortArticleService;
import com.momia.service.CrowdService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/article")
public class ArticleAction {

	@Resource
	private UserService userService;
	
	@Resource
	private AssortArticleService assortService;
	
	@Resource
	private ArticleService articleService;
	
	//@Resource
	//private ArticleAssortService articleAssortService;
	
	@Resource
	private CrowdService crowdService;
	
	@Resource
	private ArticleCrowdService articleCrowdService;
	
	@Resource
	private ArticleImgService articleImgService;
	
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("articles", articleService.findArticles()); 
		return new ModelAndView("article",context);
	}
	
	@RequestMapping(value="/operation",method = RequestMethod.GET)
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			returnString = "articleadd";
			//context.put("assorts", assortService.findLevel(assortService.findAssortments()));
			context.put("crowds", crowdService.findCrowds());
			context.put("users", userService.findUsers());
		}else{
			returnString = "articleupdate";
			context.put("model", articleService.findArticleById(id)); 
			context.put("crowds", crowdService.findArCrowds(crowdService.findCrowds(), articleCrowdService.findArticleCrowds(id)));
			context.put("users", userService.findUsers(userService.findUsers(), articleService.findArticleById(id).getAuthorId()));
			//context.put("assorts", assortService.findAssArticles(assortService.findAssortments(), articleAssortService.findArticleAssorts(id)));
		}
		context.put("user", userService.findUserById(uid));  
		
		
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("uid") int uid,HttpServletRequest req){
		String returnString = "";
		
		int intadd = articleService.insert(articleService.formArticle(req, LoginUntil.one));
		//articleAssortService.insert(req.getParameterValues("assort"), intadd, LoginUntil.one);
		articleImgService.insert(articleService.findSrc(req.getParameter("content")), intadd, LoginUntil.one);
		articleCrowdService.insert(req.getParameterValues("person"), intadd, LoginUntil.one);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "article";
			context.put("user", userService.findUserById(uid));  
			context.put("articles", articleService.findArticles()); 
		}else{
			returnString = "articleadd";
			context.put("message", LoginUntil.adderror);
		}
		return new ModelAndView(returnString,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		int intadd = articleService.update(articleService.formArticle(req, LoginUntil.zero));
		//articleAssortService.insert(req.getParameterValues("assort"), id, LoginUntil.zero);
		articleImgService.insert(articleService.findSrc(req.getParameter("content")), id, LoginUntil.zero);
		articleCrowdService.insert(req.getParameterValues("person"), id, LoginUntil.zero);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "article";
			context.put("user", userService.findUserById(uid));  
			context.put("articles", articleService.findArticles());

		}else{
			returnString = "articleupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = articleService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("articles", articleService.findArticles()); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("article",context);
	}
}
