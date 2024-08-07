package com.cm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cm.services.impl.CustomUserDetailService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailService userDetailService;
	
	@Autowired
	private OAuthAuthenticationHandler oAuthAuthenticationHandler;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(encoder());
		return provider;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		
		httpSecurity.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/user/**").authenticated();
			authorize.anyRequest().permitAll();
		});
		
		httpSecurity.formLogin(formLogin->{   
			formLogin.loginPage("/login");
			formLogin.loginProcessingUrl("/log");
//			formLogin.successForwardUrl("/user/dashboard");
            formLogin.defaultSuccessUrl("/user/profile", true);
			formLogin.failureForwardUrl("/login?error=true");
//            formLogin.failureUrl("/login?error=true");  // Use failureUrl instead of failureForwardUrl
			formLogin.usernameParameter("email");
			formLogin.passwordParameter("password");
		});
		
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.logout(logoutForm -> {
			logoutForm.logoutUrl("/do-logout");
			logoutForm.logoutSuccessUrl("/login");
		});
		
		httpSecurity.oauth2Login(oauth->{
			oauth.loginPage("/login");
//			oauth.defaultSuccessUrl("/user/dashboard", true);
			oauth.successHandler(oAuthAuthenticationHandler);
		});

		
		return httpSecurity.build();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}