package com.example.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.ems.entity.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByNameContaining(String name, Pageable pageable);

    List<Employee> findByDepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e")
    List<Object> findAllEmployeeDtos();
}
