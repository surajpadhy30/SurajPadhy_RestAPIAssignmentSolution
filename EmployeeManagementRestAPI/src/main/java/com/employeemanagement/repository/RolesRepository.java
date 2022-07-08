package com.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeemanagement.entities.Roles;
public interface RolesRepository extends JpaRepository<Roles, Long>{

}
