package com.rutkoski.hruser.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rutkoski.hruser.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmail(String email);
}
