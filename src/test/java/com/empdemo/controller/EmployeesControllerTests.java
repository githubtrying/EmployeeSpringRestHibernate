package com.empdemo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.empdemo.model.Employee;
import com.empdemo.service.EmployeesService;
import com.empdemo.utils.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeesControllerTests {
	private MockMvc mockMvc;

	@Autowired
	private EmployeesService employeeServiceMock;

	@Test
	public void list() throws Exception {
		Employee employee = new Employee();

		employee.setEmpNo(12L);
		employee.setFirstName("ramu");

		when(employeeServiceMock.list()).thenReturn(Arrays.asList(employee));

		mockMvc.perform(get("/employees"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].empNo", is(12L)))
				.andExpect(jsonPath("$[0].firstName", is("ramu")));

		verify(employeeServiceMock, times(1)).list();
		verifyNoMoreInteractions(employeeServiceMock);
	}
}
