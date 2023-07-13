package com.nagarro.DemoProjectAws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.DemoProjectAws.model.User;

public interface RegistrationRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	List<User> findAll();
}
