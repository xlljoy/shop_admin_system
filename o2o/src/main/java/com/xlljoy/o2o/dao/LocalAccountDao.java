package com.xlljoy.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xlljoy.o2o.entity.LocalAccount;

public interface LocalAccountDao {
	LocalAccount queryLocalAccountById(@Param("id")long id);
	int insertLocalAccount(LocalAccount localAccount);
	LocalAccount queryLocalAccountByUserId(@Param("userId")long userId);
	LocalAccount queryLocalAccountByUserName(@Param("userName")String userName);
	LocalAccount queryLocalAccountByUserNameAndPwd(
			@Param("userName")String userName, @Param("password")String password);
	int updateLocalAccount(@Param("userId")long userId, @Param("username")String username, 
			@Param("password")String password, @Param("newPassword")String newPassword, 
			@Param("updateTime")Date updateTime);
}
