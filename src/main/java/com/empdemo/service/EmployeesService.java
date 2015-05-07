package com.empdemo.service;

import java.util.List;

import com.empdemo.model.Employee;

public interface EmployeesService {

	void saveOrUpdateEmployee(Employee employee);

	Employee getEmployee(Long id);

	List<Employee> list();

	void deleteEmployee(Employee employee);
	
	void deleteEmployeeByEmpNo(Long empNo);
}
