package com.practice.sprintbootwebflux.controller;

import com.practice.sprintbootwebflux.dto.Employee;
import com.practice.sprintbootwebflux.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //Blocking operation
    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.loadAllEmployees();
    }

    //Leveraging Flux for asynchronous and non-blocking operation
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> getAllEmployeesStream() {
        return service.loadAllEmployeesStream();
    }

}
