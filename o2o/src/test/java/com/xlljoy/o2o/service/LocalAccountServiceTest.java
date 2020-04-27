package com.xlljoy.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.dto.LocalAccountExecution;
import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.entity.User;


public class LocalAccountServiceTest extends BaseTest {
	@Autowired
	private LocalAccountService localAccountService;
	@Test
	public void testBindLocalAccount() {
		LocalAccount localAccount = new LocalAccount();
		User user = new User();
		user.setId(3L);
		localAccount.setUser(user);
		localAccount.setUserName("the first");
		localAccount.setPassword("password");
		LocalAccountExecution ex = localAccountService.bindLocalAccount(localAccount);
		System.out.println(ex.getStateInfo());
	}
	
	@Test
	public void testModifyLocalAccount() {
		String password = "newPdddasord";
		String newPassword = "123456";
		LocalAccountExecution ex = localAccountService.modifyLocalAccount(1L, "username", password, newPassword);
		System.out.println(ex.getStateInfo());
		LocalAccount la = localAccountService.getLocalAccountByUserNameAndPwd("username", newPassword);
		System.out.println(la.getUser().getName());
	}
}
