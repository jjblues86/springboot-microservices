package com.jeromejoof.employeeservice.service.Impl;

import com.jeromejoof.employeeservice.dto.APIResponseDto;
import com.jeromejoof.employeeservice.dto.DepartmentDto;
import com.jeromejoof.employeeservice.dto.EmployeeDTO;
import com.jeromejoof.employeeservice.entity.Employee;
import com.jeromejoof.employeeservice.exception.ResourceNotFoundException;
import com.jeromejoof.employeeservice.repository.EmployeeRepository;
import com.jeromejoof.employeeservice.service.APIClient;
import com.jeromejoof.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    private APIClient apiClient;
//    private RestTemplate restTemplate;
//    private WebClient webClient;


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        //convert DTO to entity
        Employee employee = mapToEntity(employeeDTO);

        // convert entity to DTO
        Employee savedEmployee = employeeRepository.save(employee);

        return mapToDTO(savedEmployee);
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeService", "id", employeeId));

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8081/api/departments/" +
//                        employee.getDepartmentCode(), DepartmentDto.class);
//
//        DepartmentDto departmentDto = responseEntity.getBody();

//        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" +
//                        employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee .getId(),
                employee .getFirstname(),
                employee .getLastname(),
                employee .getEmail(),
                employee .getDepartmentCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDTO);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }

    private Employee mapToEntity(EmployeeDTO employeeDTO) {
        return mapper.map(employeeDTO, Employee.class);
    }
}
