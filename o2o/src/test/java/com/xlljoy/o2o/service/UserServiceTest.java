package com.xlljoy.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xlljoy.o2o.BaseTest;
import com.xlljoy.o2o.dto.UserExecution;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.util.ImageUnit;

public class UserServiceTest extends BaseTest{
	@Autowired
	private UserService userService;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setEnableStatus(1);
		user.setUserType(1);
		user.setEmail("balabalaemail");
		user.setName("Joy");
		user.setGender("female");
		File img1 = new File("/home/jli/Pictures/pic1.jpg");
		InputStream is;
		ImageUnit iu;
		try {
			is = new FileInputStream(img1);
			iu = new  ImageUnit(is, img1.getName());
			UserExecution ue = userService.userRegister(user, iu);
			System.out.println(ue.getStateInfo());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
