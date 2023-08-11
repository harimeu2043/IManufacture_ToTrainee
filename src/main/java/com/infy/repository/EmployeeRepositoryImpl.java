package com.infy.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.dto.EmployeeDTO;
import com.infy.entity.Employee;

@Repository("employeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager man;
	
	@Override
	public Integer addEmployee(EmployeeDTO employee) {
		Employee e = new Employee();
		e.setDateOfBirth(employee.getDateOfBirth());
		e.setEmailId(employee.getEmailId());
		e.setEmployeeId(employee.getEmployeeId());
		e.setManufacturingUnit(employee.getManufacturingUnit());
		e.setName(employee.getName());
		man.persist(e);
		return e.getEmployeeId();
	}

	@Override
	public EmployeeDTO getEmployeeDetails(Integer employeeId) {
		Employee e = man.find(Employee.class, employeeId);
		if (e == null) {
			return null;
		}
		EmployeeDTO dto = new EmployeeDTO();
		dto.setDateOfBirth(e.getDateOfBirth());
		dto.setEmailId(e.getEmailId());
		dto.setEmployeeId(e.getEmployeeId());
		dto.setManufacturingUnit(e.getManufacturingUnit());
		dto.setName(e.getName());
		return dto;
	}

	@Override
	public void updateEmployee(Integer employeeId, String emailId) {
		Employee e = man.find(Employee.class, employeeId);
		if (e == null) {
			return;
		}
		e.setEmailId(emailId);
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee e = man.find(Employee.class, employeeId);
		man.remove(e);
	}

}