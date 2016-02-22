package com.ljy.dao;

import org.springframework.stereotype.Repository;

import com.ljy.vo.User;

@Repository
public interface UserMapper {
	public User checkUserInfo(User user);

}
