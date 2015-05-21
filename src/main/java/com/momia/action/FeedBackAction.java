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

import com.momia.service.FeedBackService;
import com.momia.service.UserService;

@Controller
@RequestMapping("/feedback")
public class FeedBackAction {

	@Resource
	private UserService userService;
	
	@Resource
	private FeedBackService feedBackService;
	
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("feedBacks", feedBackService.findFeedBacks()); 
		return new ModelAndView("feedback",context);
	}
	
	@RequestMapping(value="/operation",method = RequestMethod.GET)
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("feedBacks", feedBackService.findFeedBacks()); 
		context.put("model", feedBackService.findFeedBackById(id)); 
		context.put("user", userService.findUserById(uid));  
		
		return new ModelAndView("feedbackdetails",context);
	}
}
