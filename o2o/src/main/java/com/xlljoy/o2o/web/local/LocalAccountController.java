package com.xlljoy.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlljoy.o2o.dto.LocalAccountExecution;
import com.xlljoy.o2o.entity.LocalAccount;
import com.xlljoy.o2o.entity.User;
import com.xlljoy.o2o.enums.LocalAccountStateEnum;
import com.xlljoy.o2o.service.LocalAccountService;
import com.xlljoy.o2o.util.HttpServletRequestUtil;
import com.xlljoy.o2o.util.VerifyCodeUtil;

@Controller
@RequestMapping(value ="/local", method= {RequestMethod.GET, RequestMethod.POST})
public class LocalAccountController {
	@Autowired
	private LocalAccountService localAccountService;
	
	@RequestMapping(value="/binglocalaccount", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> bingLocalRequst(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1. check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		User user = (User) request.getSession().getAttribute("user");
		if (userName != null && password != null && user != null && user.getId() != null) {
			LocalAccount localAccount = new LocalAccount();
			localAccount.setUserName(userName);
			localAccount.setPassword(password);
			localAccount.setUser(user);
			LocalAccountExecution le = localAccountService.bindLocalAccount(localAccount);
			if (le.getState() == LocalAccountStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", le.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "username and password cannot be null");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/changelocalaccountpwd", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeLocalAccountPwd(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 1. check verify code 
		if (!VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		String username = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		User user = (User) request.getSession().getAttribute("user");
		if (username != null && password != null && user != null && user.getId() != null && newPassword != null) {
			long userId = user.getId();
			LocalAccount accountFromUserId = localAccountService.getLocalAccountByUserId(userId);
			if (accountFromUserId == null || !accountFromUserId.getUserName().equals(username)) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "no right to change, wrong user login");
				return modelMap;
			}
			LocalAccountExecution le = localAccountService.modifyLocalAccount(userId, username, password, newPassword);
			if (le.getState() == LocalAccountStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", le.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "username and password cannot be null");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/logincheck", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginCheck(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");		
		// 1. check verify code 
		if (needVerify && !VerifyCodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please input right verify code");
			return modelMap;
		}
		String username = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		if (username != null && password != null) {
			LocalAccount localAccount = localAccountService.getLocalAccountByUserNameAndPwd(username, password);
			if (localAccount != null && localAccount.getUser() != null) {
				request.getSession().setAttribute("user", localAccount.getUser());
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "username or password is wrong");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "username and password cannot be null");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);

		return modelMap;
	}
}
