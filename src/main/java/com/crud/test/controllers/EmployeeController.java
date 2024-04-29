package com.crud.test.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.test.Repo.EmployeeRepo;
import com.crud.test.model.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@PostMapping("/employees")
	public String createNewEmployee(@RequestBody Employee employee) {
		
		employeeRepo.save(employee);
		return "Employee created in database";
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		
		List<Employee> empList=new ArrayList<>();
		employeeRepo.findAll().forEach(empList ::add);
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long empid)
	{
		Optional<Employee> emp=employeeRepo.findById(empid);
		if(emp.isPresent()) {
			return new ResponseEntity<Employee>(emp.get(),HttpStatus.FOUND);
					
		}
		else {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

	
	
     @PutMapping("/employees/{empid}")
     public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee) {
    	 
    	 Optional<Employee> emp=employeeRepo.findById(empid);
    	 if(emp.isPresent()) {
    		 Employee existEmp=emp.get();
    		 existEmp.setEmp_age(employee.getEmp_age());
    		 existEmp.setEmp_city(employee.getEmp_city());
    		 existEmp.setEmp_salary(employee.getEmp_salary());
    		 employeeRepo.save(existEmp);
    		 return "employee details change success"+empid;
    		     		 
    		 
    	 }
    	 else {
    		 return "details doesn't exist";
    		 
    	 }
    	 
     }
     @DeleteMapping("/employees/{empid}")
     public String delteEmployeeByEmpId(@PathVariable long empid) {
    	 employeeRepo.deleteById(empid);
    	 return "employee deleted successfully";
     }
     
}
