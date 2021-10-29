package com.springboot.thymeleaf.crud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.thymeleaf.crud.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	
}
