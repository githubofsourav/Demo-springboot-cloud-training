package com.nagarro.DemoProjectAws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.DemoProjectAws.model.User;
import com.nagarro.DemoProjectAws.service.RegistrationService;

@RestController
@CrossOrigin("*")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		if(tempEmail != null && tempEmail != "") {
			User userObj = service.fetchUserByEmail(tempEmail);
			if(userObj != null) {
				throw new Exception("User with "+tempEmail+" already exist!");
			}
		}
		User userObj = null;
		
		userObj = service.saveUser(user);
		return userObj;
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		String tempPass = user.getPassword();
		User userObj = null;
		if(tempEmail != null && tempPass != null) {
			userObj = service.fetchUserByEmailAndPassword(tempEmail, tempPass);
		}
		if(userObj == null) {
			throw new Exception("Bad credentials!");
		}
		return userObj;
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return service.getUsers();
	}
}
