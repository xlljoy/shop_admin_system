package com.xlljoy.o2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.entity.User;

public class UserDaoTest extends BaseTest {
	@Autowired
	private UserDao userDao;
	@Test
	public void testQueryUser() {
		User user = userDao.queryUserById(1L);
		System.out.println(user.getName());
		System.out.println(user.getUserType());
	}
	@Test
	public void testInsertUser() {
		User user = new User();
		user.setCreateTime(new Date());
		user.setEmail("mmiiu@ususu.com");
		user.setEnableStatus(1);
		user.setGender("male");
		user.setName("Marc");
		user.setUserType(2);
		user.setUpdateTime(new Date());
		user.setHeadImg("/home/jli/Pictures/pic1.jpg");
		int effect = userDao.insertUser(user);
		System.out.println(effect);
	}
	
	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setId(2L);
		user.setHeadImg("/home/jli/Pictures/pic2.jpg");
		int effect = userDao.updateUser(user);
		System.out.println(effect);
	}
}
