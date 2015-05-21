package com.momia.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.momia.service.AssortGoodsService;
import com.momia.service.CrowdService;
import com.momia.service.GoodsAssortService;
import com.momia.service.GoodsCrowdService;
import com.momia.service.GoodsImgService;
import com.momia.service.GoodsService;
import com.momia.service.UserService;
import com.momia.until.LoginUntil;

@Controller
@RequestMapping("/goods")
public class GoodsAction {

	@Resource
	private UserService userService;
	
	@Resource
	private GoodsService goodsService;
	
	@Resource
	private AssortGoodsService assortService;
	
	@Resource
	private GoodsAssortService goodsAssortService;
	
	@Resource
	private CrowdService crowdService;
	
	@Resource
	private GoodsCrowdService goodsCrowdService;
	
	@Resource
	private GoodsImgService  goodsImgService;
	
	@RequestMapping("/info")
	public ModelAndView mangerinfo(@RequestParam("uid") int uid,HttpServletRequest req){
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("goodslist", goodsService.findGoods()); 
		return new ModelAndView("goods",context);
	}
	
	@RequestMapping("/operation")
	public ModelAndView operation(@RequestParam("uid") int uid,@RequestParam("flag") String flag,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		Map<String, Object> context = new HashMap<String, Object>();
		if(flag.equals("add")){
			returnString = "goodsadd";
			context.put("assorts", assortService.findAssortments());
			context.put("crowds", crowdService.findCrowds());
		}else{
			returnString = "goodsupdate";
			context.put("model", goodsService.findGoodsById(id)); 
			context.put("crowds", crowdService.findGoodsCrowds(crowdService.findCrowds(), goodsCrowdService.findGoodsCrowds(id)));
			context.put("assorts", assortService.findAssortGoods(assortService.findAssortments(), goodsAssortService.findGoodsAssorts(id)));
		}
		context.put("user", userService.findUserById(uid));  
		
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(@RequestParam("uid") int uid,HttpServletRequest req){
		String returnString = "";
		
		int intadd = goodsService.insert(goodsService.formGoods(req, LoginUntil.one));
		goodsImgService.insert(req.getParameter("picUrl"), intadd, LoginUntil.one);
		goodsCrowdService.insert(req.getParameterValues("person"), intadd, LoginUntil.one);
		goodsAssortService.insert(req.getParameterValues("assort"), intadd, LoginUntil.one);
		//articleAssortService.insert(req.getParameterValues("assort"), intadd, LoginUntil.one);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "goods";
			context.put("user", userService.findUserById(uid));  
			context.put("goodslist", goodsService.findGoods()); 
		}else{
			returnString = "goodsadd";
			context.put("message", LoginUntil.adderror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		String returnString = "";
		
		int intadd = goodsService.update(goodsService.formGoods(req, LoginUntil.zero));
		String picStr = req.getParameter("picUrl");
		if(!picStr.equals("") || picStr != "" ){
			goodsImgService.insert(picStr, id, LoginUntil.zero);
		}
		goodsCrowdService.insert(req.getParameterValues("person"), id, LoginUntil.zero);
		goodsAssortService.insert(req.getParameterValues("assort"), id, LoginUntil.zero);
		Map<String, Object> context = new HashMap<String, Object>();
		if(intadd > 0){
			returnString = "goods";
			context.put("user", userService.findUserById(uid));  
			context.put("goodslist", goodsService.findGoods()); 
		}else{
			returnString = "goodsupdate";
			context.put("message", LoginUntil.updateerror);  
		}
		return new ModelAndView(returnString,context);
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam("uid") int uid,@RequestParam("id") int id,HttpServletRequest req){
		int intdel = goodsService.delete(id);
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("user", userService.findUserById(uid));  
		context.put("goodslist", goodsService.findGoods()); 
		if(intdel > 0){
			context.put("message", LoginUntil.delsuccess); 
		}else{
			context.put("message", LoginUntil.delerror); 
		}
		 
		return new ModelAndView("goods",context);
	}
}
