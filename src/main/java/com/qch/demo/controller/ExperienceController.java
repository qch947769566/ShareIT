package com.qch.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qch.demo.entity.User;

/**
 * 经验案例控制器
 * @author 94776
 *
 */
@RequestMapping("/experience")
@Controller
public class ExperienceController {
	
	@RequestMapping("/")
	public ModelAndView experienceList(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Integer page = 1;
		ModelAndView mav=new ModelAndView();	
		
		String username = "";  //用户名
		String name = "";      //真实姓名
		int admin = -1;        //1-管理员，0-普通用户 ,-1-未登录
		
		//若用户已登录，则赋值
		if(user != null) {
			username = user.getUsername();			
			name = user.getName();
			admin = user.getAdmin();
		}
		
		//分类路径为全部
		mav.addObject("route", "全部");
		
		//分类
		mav.addObject("classifyList", null);
		//最新10个知识案例
		mav.addObject("newKnowList", null); 
		//获取10个热门知识案例（阅读量最高）
		mav.addObject("newHotKnowList", null);
		mav.addObject("username", username);
		mav.addObject("name", name);
		mav.addObject("admin", admin);
		mav.addObject("knowledgeList", null);
		mav.addObject("title", "经验案例");
		mav.addObject("mainPage", "/experience/indexExperience");
    	mav.addObject("mainPageKey", "#f");
    	mav.setViewName("index");
		return mav;
	}	
}
