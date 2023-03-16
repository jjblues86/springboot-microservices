package com.jeromejoof.employeeservice.repository;

import com.jeromejoof.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
