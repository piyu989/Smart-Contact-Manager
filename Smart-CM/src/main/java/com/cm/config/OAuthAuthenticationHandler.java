package com.cm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cm.entity.Providers;
import com.cm.entity.User;
import com.cm.form.repository.UserRepository;
import com.cm.helper.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository repo;

	Logger logger=LoggerFactory.getLogger(OAuthAuthenticationHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("OAuthAuthentication");
		System.out.println("sita ram");
		
		DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
		
		// logger.info(user.getName());
		
		// user.getAttributes().forEach((key,value)->{
		// 	logger.info("{}=>{}",key,value);
		// });
		// logger.info(user.getAuthorities().toString());
		
		String email=user.getAttribute("email").toString();
		String name=user.getAttribute("name").toString();
		// String picture=user.getAttribute("pic").toString();

		User user1=new User();
		user1.setEmail(email);
		user1.setName(name);
		// user1.setPic(picture);
		user1.setPassword("121212");
		user1.setUserId(UUID.randomUUID().toString());
		user1.setProvider(Providers.GOOGLE);
		user1.setEnabled(true);
		user1.setEmailVerified(true);
		user1.setProvideUserId(user.getName());
		user1.setRoles_list(List.of(AppConstants.ROLE_USER));
		user1.setAbout("sita ram");

		User user2=repo.findByEmail(email).orElse(null);
		
		System.out.println(user1);
		System.out.println("sita ram");
		
		if (user2 == null) {			 
			repo.save(user1);
			System.out.println("ram ram");
		}
//		repo.save(user1);

		new DefaultRedirectStrategy().sendRedirect(request, response,"/user/dashboard");
		
	}
	
}
