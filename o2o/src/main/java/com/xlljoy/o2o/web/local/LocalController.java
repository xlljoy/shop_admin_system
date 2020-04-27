package com.xlljoy.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {
	@RequestMapping(value="/accountbind", method=RequestMethod.GET)
	public String accountBind() {
		return "local/accountbind";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "local/login";
	}
	@RequestMapping(value="/changepwd", method=RequestMethod.GET)
	public String changePassword() {
		return "local/changepwd";
	}
}
