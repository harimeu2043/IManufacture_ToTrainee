package com.infy.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.EmployeeDTO;
import com.infy.exception.InfyEmployeeException;
import com.infy.repository.EmployeeRepository;
import com.infy.validator.Validator;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository repo;

	@Override
	public Integer addEmployee(EmployeeDTO employee) throws InfyEmployeeException {
		Validator.validate(employee);
		EmployeeDTO repoResults = repo.getEmployeeDetails(employee.getEmployeeId());
		if (repoResults != null) {
			throw new InfyEmployeeException("Service.EMPLOYEE_ALREADY_PRESENT");
		}
		return repo.addEmployee(employee);
		
	}
	
	@Override
	public EmployeeDTO getEmployeeDetails(Integer employeeId) throws InfyEmployeeException {
		EmployeeDTO results = repo.getEmployeeDetails(employeeId);
		if (results == null) {
			throw new InfyEmployeeException("Service.EMPLOYEE_NOT_FOUND");		
		}
		return results;
	}
	
	@Override
	public void updateEmployee(Integer employeeId, String emailId) throws InfyEmployeeException {
		EmployeeDTO results = repo.getEmployeeDetails(employeeId);
		if (results == null) {
			throw new InfyEmployeeException("Service.EMPLOYEE_NOT_FOUND");		
		}
		repo.updateEmployee(employeeId, emailId);

	}
	
	@Override
	public void deleteEmployee(Integer employeeId) throws InfyEmployeeException {

		EmployeeDTO result = repo.getEmployeeDetails(employeeId);
		if (result == null) {
			throw new InfyEmployeeException("Service.EMPLOYEE_NOT_FOUND");		
		}
		repo.deleteEmployee(employeeId);
	}
}
