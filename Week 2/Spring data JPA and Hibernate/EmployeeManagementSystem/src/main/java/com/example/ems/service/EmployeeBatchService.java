package com.example.ems.service;

import org.springframework.stereotype.Service;
import com.example.ems.repository.EmployeeRepository;

@Service
public class EmployeeBatchService {

    private final EmployeeRepository employeeRepository;


    public EmployeeBatchService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    
    public void printStatus() {
        if (this.employeeRepository != null) {
            System.out.println("EmployeeBatchService repository is active.");
        }
    }
}
