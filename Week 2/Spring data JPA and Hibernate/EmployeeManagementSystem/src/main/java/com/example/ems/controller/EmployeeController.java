package com.example.ems.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import com.example.ems.entity.Employee;
import com.example.ems.repository.EmployeeRepository;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployeesByName(
            @RequestParam(required = false, defaultValue = "") String name, 
            Pageable pageable) {
        return ResponseEntity.ok(employeeRepository.findByNameContaining(name, pageable));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable @NonNull Long departmentId) {
        return ResponseEntity.ok(employeeRepository.findByDepartmentId(departmentId));
    }

    @GetMapping("/dtos")
    public ResponseEntity<List<Object>> getAllEmployeeDtos() {
        return ResponseEntity.ok(employeeRepository.findAllEmployeeDtos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable @NonNull Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @NonNull Employee employee) {
        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable @NonNull Long id, @RequestBody @NonNull Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok(employeeRepository.save(employeeDetails)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @NonNull Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
