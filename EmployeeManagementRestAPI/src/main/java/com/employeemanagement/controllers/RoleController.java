package com.employeemanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeemanagement.entities.Roles;
import com.employeemanagement.repository.RolesRepository;

@RestController
@RequestMapping("/api")

public class RoleController {

	@Autowired
	private RolesRepository rolesRepository;

	@PostMapping("/addroles")
	public Roles addRoles(@RequestBody Roles roles) {
		
		System.out.println(roles.getId() + " " + roles.getName());
		return rolesRepository.save(roles);
	}
}
