package com.ljy.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljy.dao.UserMapper;
import com.ljy.service.IUserService;
import com.ljy.vo.User;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserMapper userDao;

	@Override
	public User checkUserInfo(String userName, String password) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUserName(userName);;
		user.setPassword(password);
		return userDao.checkUserInfo(user);
	}
   
}
