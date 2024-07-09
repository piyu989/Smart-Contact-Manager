package com.cm.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationHandler implements AuthenticationSuccessHandler {

	Logger logger=LoggerFactory.getLogger(OAuthAuthenticationHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("OAuthAuthentication");
		System.out.println("sita ram");
		new DefaultRedirectStrategy().sendRedirect(request, response,"/user/dashboard");
		
		DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
		
		logger.info(user.getName());
		
		user.getAttributes().forEach((key,value)->{
			logger.info("{}=>{}",key,value);
		});
		logger.info(user.getAuthorities().toString());
		
		
	}
	
}
