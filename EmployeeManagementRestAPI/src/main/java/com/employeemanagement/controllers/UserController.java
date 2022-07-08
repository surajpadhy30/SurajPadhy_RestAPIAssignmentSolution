package com.employeemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.entities.Roles;
import com.employeemanagement.entities.User;
import com.employeemanagement.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		
		System.out.println(user);
		User user1=new User();
		user1.setUserName(user.getUserName());
		user1.setPassword(user.getPassword());
		
		Roles role1=new Roles();
		role1.setId(user.getRoles().getId());
		role1.setName(user.getRoles().getName());
		user1.setRoles(role1);
		return userRepository.save(user1);
	}
}
