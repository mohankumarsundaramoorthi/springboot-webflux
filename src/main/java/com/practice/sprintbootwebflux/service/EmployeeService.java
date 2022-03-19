package com.practice.sprintbootwebflux.service;

import com.practice.sprintbootwebflux.dao.EmployeeDAO;
import com.practice.sprintbootwebflux.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> loadAllEmployees() {
        return employeeDAO.getEmployees();
    }

    public Flux<Employee> loadAllEmployeesStream() {
        return employeeDAO.getEmployeesStream();
    }
}
