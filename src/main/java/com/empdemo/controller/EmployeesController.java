package com.empdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.empdemo.model.EmpResponse;
import com.empdemo.model.Employee;
import com.empdemo.service.EmployeesService;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
	@Autowired
	private EmployeesService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public EmpResponse list() {
		EmpResponse empResponse = new EmpResponse();
		List<Employee> list = service.list();
		empResponse.setData(list);
		return empResponse;
	}

	// PUT request for updating/saving employee
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public EmpResponse updateEmployees(@RequestBody List<Employee> employees) {
		EmpResponse empResponse = new EmpResponse();
		empResponse.setMessage("success");
		for (Employee employee : employees) {
			service.saveOrUpdateEmployee(employee);
		}
		return empResponse;
	}

	// DELETE request for updating/saving employee
	@RequestMapping(value = "/{empNo}", method = RequestMethod.DELETE)
	public void deleteEmployee(@PathVariable Long empNo) {
		service.deleteEmployeeByEmpNo(empNo);
	}

	@RequestMapping("/{id}")
	public EmpResponse getEmployee(@PathVariable Long id) {
		EmpResponse empResponse = new EmpResponse();
		empResponse.setData(service.getEmployee(id));
		return empResponse;
	}
}
