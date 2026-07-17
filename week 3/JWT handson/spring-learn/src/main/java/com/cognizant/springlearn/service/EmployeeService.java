package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(readOnly = true)
    public ArrayList<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        employeeDao.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        employeeDao.deleteEmployee(id);
    }
}