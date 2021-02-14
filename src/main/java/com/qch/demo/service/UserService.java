package com.qch.demo.service;

import com.qch.demo.entity.User;

public interface UserService {
	//查看指定用户名的用户
	User getUserByUsername(String username); 
	
	//注册成功，存入用户信息（仅普通用户）
	int userSave(User user);
}
