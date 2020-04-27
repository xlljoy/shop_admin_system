package com.xlljoy.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.UserDao;
import com.xlljoy.o2o.dto.UserExecution;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.enums.UserStateEnum;
import com.xlljoy.o2o.service.UserService;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.ImageUtil;
import com.xlljoy.o2o.util.PathUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User getUserById(long id) {
		if (id > -1) {
			return userDao.queryUserById(id);
		}
		return null;
	}
	@Transactional
	@Override
	public UserExecution userRegister(User user, ImageUnit profileImg) {
		if (user != null && user.getName() != null) {
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setEnableStatus(1);
			user.setUserType(1);
			int effect = userDao.insertUser(user);
			if (effect  > 0) {
				addImg(profileImg, user);
				return new UserExecution(UserStateEnum.SUCCESS, user);
			}
			return new UserExecution(UserStateEnum.INNER_ERROR);
		}
		return new UserExecution(UserStateEnum.NULL_USER);
	}
	
	public void addImg(ImageUnit profileImg, User user) {
		Long userId = user.getId();
		String targetAddrString = PathUtil.getUserImgPath(userId);
		String imgPath = ImageUtil.generateThumbnail(profileImg.getImage(), profileImg.getName(), targetAddrString);
		user.setHeadImg(imgPath);
	}
	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}
}
