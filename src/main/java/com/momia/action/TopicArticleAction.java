package com.momia.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.momia.service.ArticleService;
//import com.momia.service.AssortArticleService;
//import com.momia.service.CrowdService;
//import com.momia.service.TopicArticleAssortService;
import com.momia.service.TopicArticleService;
//import com.momia.service.TopicCrowdService;
import com.momia.service.TopicService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/topicarticle")
public class TopicArticleAction {

	@Resource
	private UserService userService;
	
	@Resource
	private TopicService topicService;
	
	//@Resource
	//private CrowdService crowdService;
	
	//@Resource
	//private AssortArticleService assortService;
	
	//@Resource
	//private TopicCrowdService topicCrowdService;
	
	//@Resource
	//private TopicArticleAssortService topicArticleAssortService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private TopicArticleService topicArticleService;
	
	
	@RequestMapping("/info")
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("topics", topicService.findTopics(LoginUntil.one)); 
		return new ModelAndView("topicarticle",context);
	}
	
	@RequestMapping("/operation")
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String mavStr = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			mavStr = "topicarticleadd";
			//context.put("assorts", assortService.findLevel(assortService.findAssortments()));
			//context.put("crowds", crowdService.findCrowds());
			context.put("nowdate", (new SimpleDateFormat("MM/dd/yyyy")).format(new Date()));
			
		}else{
			mavStr = "topicarticleupdate";
			context.put("model", topicService.findTopicById(id)); 
			//context.put("crowds", crowdService.findCrowds(crowdService.findCrowds(), topicCrowdService.findTopicCrowds(id)));
			//context.put("assorts", assortService.findAssortArticles(assortService.findAssortments(), topicArticleAssortService.findTopicArticleAssorts(id)));
		}
		context.put("user", userService.findUserById(uid));
		
		return new ModelAndView(mavStr,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("submitType") int submitType,@RequestParam("uid") int uid,HttpServletRequest req){
		String mavStr = "";
		int intadd = topicService.insert(topicService.formTopic(req, LoginUntil.one, LoginUntil.one));
		//topicCrowdService.insert(req.getParameterValues("person"), intadd, LoginUntil.one);
		//topicArticleAssortService.insert(req.getParameterValues("assort"), intadd, LoginUntil.one);
		
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			mavStr = "topicarticle";
			context.put("user", userService.findUserById(uid));  
			context.put("topics", topicService.findTopics(LoginUntil.one)); 
		}else{
			mavStr = "topicarticleadd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(mavStr,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("submitType") int submitType,@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String mavStr = "";
		int intadd = topicService.update(topicService.formTopic(req, LoginUntil.zero, LoginUntil.one));
		//topicCrowdService.insert(req.getParameterValues("person"), id, LoginUntil.zero);
		//topicArticleAssortService.insert(req.getParameterValues("assort"), id, LoginUntil.zero);
		
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			mavStr = "topicarticle";
			context.put("user", userService.findUserById(uid));  
			context.put("topics", topicService.findTopics(LoginUntil.one)); 
			
		}else{
			mavStr = "topicarticleupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(mavStr,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = topicService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("topics", topicService.findTopics(LoginUntil.one)); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("topicarticle",context);
	}
	
	@RequestMapping("/oparticles")
	public ModelAndView oparticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("articles", articleService.findArticles(topicArticleService.findTopicArticles(id))); 
		return new ModelAndView("toparticles",context);
	}
	
	@RequestMapping("/addtoparticles")
	public ModelAndView addarticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		topicArticleService.insert(req.getParameterValues("article"), id, LoginUntil.one);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("articles", articleService.findArticles(topicArticleService.findTopicArticles(id))); 
		return new ModelAndView("toparticles",context);
	}
	
	@RequestMapping("/getarticles")
	public ModelAndView backarticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("articles", articleService.findArticles(articleService.findArticles(), topicArticleService.findTopicArticles(id))); 
		return new ModelAndView("tarticles",context);
	}
	
	@RequestMapping("/deletearticle")
	public ModelAndView deletearticle(@RequestParam("uid") int uid,@RequestParam("id") int id,@RequestParam("arid") int arid,HttpServletRequest req){
		int intdel = topicArticleService.deletearticle(id, arid);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("articles", articleService.findArticles(topicArticleService.findTopicArticles(id))); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		return new ModelAndView("toparticles",context);
	}
}
