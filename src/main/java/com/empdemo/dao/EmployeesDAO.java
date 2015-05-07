package com.empdemo.dao;

import java.util.List;

import com.empdemo.model.Employee;

public interface EmployeesDAO {
	void saveOrUpdateEmployee(Employee employee);

	Employee getEmployee(Long id);

	List<Employee> list();

	void deleteEmployee(Employee employee);

	void deleteEmployeeByEmpNo(Long empNo);
}
