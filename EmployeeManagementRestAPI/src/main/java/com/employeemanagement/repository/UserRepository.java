package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
