package com.xlljoy.o2o.dao;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.dto.LocalAccountExecution;
import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.util.MD5;

public class LocalAccountDaoTest extends BaseTest{
	@Autowired 
	private LocalAccountDao localAccountDao;
	@Test
	public void testQueryLocalAccountById() {
		LocalAccount localAccount = localAccountDao.queryLocalAccountById(1L);
		System.out.println(localAccount.getUserName());
	}
	@Test
	public void testQueryLocalAccountByUserId() {
		LocalAccount localAccount = localAccountDao.queryLocalAccountByUserId(2L);
		System.out.println(localAccount.getUserName());
	}
	@Test
	public void testQueryLocalAccountByUserName() {
		LocalAccount localAccount = localAccountDao.queryLocalAccountByUserName("Betty");
		System.out.println(localAccount.getId());
	}
	
	@Test
	public void testQueryLocalAccountByUserNameAndPwd() {
		String password = "1234";
		LocalAccount localAccount = localAccountDao.queryLocalAccountByUserNameAndPwd("Betty", MD5.getMd5(password));
		if (localAccount != null) {
			System.out.println(localAccount.getId());
			System.out.println(localAccount.getUser().getId());
		}
	}
	
	@Test
	public void testInsertLocalAccount() {
		LocalAccount localAccount = new LocalAccount();
		localAccount.setCreateTime(new Date());
		localAccount.setPassword(MD5.getMd5("password"));
		localAccount.setUserName("username");
		User user = new User();
		user.setId(1L);
		localAccount.setUser(user);
		int effect = localAccountDao.insertLocalAccount(localAccount);
		System.out.println(effect);
	}
	
	@Test
	public void testUpdateLocalAccount() {
		int effect = localAccountDao.updateLocalAccount(2L, "Betty", "password", "newPassword", new Date());
		System.out.println(effect);
	}
}
