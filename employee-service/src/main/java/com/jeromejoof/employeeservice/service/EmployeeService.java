package com.jeromejoof.employeeservice.service;

import com.jeromejoof.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long employeeId);
}
