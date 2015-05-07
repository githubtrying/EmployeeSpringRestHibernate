package com.empdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empdemo.dao.EmployeesDAO;
import com.empdemo.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO dao;

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		dao.saveOrUpdateEmployee(employee);
	}

	@Override
	public Employee getEmployee(Long id) {
		return dao.getEmployee(id);
	}

	@Override
	public List<Employee> list() {
		return dao.list();
	}

	@Override
	public void deleteEmployee(Employee employee) {
		dao.deleteEmployee(employee);
	}

	@Override
	public void deleteEmployeeByEmpNo(Long empNo) {
		dao.deleteEmployeeByEmpNo(empNo);
	}
}
