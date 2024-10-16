package com.cm.services;

import java.util.List;
import java.util.Optional;

import com.cm.entity.User;

public interface UserService {
	User save(User user);
	Optional<User> getUserById(String id);
	Optional<User> updateUser(User user);
	void deleteUser(String id);
	boolean isUserExist(String userId);
	boolean isUserExistByEmail(String email);
	List<User>getAllUser();
	User getUserByEmail(String email);
}
