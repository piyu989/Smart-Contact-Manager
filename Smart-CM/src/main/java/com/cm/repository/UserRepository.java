package com.cm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cm.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User>findByEmail(String email);
}
