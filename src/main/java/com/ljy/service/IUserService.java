package com.ljy.service;

import com.ljy.vo.User;

public interface IUserService {
	public User checkUserInfo(String userName,String password);
}
