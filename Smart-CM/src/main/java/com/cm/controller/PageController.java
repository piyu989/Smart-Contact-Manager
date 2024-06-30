package com.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.entity.User;
import com.cm.form.UserForm;
import com.cm.services.UserService;

@Controller
public class PageController {
	
	@Autowired
	private UserService userService;

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
		UserForm form=new UserForm();
		// form.setName("piyush");
		// form.setPhoneNumber("234234234234");
		// form.setPassword("435235235342x");
		// form.setAbout("about");
		// form.setEmail("Piyush Tomar");
		model.addAttribute("userForm", form);
		return "signup";
	}

	@PostMapping("/do-register")
	public String processRegister(@ModelAttribute UserForm userForm){
		System.out.println(userForm);
		System.out.println("sita ram");
		
		User user=User.builder()
				.name(userForm.getName())
				.email(userForm.getEmail())
				.phoneNumber(userForm.getPhoneNumber())
				.password(userForm.getPassword())
				.about(userForm.getAbout())
				.pic("https://www.google.com/imgres?q=sita%20ram&imgurl=http%3A%2F%2Fonlineprasad.com%2Fcdn%2Fshop%2Fproducts%2Fsitaram-1_grande.jpg%3Fv%3D1595505094&imgrefurl=https%3A%2F%2Fonlineprasad.com%2Fproducts%2Fprasad-sitaram-special&docid=8aVOb6jo_wPpIM&tbnid=v9fuj16M2srUHM&vet=12ahUKEwiL3oTdgYSHAxXeRWcHHSflA8AQM3oECHYQAA..i&w=552&h=600&hcb=2&ved=2ahUKEwiL3oTdgYSHAxXeRWcHHSflA8AQM3oECHYQAA")
				.build();
		
		User SavedUser=userService.save(user);
		System.out.println("saved user: "+SavedUser);
		//fetch form data
		//validate form data
		//save to db
		//message="registration sucessfully
		//redirect to login page
		return "redirect:/signup";
	}
	
}
