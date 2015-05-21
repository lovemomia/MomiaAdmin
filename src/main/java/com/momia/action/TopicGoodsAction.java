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

//import com.momia.service.AssortGoodsService;
//import com.momia.service.CrowdService;
//import com.momia.service.GoodsAssortService;
import com.momia.service.GoodsService;
//import com.momia.service.TopicCrowdService;
//import com.momia.service.TopicGoodsAssortService;
import com.momia.service.TopicGoodsService;
import com.momia.service.TopicService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/topicgoods")
public class TopicGoodsAction {

	@Resource
	private UserService userService;
	
	@Resource
	private TopicService topicService;
	
	//@Resource
	//private CrowdService crowdService;
	
	//@Resource
	//private AssortGoodsService assortService;
	
	@Resource
	private GoodsService goodsService;
	
	//@Resource
	//private GoodsAssortService goodsAssortService;
	
	//@Resource
	//private TopicCrowdService topicCrowdService;
	
	@Resource
	private TopicGoodsService topicGoodsService;
	
	//@Resource
	//private TopicGoodsAssortService topicGoodsAssortService;
	
	
	@RequestMapping("/info")
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("topics", topicService.findTopics(LoginUntil.two)); 
		return new ModelAndView("topicgoods",context);
	}
	
	@RequestMapping("/operation")
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String mavStr = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			mavStr = "topicgoodsadd";
			//context.put("crowds", crowdService.findCrowds());
			//context.put("assorts", assortService.findLevel(assortService.findAssortments()));
			context.put("nowdate", (new SimpleDateFormat("MM/dd/yyyy")).format(new Date()));
		}else{
			mavStr = "topicgoodsupdate";
			context.put("model", topicService.findTopicById(id)); 
			//context.put("crowds", crowdService.findCrowds(crowdService.findCrowds(), topicCrowdService.findTopicCrowds(id)));
			//context.put("assorts", assortService.findAssortgoods(assortService.findAssortments(), topicGoodsAssortService.findTopicGoodsAssorts(id)));
		}
		context.put("user", userService.findUserById(uid));
		return new ModelAndView(mavStr,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("submitType") int submitType,@RequestParam("uid") int uid,HttpServletRequest req){
		String mavStr = "";
		int intadd = topicService.insert(topicService.formTopic(req,  LoginUntil.one, LoginUntil.two));
		//topicCrowdService.insert(req.getParameterValues("person"), intadd, LoginUntil.one);
		//topicGoodsAssortService.insert(req.getParameterValues("assort"), intadd, LoginUntil.one);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			mavStr = "topicgoods";
			context.put("user", userService.findUserById(uid));  
			context.put("topics", topicService.findTopics(LoginUntil.two)); 
		}else{
			mavStr = "topicgoodsadd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(mavStr,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("submitType") int submitType,@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String mavStr = "";
		
		int intadd = topicService.update(topicService.formTopic(req, LoginUntil.zero, LoginUntil.two));
		//topicCrowdService.insert(req.getParameterValues("person"), id, LoginUntil.zero);
		//topicGoodsAssortService.insert(req.getParameterValues("assort"), id, LoginUntil.zero);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			mavStr = "topicgoods";
			context.put("user", userService.findUserById(uid));  
			context.put("topics", topicService.findTopics(LoginUntil.two)); 
		}else{
			mavStr = "topicgoodsupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(mavStr,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = topicService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("topics", topicService.findTopics(LoginUntil.two)); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("topicgoods",context);
	}
	
	@RequestMapping("/oparticles")
	public ModelAndView oparticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("goodslist", goodsService.findgoods(topicGoodsService.findTopicGoods(id))); 
		return new ModelAndView("topgoods",context);
	}
	
	@RequestMapping("/addtoparticles")
	public ModelAndView addarticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		topicGoodsService.insert(req.getParameterValues("goods"), id, LoginUntil.one);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("goodslist", goodsService.findgoods(topicGoodsService.findTopicGoods(id))); 
		return new ModelAndView("topgoods",context);
	}
	
	@RequestMapping("/getgoods")
	public ModelAndView backarticles(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("goodslist", goodsService.findgoods(goodsService.findGoods(),topicGoodsService.findTopicGoods(id))); 
		return new ModelAndView("tgoods",context);
	}
	
	@RequestMapping("/deletegoods")
	public ModelAndView deletearticle(@RequestParam("uid") int uid,@RequestParam("id") int id,@RequestParam("gid") int gid,HttpServletRequest req){
		int intdel = topicGoodsService.deletegoods(id, gid);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("model", topicService.findTopicById(id));  
		context.put("goodslist", goodsService.findgoods(topicGoodsService.findTopicGoods(id))); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		return new ModelAndView("topgoods",context);
	}
}
