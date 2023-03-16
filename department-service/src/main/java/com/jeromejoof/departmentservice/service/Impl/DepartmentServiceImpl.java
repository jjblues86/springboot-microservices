package com.jeromejoof.departmentservice.service.Impl;

import com.jeromejoof.departmentservice.dto.DepartmentDto;
import com.jeromejoof.departmentservice.entity.Department;
import com.jeromejoof.departmentservice.repository.DepartmentRepository;
import com.jeromejoof.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert DTO to entity
        Department department = mapToEntity(departmentDto);

        // convert entity to DTO
        Department savedDepartment = departmentRepository.save(department);

        return mapToDTO(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        return modelMapper.map(department, DepartmentDto.class);
    }

    private DepartmentDto mapToDTO(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }

    private Department mapToEntity(DepartmentDto departmentDto) {
        return modelMapper.map(departmentDto, Department.class);
    }
}
