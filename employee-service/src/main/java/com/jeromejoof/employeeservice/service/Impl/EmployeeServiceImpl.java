package com.jeromejoof.employeeservice.service.Impl;

import com.jeromejoof.employeeservice.dto.EmployeeDTO;
import com.jeromejoof.employeeservice.entity.Employee;
import com.jeromejoof.employeeservice.exception.ResourceNotFoundException;
import com.jeromejoof.employeeservice.repository.EmployeeRepository;
import com.jeromejoof.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        //convert DTO to entity
        Employee employee = mapToEntity(employeeDTO);

        // convert entity to DTO
        Employee savedEmployee = employeeRepository.save(employee);

        return mapToDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeService", "id", employeeId));

        return mapper.map(employee, EmployeeDTO.class);
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }

    private Employee mapToEntity(EmployeeDTO employeeDTO) {
        return mapper.map(employeeDTO, Employee.class);
    }
}
