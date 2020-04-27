package com.xlljoy.o2o.dao;

import com.xlljoy.o2o.entity.User;

public interface UserDao {
	User queryUserById(long id);
	int insertUser(User user);
	int updateUser(User user);
}