package com.xlljoy.o2o.web.local;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xlljoy.o2o.dto.UserExecution;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.enums.UserStateEnum;
import com.xlljoy.o2o.service.UserService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;
import com.xlljoy.o2o.util.ImageUnit;
import com.xlljoy.o2o.util.VerifyCodeUtil;

@Controller
@RequestMapping("/frontend")
public class UserRegisterController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/adduser", method=RequestMethod.POST)
	@ResponseBody
	Map<String, Object> adduser(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1. check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		// 2. get info from front end and transfer to user.class
		String userStr = HttpServletRequestUtil.getString(request, "userStr");
		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		try {
			user = objectMapper.readValue(userStr, User.class);
		} catch (IOException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		} 
		
		
		// 3 img processing
		MultipartHttpServletRequest  multipartHttpServletRequest;
		CommonsMultipartFile headImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			headImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("headImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "profile img cannot be null");
			return modelMap;
		}
		
		ImageUnit mainImg = new ImageUnit();
		try {
			mainImg.setImage(headImg.getInputStream());
			mainImg.setName(headImg.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
			
		if (user != null && headImg != null) {
			UserExecution ue = userService.userRegister(user, mainImg);
			if (ue.getState() != UserStateEnum.SUCCESS.getState()) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "profile img cannot be null");
				return modelMap;
			} else {
				int imageEffect = userService.updateUser(user);
				if (imageEffect > 0) {
					request.getSession().setAttribute("user", ue.getUser());
					modelMap.put("success", true);
					return modelMap;
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "failed to add profile picture");
					return modelMap;
				}

			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "cannot get user properly");
			return modelMap;
		}
	}

}
