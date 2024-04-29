package com.crud.test.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.test.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
	
	

}
