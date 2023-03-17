package com.jeromejoof.employeeservice.service;

import com.jeromejoof.employeeservice.dto.APIResponseDto;
import com.jeromejoof.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    APIResponseDto getEmployeeById(Long employeeId);
}
