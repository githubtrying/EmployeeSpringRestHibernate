package com.empdemo.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.empdemo.model.Employee;
import com.empdemo.service.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
	@Autowired
	private EmployeesService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Employee> list() {
		return service.list();
	}

	// PUT request for updating/saving employee
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String updateEmployees(@RequestBody List<Employee> employees) {
		for (Employee employee : employees) {
			service.saveOrUpdateEmployee(employee);
		}
		return "success";
	}

	// DELETE request for updating/saving employee
	@RequestMapping(value = "/{empNo}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable Long empNo) {
		service.deleteEmployeeByEmpNo(empNo);
	}

	@RequestMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return service.getEmployee(id);
	}
}
