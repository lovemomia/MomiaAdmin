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

import com.momia.service.EventService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/event")
public class EventAaction {

	@Resource
	private UserService userService;
	
	@Resource
	private EventService eventService;
	
	@RequestMapping("/info")
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("events", eventService.findEvents()); 
		return new ModelAndView("event",context);
	}
	
	@RequestMapping("/operation")
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			returnString = "eventadd";
			context.put("nowdate", (new SimpleDateFormat("MM/dd/yyyy")).format(new Date()));
		}else{
			returnString = "eventupdate";
			context.put("model", eventService.findEventId(id)); 
		}
		context.put("user", userService.findUserById(uid));  
		
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("uid") int uid,HttpServletRequest req){
		String returnString = "";
		int intadd = eventService.insert(eventService.formEvent(req, LoginUntil.one));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "event";
			context.put("user", userService.findUserById(uid));  
			context.put("events", eventService.findEvents()); 
		}else{
			returnString = "eventadd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		int intadd = eventService.update(eventService.formEvent(req, LoginUntil.zero));
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "event";
			context.put("user", userService.findUserById(uid));  
			context.put("events", eventService.findEvents()); 
		}else{
			returnString = "eventupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = eventService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("events", eventService.findEvents()); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("event",context);
	}
}
