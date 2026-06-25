package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.repository.DepartmentRepository;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(int id) {
        Optional<Department> result = departmentRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}