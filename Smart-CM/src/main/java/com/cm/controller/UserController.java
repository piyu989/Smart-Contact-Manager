package com.cm.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger =LoggerFactory.getLogger(UserController.class);

	//user dashboard
	@RequestMapping(value="/profile",method = RequestMethod.GET)
	public String userDashboard(Principal principal) {
		return "user/dashboard";
	}
	//user profile
	@RequestMapping(value="/dashboard",method = RequestMethod.GET)
	public String userProfile(Authentication authentication) {
		
		String username = Helper.getEmailOfLoggedInUser(authentication);
		System.out.println("sita ram sita ram");
		System.out.println(username);

		return "user/profile";
	}
	//user add contact
	//user view contact
	//user edit contact
	//user delete contact
	//user search contact
}
