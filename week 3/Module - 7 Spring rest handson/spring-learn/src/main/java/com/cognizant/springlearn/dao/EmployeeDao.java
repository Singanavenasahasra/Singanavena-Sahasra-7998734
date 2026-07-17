package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class EmployeeDao {
    private static final ArrayList<Employee> EMPLOYEE_LIST = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml")) {
            ArrayList<Employee> list = context.getBean("staticEmployeeList", ArrayList.class);
            if (list != null) {
                EMPLOYEE_LIST.addAll(list);
            }
        }
    }

    public ArrayList<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        boolean found = false;
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EmployeeNotFoundException();
        }
    }

    public void deleteEmployee(Integer id) throws EmployeeNotFoundException {
        Employee toRemove = null;
        for (Employee emp : EMPLOYEE_LIST) {
            if (emp.getId().equals(id)) {
                toRemove = emp;
                break;
            }
        }
        if (toRemove != null) {
            EMPLOYEE_LIST.remove(toRemove);
        } else {
            throw new EmployeeNotFoundException();
        }
    }
}