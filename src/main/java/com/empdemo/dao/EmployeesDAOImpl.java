package com.empdemo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.empdemo.model.Employee;

@Repository("employeeDao")
public class EmployeesDAOImpl extends AbstractDao implements EmployeesDAO {

	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		saveOrUpdate(employee);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> list() {
		Criteria criteria = getSession().createCriteria(Employee.class);
		// filter to 100 records as the records are in lakhs
		criteria.setMaxResults(100);
		return (List<Employee>) criteria.list();
	}

	@Override
	public void deleteEmployee(Employee employee) {
		delete(employee);
	}

	@Override
	public Employee getEmployee(Long id) {
		Criteria criteria = getSession().createCriteria(Employee.class).add(
				Restrictions.eq("empNo", id));
		return (Employee) criteria.uniqueResult();
	}

	@Override
	public void deleteEmployeeByEmpNo(Long empNo) {
		Query query = getSession().createQuery(
				"delete Employee where empNo = :empNo");
		query.setParameter("empNo", empNo);
		query.executeUpdate();
	}

}
