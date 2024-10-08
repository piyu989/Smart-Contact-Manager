package com.cm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cm.entity.User;
import com.cm.helper.AppConstants;
import com.cm.helper.ResourceNotFoundException;
import com.cm.repository.UserRepository;
import com.cm.services.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo; 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		String userId = UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
		user.setUserId(userId);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles_list(List.of(AppConstants.ROLE_USER));
		return userRepo.save(user);
	}

	@Override
	public Optional<User> getUserById(String id) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepo.findById(id);
		return user;
	}

	@Override
	public Optional<User> updateUser(User user) {
		// TODO Auto-generated method stub
		User user2=userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("user with this id not found"));
		
		user2.setName(user.getName());
		user2.setAbout(user.getAbout());
		user2.setEmail(user.getEmail());
		user2.setEmailVerified(user.isEmailVerified());
		user2.setEnabled(user.isEnabled());	
		user2.setPhoneVerified(user.isPhoneVerified());
		user2.setPic(user.getPic());
		user2.setProvider(user.getProvider());
		user2.setProvideUserId(user.getProvideUserId());
		user2.setPassword(user.getPassword());
		user2.setPhoneNumber(user.getPhoneNumber());

		User save=userRepo.save(user2);
		return Optional.ofNullable(save);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
	}

	@Override
	public boolean isUserExist(String userId) {
		// TODO Auto-generated method stub
		User user2=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found for existence"));
		return user2!=null?true:false;
	}

	@Override
	public boolean isUserExistByEmail(String email) {
		// TODO Auto-generated method stub
		User user2=userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("user not found with this email"));
		return user2!=null?true:false;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email).orElse(null);
	}

}
