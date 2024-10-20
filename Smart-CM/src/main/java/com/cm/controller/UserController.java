package com.cm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.entity.User;
import com.cm.helper.Helper;
import com.cm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger =LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void addLoggedInUserInformation(Model model,Authentication authentication) {
		
		String username = Helper.getEmailOfLoggedInUser(authentication);

		User user=userService.getUserByEmail(username);

		// System.out.println(user.getName());
		// System.out.println(user.getEmail());

		model.addAttribute("loggedInUser",user);
	}

	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String userDashboard(Model model,Authentication authentication) {
		
		return "user/profile";
	}

	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String userProfile(Model model,Authentication authentication) {
		

//		model.addAttribute("email",user.getEmail());

		return "user/dashboard";
	}
	//user add contact
	//user view contact
	//user edit contact
	//user delete contact
	//user search contact
}
