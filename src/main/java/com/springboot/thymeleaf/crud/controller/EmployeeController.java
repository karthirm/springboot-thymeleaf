package com.springboot.thymeleaf.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.thymeleaf.crud.entity.Employee;
import com.springboot.thymeleaf.crud.repository.EmployeeRepository;

@Controller
@RequestMapping("/employees/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("showForm")
	public String showStudentForm() {
		return "add-employee";
	}
	
	@GetMapping("list")
	public String employees(Model model) {
		model.addAttribute("employees", this.employeeRepository.findAll());
		return "index";
	}
	
	@PostMapping("add")
	public String addEmployee(@Valid Employee employee, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-employee";
		}
		
		this.employeeRepository.save(employee);
		return "redirect:list";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Employee employee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee id :"+ id));
		
		model.addAttribute("employee", employee);
		return "update-employee";
	}
	
	@GetMapping("/update{id}")
	public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, 
									BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			employee.setId(id);
			return "update-employee";
		}
		
		//update employee
		employeeRepository.save(employee);
		
		//get all employees
		model.addAttribute("employees", this.employeeRepository.findAll());
		return "index";	
	}
	
	@DeleteMapping("/delete/{id")
	public String deleteEmployee(@PathVariable ("id") long id, Model model) {
		
		Employee employee = this.employeeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee id :"+ id));
		
		this.employeeRepository.delete(employee);
		model.addAttribute("employees", this.employeeRepository.findAll());
		return "index";
	}
	
}
