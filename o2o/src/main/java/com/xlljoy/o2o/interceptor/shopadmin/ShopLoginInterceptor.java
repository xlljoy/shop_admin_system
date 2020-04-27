package com.xlljoy.o2o.interceptor.shopadmin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xlljoy.o2o.entity.User;

public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
	// pre interceptor: before we call controller, we do interceptor
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		// get user info from session
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			User user = (User) userObj;
			if (user != null && user.getId() != null && user.getId() > 0 && user.getEnableStatus() == 1) {
				return true;
			}
		}
		// if does not satisfy, redirect to login page
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open('" + request.getContextPath() + "/local/login?usertype=2','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
}
