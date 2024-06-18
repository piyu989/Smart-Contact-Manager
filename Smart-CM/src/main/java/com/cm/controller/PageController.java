package com.cm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("name","piyush");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("name","piyush");
		return "about";
	}
	
	@RequestMapping("/service")
	public String service(Model model) {
		model.addAttribute("name","piyush");
		return "service";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("name","piyush");
		return "contact";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("name","piyush");
		return "login";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("name","piyush");
		return "signup";
	}
	
}
