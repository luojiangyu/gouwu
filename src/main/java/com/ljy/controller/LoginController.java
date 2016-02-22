package com.ljy.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ljy.service.IUserService;
import com.ljy.util.EncodeUtil;
import com.ljy.vo.User;

@Controller
public class LoginController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/login")
	public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		password = EncodeUtil.generateMd5(password);
		User user = userService.checkUserInfo(userName, password);
		request.getSession().setAttribute("user", user);
		return user!=null?"main":"index";
	}
	@RequestMapping(value="/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("user");
    	return "index";
    }
	@RequestMapping("loginPage.do")
	public String loginPage(){
		return "index";
	}
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
