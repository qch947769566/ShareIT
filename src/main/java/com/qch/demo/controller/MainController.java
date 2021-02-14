package com.qch.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qch.demo.entity.User;
import com.qch.demo.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	/*
	 * @RequestMapping("/test")
	 * 
	 * @ResponseBody public String Test() { System.out.println("SpringBoot项目启动");
	 * return "SpringBoot项目启动"; }
	 */
	
	//进入登录页面
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//进入注册页面
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	//注册
	@RequestMapping("/register/submit")
	@ResponseBody
	public Map<String,String> register_submit(HttpServletRequest request) {
		Map<String,String> data = new HashMap<String,String>();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		User user = userService.getUserByUsername(username);
		if(user == null) {
			User user_n = new User();
			user_n.setUsername(username);
			user_n.setPassword(password);
			user_n.setName(name);
			int res = userService.userSave(user_n);
			if(res == 1){
        		data.put("type", "success");
    			data.put("msg", "注册成功,将跳转至登录界面！");    			
        	}
        	else{
        		data.put("type", "error");
    			data.put("msg", "注册失败，请重新操作！"); 			
        	}
		}
		else {
			data.put("type", "error");
			data.put("msg", "当前用户已存在,请重新填写用户名！");
		}
		
		return data;
	}
	
	//登录验证
	@ResponseBody
	@RequestMapping("/login/submit")
	public Map<String,String> login_submit(HttpServletRequest request) {
		Map<String,String> data = new HashMap<String,String>();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//通过用户名查询用户信息
		User user = userService.getUserByUsername(username);
		if(user != null) {
			String password_db = user.getPassword();
			if(password_db.equals(password)) {
				data.put("msg", "完成登录！");
				data.put("type", "success");
				request.getSession().setAttribute("user", user);
			}
			else {
				data.put("msg", "密码不正确，请重新输入密码！");
				data.put("type", "error");
			}
		}
		else {
			data.put("msg", "该用户并不存在，请先注册！");
			data.put("type", "error");
		}
		return data;
	}
	
	//登录成功，进入主页
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		//获取登录时的用户信息
		User user = (User)request.getSession().getAttribute("user");
		
		String username = "";  //用户名
		String name = "";      //真实姓名
		int admin = -1;        //1-管理员，0-普通用户 ,-1-未登录
		
		//若用户已登录，则赋值
		if(user != null) {
			username = user.getUsername();			
			name = user.getName();
			admin = user.getAdmin();
		}
		
		mav.addObject("username", username);
		mav.addObject("name", name);
		mav.addObject("admin", admin);
		mav.addObject("title", "首页");
		mav.addObject("knowledgeList", null);
		mav.addObject("mainPage", "/index-list");
    	mav.addObject("mainPageKey", "#f");
		mav.setViewName("index");
		return mav;
	}
	
	//关于本站
	@RequestMapping("/about")
	public ModelAndView about(HttpServletRequest request) {	
		
		ModelAndView mav = new ModelAndView();
    	
    	User user = (User) request.getSession().getAttribute("user");
    	
    	String username = "";  //用户名
		String name = "";      //真实姓名
		int admin = -1;        //1-管理员，0-普通用户 ,-1-未登录
		
		//若用户已登录，则赋值
		if(user != null) {
			username = user.getUsername();			
			name = user.getName();
			admin = user.getAdmin();
		}
    	
		mav.addObject("username", username);
		mav.addObject("name", name);
		mav.addObject("admin", admin);
    	//最新10个知识案例
    	mav.addObject("newKnowList", null); 
    	//获取10个热门知识案例（阅读量最高）
    	mav.addObject("newHotKnowList", null);
    	mav.addObject("title", "关于本站");
    	mav.addObject("mainPage", "common/aboutMe");
    	mav.addObject("mainPageKey", "#a");
    	mav.setViewName("index");
        return mav;
	}
	
	//关于本站
	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest request) {	
		
		ModelAndView mav = new ModelAndView();
    	
    	User user = (User) request.getSession().getAttribute("user");
    	
    	String username = "";  //用户名
		String name = "";      //真实姓名
		int admin = -1;        //1-管理员，0-普通用户 ,-1-未登录
		
		//若用户已登录，则赋值
		if(user != null) {
			username = user.getUsername();			
			name = user.getName();
			admin = user.getAdmin();
		}
    	
		mav.addObject("username", username);
		mav.addObject("name", name);
		mav.addObject("admin", admin);
    	mav.addObject("title", "个人中心");
    	mav.setViewName("/admin/index");
        return mav;
	}
}
