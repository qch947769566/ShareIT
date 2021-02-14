package com.qch.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.qch.demo.entity.User;
/**
 * 用户
 * @author 94776
 *
 */
@Mapper
public interface UserMapper {
	
	//查看指定用户名的用户
	User getUserByUsername(String username); 
	
	//注册成功，存入用户信息（仅普通用户）
	int userSave(User user);
}
