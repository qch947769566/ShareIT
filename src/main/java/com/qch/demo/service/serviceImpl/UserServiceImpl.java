package com.qch.demo.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qch.demo.dao.UserMapper;
import com.qch.demo.entity.User;
import com.qch.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUsername(username);
	}

	@Override
	public int userSave(User user) {
		// TODO Auto-generated method stub
		return userMapper.userSave(user);
	}
	
}
