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

import com.momia.service.GoodsLinkService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/goodslink")
public class GoodsLinkAction {

	@Resource
	private GoodsLinkService goodsLinkService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,@RequestParam("gid") int gid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("gid", gid);
		context.put("user", userService.findUserById(uid));  
		context.put("links", goodsLinkService.findGoodsLinkByGid(gid)); 
		return new ModelAndView("link",context);
	}
	
	@RequestMapping(value="/operation",method = RequestMethod.GET)
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,@RequestParam("gid") int gid,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("gid", gid);
		if(flag.equals("add")){
			returnString = "linkadd";
		}else{
			returnString = "linkupdate";
			context.put("model", goodsLinkService.findGoodsLinkById(id)); 
		}
		context.put("user", userService.findUserById(uid));  
		
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("uid") int uid,@RequestParam("gid") int gid,HttpServletRequest req){
		String returnString = "";
		int intadd = goodsLinkService.insert(goodsLinkService.formGoodsLink(req, gid,LoginUntil.zero));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("gid", gid);
		if(intadd > 0){
			returnString = "link";
			context.put("user", userService.findUserById(uid));  
			context.put("links", goodsLinkService.findGoodsLinkByGid(gid)); 
		}else{
			returnString = "linkadd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("uid") int uid,@RequestParam("id") int id,@RequestParam("gid") int gid,HttpServletRequest req){
		String returnString = "";
		int intadd = goodsLinkService.update(goodsLinkService.formGoodsLink(req, gid, id));
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("gid", gid);
		if(intadd > 0){
			returnString = "link";
			context.put("user", userService.findUserById(uid));  
			context.put("links", goodsLinkService.findGoodsLinkByGid(gid)); 
		}else{
			returnString = "linkupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,@RequestParam("gid") int gid,HttpServletRequest req){
		int intdel = goodsLinkService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("gid", gid);
		context.put("user", userService.findUserById(uid));  
		context.put("links", goodsLinkService.findGoodsLinkByGid(gid)); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("link",context);
	}
	
	
}
