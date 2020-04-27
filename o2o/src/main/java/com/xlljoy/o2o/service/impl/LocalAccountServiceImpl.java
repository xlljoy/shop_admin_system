package com.xlljoy.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlljoy.o2o.dao.LocalAccountDao;
import com.xlljoy.o2o.dto.LocalAccountExecution;
import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.enums.LocalAccountStateEnum;
import com.xlljoy.o2o.exceptions.LocalAccountException;
import com.xlljoy.o2o.service.LocalAccountService;
import com.xlljoy.o2o.util.MD5;

@Service
public class LocalAccountServiceImpl implements LocalAccountService {
	@Autowired
	private LocalAccountDao localAccountDao;	
	@Override
	public LocalAccount getLocalAccountById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public LocalAccountExecution addLocalAccount(LocalAccount localAccount) {
		if (localAccount == null || localAccount.getUser() == null || 
			localAccount.getUser().getId() == null ||
			localAccount.getPassword() == null) {
			
			return new LocalAccountExecution(LocalAccountStateEnum.NULL_USER);
		}
		
		// check if this localaccount exists already
		LocalAccount existAccount = localAccountDao.queryLocalAccountByUserNameAndPwd(
				localAccount.getUserName(), MD5.getMd5(localAccount.getPassword()));
		if (existAccount != null) {
			// means, this account exist
			return new LocalAccountExecution(LocalAccountStateEnum.DUPLICATE_ACCOUNT);
		}
		try {
			localAccount.setCreateTime(new Date());
			localAccount.setUpdateTime(new Date());
			int effect = localAccountDao.insertLocalAccount(localAccount);
			if (effect > 0) {
				return new LocalAccountExecution(LocalAccountStateEnum.SUCCESS, localAccount);
			} 
			return new LocalAccountExecution(LocalAccountStateEnum.INNER_ERROR);
		} catch (Exception e) {
			throw new LocalAccountException(e.getMessage());
		}

	}

	@Override
	public LocalAccount getLocalAccountByUserId(long userId) {
		if (userId > -1) {
			return localAccountDao.queryLocalAccountByUserId(userId);
		}
		return null;
	}

	@Override
	public LocalAccount getLocalAccountByUserName(String userName) {
		if (userName != null) {
			return localAccountDao.queryLocalAccountByUserName(userName);
		}
		return null;
	}

	@Override
	public LocalAccount getLocalAccountByUserNameAndPwd(String userName, String password) {
		if (userName != null && password != null) {
			return localAccountDao.queryLocalAccountByUserNameAndPwd(userName, MD5.getMd5(password));
		}
		return null;
	}

	@Override
	@Transactional
	public LocalAccountExecution bindLocalAccount(LocalAccount localAccount) throws LocalAccountException {
		if (localAccount == null || localAccount.getUser() == null || 
				localAccount.getUser().getId() == null ||
				localAccount.getPassword() == null || localAccount.getUserName() == null) {
				
				return new LocalAccountExecution(LocalAccountStateEnum.NULL_USER);
			}
			
			// check if this localaccount exists already
			LocalAccount existAccount = localAccountDao.queryLocalAccountByUserId(
					localAccount.getUser().getId());
			if (existAccount != null) {
				// means, this account exist
				return new LocalAccountExecution(LocalAccountStateEnum.DUPLICATE_ACCOUNT);
			}
			try {
				localAccount.setCreateTime(new Date());
				localAccount.setUpdateTime(new Date());
				localAccount.setPassword(MD5.getMd5(localAccount.getPassword()));
				int effect = localAccountDao.insertLocalAccount(localAccount);
				if (effect > 0) {
					return new LocalAccountExecution(LocalAccountStateEnum.SUCCESS, localAccount);
				} 
				return new LocalAccountExecution(LocalAccountStateEnum.INNER_ERROR);
			} catch (Exception e) {
				throw new LocalAccountException("insertLocalAccount error: " + e.getMessage());
			}
	}

	@Override
	@Transactional
	public LocalAccountExecution modifyLocalAccount(long userId, String username, 
			String password, String newPassword) throws LocalAccountException {
		if (userId < -1 || username == null || password == null || newPassword == null) {
			return new LocalAccountExecution(LocalAccountStateEnum.NULL_USER);
		}
		try {
			int effect = localAccountDao.updateLocalAccount(userId, username, 
					MD5.getMd5(password), MD5.getMd5(newPassword), new Date());
			if (effect < 1) {
				throw new LocalAccountException("failed to change password");
			}
		} catch (Exception e) {
			throw new LocalAccountException("failed to change password: " + e.getMessage());
		}		
		return new LocalAccountExecution(LocalAccountStateEnum.SUCCESS);
	}

}
