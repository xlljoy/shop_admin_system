package com.xlljoy.o2o.service;

import com.xlljoy.o2o.dto.UserExecution;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.util.ImageUnit;

public interface UserService {
	User getUserById(long id);
	UserExecution userRegister(User user, ImageUnit profileImg);
	int updateUser(User user);
}
