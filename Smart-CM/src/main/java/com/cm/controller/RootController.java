package com.cm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cm.entity.User;
import com.cm.helper.Helper;
import com.cm.services.UserService;

@ControllerAdvice
public class RootController {
	Logger logger =LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void addLoggedInUserInformation(Model model,Authentication authentication) {
		if(authentication==null)return;
		
		String username = Helper.getEmailOfLoggedInUser(authentication);

		User user=userService.getUserByEmail(username);
		
		// System.out.println("sita sita ram ram");
		
		if(user==null) {
			System.out.println("user is null");
		}else {			
			// System.out.println(user.getName());
			// System.out.println(user.getEmail());
			
			model.addAttribute("loggedInUser",user);
		}
		
	}
}
