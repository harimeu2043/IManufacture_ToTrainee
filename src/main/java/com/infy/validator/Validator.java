package com.infy.validator;

import com.infy.dto.EmployeeDTO;
import com.infy.exception.InfyEmployeeException;

public class Validator {
	
	public static void validate(EmployeeDTO employee) throws InfyEmployeeException	{
		if (!validateEmailId(employee.getEmailId())) {
			throw new InfyEmployeeException("Validator.INVALID_EMAILID");
		}
	}
	
	public static Boolean validateEmailId(String emailId)	{
		if (emailId == null) {
			return false;
		}
		return emailId.matches("[A-Za-z0-9]+@[A-Za-z0-9]+[.][A-Za-z0-9]+");
	}

}
