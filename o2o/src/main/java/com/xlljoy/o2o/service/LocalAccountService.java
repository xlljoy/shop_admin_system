package com.xlljoy.o2o.service;

import com.xlljoy.o2o.dto.LocalAccountExecution;
import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.exceptions.LocalAccountException;

public interface LocalAccountService {
	LocalAccount getLocalAccountById(long id);
	LocalAccountExecution addLocalAccount(LocalAccount localAccount);
	LocalAccount getLocalAccountByUserId(long userId);
	LocalAccount getLocalAccountByUserName(String userName);
	LocalAccount getLocalAccountByUserNameAndPwd(String userName, String password);
	LocalAccountExecution bindLocalAccount(LocalAccount localAccount) throws LocalAccountException;
	LocalAccountExecution modifyLocalAccount(long userId, String username, 
			String password,String newPassword) throws LocalAccountException;
}
